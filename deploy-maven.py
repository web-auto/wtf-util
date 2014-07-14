# Usage:
# 	python deploy.py target-project-dir

import os, sys, logging, tempfile, shutil, re
from xml.etree import ElementTree

class pom_xml:
	def __init__(self, target_project_dir):
		pom_path = os.path.join(target_project_dir, "pom.xml")
		self.project = ElementTree.parse(pom_path).getroot()
		self.ns_prefix = re.search("{.+}", self.project.tag).group(0)
        
		self.group_id = self._find('groupId').text
		self.artifact_id = self._find('artifactId').text
		self.version = self._find('version').text
		self.is_snapshot = self.version.endswith('-SNAPSHOT')
        
		if self.is_snapshot and self._find('distributionManagement/snapshotRepository') is not None:
			self.repo_url = self._find('distributionManagement/snapshotRepository/url').text
			self.repo_id = self._find('distributionManagement/snapshotRepository/id').text
			self.repo_git_url = self._get_repo_git_url(self.repo_url)
		else:
			self.repo_url = self._find('distributionManagement/repository/url').text
			self.repo_id = self._find('distributionManagement/repository/id').text
			self.repo_git_url = self._get_repo_git_url(self.repo_url)
    
	def _ns_path(self, xml_path):
		return '/'.join([self.ns_prefix + t for t in xml_path.split('/') if t != '.'])
    
	def _find(self, xml_path):
		return self.project.find(self._ns_path(xml_path))
    
	def _findall(self, xml_path):
		return self.project.findall(self._ns_path(xml_path))
    
	def _get_repo_git_url(self, repo_url):
		from urlparse import urlparse
        
		o = urlparse(self.repo_url)
		p = o.path.split('/')
		return o.scheme+"://"+o.netloc+"/"+p[1]+"/"+p[2]+".git"

class git_temp_local_repo:
	def __init__(self, repo_url):
		self.temp_dir = tempfile.mkdtemp()
        
		if os.system("git clone -n " + repo_url + " " + self.temp_dir):
			raise Exception("Failed to clone: " + repo_url)
    
	def push(self, commit_msg, branch='master', delete_after_push=True):
		if not self.temp_dir:
			raise Exception("Temporary directory was not created.")
        
		current_dir = os.getcwd()
		os.chdir(self.temp_dir)
		logging.debug("Changed directory to: " + self.temp_dir)
        
		files_to_push = self._get_files_to_push()
		for f in files_to_push:
			logging.debug("File to push: " + f)
		files_to_push_str = " ".join(files_to_push)
        
		add_command = "git add " + files_to_push_str
		logging.debug("Executing: " + add_command)
		os.system(add_command)
        
		commit_command = "git commit " + files_to_push_str + " -m '"+commit_msg+"'"
		logging.debug("Executing: " + commit_command)
		os.system(commit_command)
        
		push_command = "git push origin " + branch
		logging.debug("Executing: " + push_command)
		os.system(push_command)
        
		os.chdir(current_dir)
		logging.debug("Changed directory to: " + current_dir)
        
		if delete_after_push:
			self.delete()
    
	def delete(self):
		if self.temp_dir:
			#shutil.rmtree(self.temp_dir)
			self.temp_dir = None
    
	def _get_files_to_push(self):
		files_to_push = []
        
		for root, dirs, files in os.walk(self.temp_dir):
			if '.git' in dirs:
				dirs.remove('.git')
            
			files_to_push.extend([os.path.join(root, f) for f in files])
        
		return files_to_push

def deploy(target_project_dir):
	if not os.path.exists(target_project_dir) or not os.path.isdir(target_project_dir):
		logging.error('Target project directory does not exist: ' + target_project_dir)
		return
	target_project_dir = os.path.abspath(target_project_dir)
	logging.info('Target project directory: ' + target_project_dir)
    
	target_pom_xml = pom_xml(target_project_dir)
    
	try:
		temp_repo = git_temp_local_repo(target_pom_xml.repo_git_url)
		temp_repo_path = temp_repo.temp_dir
		logging.info("Clonining maven repository to: " + temp_repo_path)
        
		# build the target project and deploy to the temporary repo.
		current_dir = os.getcwd()
		os.chdir(target_project_dir)
        
		deploy_path = os.path.join(temp_repo_path, "releases")
		update_release = "-DupdateReleaseInfo=true"
		if target_pom_xml.is_snapshot:
			deploy_path = os.path.join(temp_repo_path, "snapshots")
			update_release = "-DupdateReleaseInfo=false"
		
		deploy_command_line = "mvn -DaltDeploymentRepository="+target_pom_xml.repo_id+"::default::file:"+deploy_path+" "+update_release+" clean deploy"
        
		if os.system(deploy_command_line):
			print "Deploy failed."
			return
        
		os.chdir(current_dir)
        
		commit_msg = target_pom_xml.group_id + ":" + target_pom_xml.artifact_id + ":" + target_pom_xml.version
		temp_repo.push(commit_msg)
	finally:
		temp_repo.delete()

if __name__ == '__main__':
	log_format = '%(asctime)s %(filename)s(%(lineno)d) %(levelname)s %(message)s'
	logging.basicConfig(level=logging.INFO, format=log_format)
    
	if len(sys.argv) < 2:
		print "usage: python "+os.path.basename(__file__)+" target-project-dir"
		exit(1)
    
	try:
		deploy(sys.argv[1])
	except Exception as exception:
		logging.exception(exception)