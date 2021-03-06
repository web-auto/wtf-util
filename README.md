wtf-util
========

[![Build Status](https://travis-ci.org/web-auto/wtf-util.svg?branch=master)](https://travis-ci.org/web-auto/wtf-util)
[![Coverage Status](https://coveralls.io/repos/web-auto/wtf-util/badge.png?branch=master)](https://coveralls.io/r/web-auto/wtf-util?branch=master)

### What is WTF Util?
The WTF Util is a collection of util methods for writing webdriver tests on Java. These util methods will makes the webdriver testing on browsers more reliable, easier and stable.

### Util method pattern
All our util methods supports 4 unique method patterns. All 4 variations does the same thing but it provides options like explicit wait condition to use, options to pass parent element to isolate or minimize the DOM lookup etc..

```
1. utilMethodName(Webdriver driver, By by);
2. utilMethodName(Webdriver driver, WebdriverWait wait, By by);
3. utilMethodName(Webdriver, WebElement ParenetElement, By by);
4. utilMethodName(Webdriver driver, WebdriverWait wait,  WebElement ParenetElement, By by);
```

### Util Methods
* [`attributeValuePresent`](#attributeValuePresent)
* [`clear`](#clear)
* [`click`](#click)
* [`findElement`](#findElement)
* [`findElements`](#findElements)
* [`getAttributeValue`](#getAttributeValue)
* [`getSelectElement`](#getSelectElement)
* [`getText`](#getText)
* [`getValue`](#getValue)
* [`hover`](#hover)
* [`invisible`](#invisible)
* [`notPresent`](#notPresent)
* [`notSelected`](#notSelected)
* [`present`](#present)
* [`selected`](#selected)
* [`textNotPresent`](#textNotPresent)
* [`textPresent`](#textPresent)
* [`type`](#type)
* [`visible`](#visible)


### Start using in your Webdriver project ( Maven Repo. and Deps. )
```xml
 <repositories>
     <repository>
         <id>my-repo</id>
         <url>https://github.com/web-auto/wtf-maven-repo/raw/master/releases</url>
     </repository>
 </repositories>


  <dependencies>
    <dependency>
      <groupId>org.wtf</groupId>
      <artifactId>wtf-util</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>
```

<a name="attributeValuePresent" />
### AttributeValuePresent
###### attributeValuePresent(driver, by, attr, value)
###### attributeValuePresent(driver, wait, by, attr, value)
###### attributeValuePresent(driver, parent, by, attr, value)
###### attributeValuePresent(driver, wait, parent, by, attr, value)

Find and return boolean if the given `attr` name and `value` found on element using the given `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance
* `attr` - The attribut to lookup
* `value` - The value to match

__Example__

```java
public void testAttributeValuePresent() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Assert.assertTrue(WTFUtil.attributeValuePresent(driver, By.className("some-class-name"),
                    "myattr", "my-attr-value"));
}
```

---------------------------------------


<a name="clear" />
### Clear
###### clear(driver, by)
###### clear(driver, wait, by)
###### clear(driver, parent, by)
###### clear(driver, wait, parent, by)

Clear the text field element using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testClear() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WTFUtil.clear(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="click" />
### Click
###### click(driver, by)
###### click(driver, wait, by)
###### click(driver, parent, by)
###### click(driver, wait, parent, by)

Click on an element using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testClick() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WTFUtil.click(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="findElement" />
### FindElement
###### findElement(driver, by)
###### findElement(driver, wait, by)
###### findElement(driver, parent, by)
###### findElement(driver, wait, parent, by)

Find and returns a WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testFindElement() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WebElement webElement = WTFUtil.findElement(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="findElements" />
### FindElements
###### findElements(driver, by)
###### findElements(driver, wait, by)
###### findElements(driver, parent, by)
###### findElements(driver, wait, parent, by)

Find and returns a list of WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testFindElements() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WebElement [] webElements = WTFUtil.findElements(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="getAttributeValue" />
### GetAttributeValue
###### getAttributeValue(driver, by, attr)
###### getAttributeValue(driver, wait, by, attr)
###### getAttributeValue(driver, parent, by, attr)
###### getAttributeValue(driver, wait, parent, by, attr)

Find and returns an attrubute value for the given attribute name on a WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance
* `attr` - An attribut to find


__Example__

```java
public void testGetAttributeValue() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  String attrVal = WTFUtil.getAttributeValue(driver, By.className("some-class-name"), "some-attribute");
}
```

---------------------------------------


<a name="getSelectElement" />
### GetSelectElement
###### getSelectElement(driver, by)
###### getSelectElement(driver, wait, by)
###### getSelectElement(driver, parent, by)
###### getSelectElement(driver, wait, parent, by)

Find and returns the Webdriver Select object using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testGetSelectElement() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Select select = WTFUtil.getSelectElement(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="getText" />
### GetText
###### getText(driver, by)
###### getText(driver, wait, by)
###### getText(driver, parent, by)
###### getText(driver, wait, parent, by)

Returns the text value of an element using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testGetText() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  String text = WTFUtil.getText(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="getValue" />
### GetValue
###### getValue(driver, by)
###### getValue(driver, wait, by)
###### getValue(driver, parent, by)
###### getValue(driver, wait, parent, by)

Returns the value from an element using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testValue() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  String value = WTFUtil.getValue(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="hover" />
### Hover
###### hover(driver, by)
###### hover(driver, wait, by)
###### hover(driver, parent, by)
###### hover(driver, wait, parent, by)

Hover on an element using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testHover() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WTFUtil.hover(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="invisible" />
### Invisible
###### invisible(driver, by)
###### invisible(driver, wait, by)
###### invisible(driver, parent, by)
###### invisible(driver, wait, parent, by)

Returns boolean if an element is not visible using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testInvisible() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean inVisible = WTFUtil.invisible(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="notPresent" />
### NotPresent
###### notPresent(driver, by)
###### notPresent(driver, wait, by)
###### notPresent(driver, parent, by)
###### notPresent(driver, wait, parent, by)

Determines the given element is not present on the DOM using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testNotPresent() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean visible = WTFUtil.notPresent(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="notSelected" />
### NotSelected
###### notSelected(driver, by)
###### notSelected(driver, wait, by)
###### notSelected(driver, parent, by)
###### notSelected(driver, wait, parent, by)

Determines the given item is not selected yet on a Select WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testNotSelected() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean notSlected = WTFUtil.notSelected(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="present" />
### Present
###### present(driver, by)
###### present(driver, wait, by)
###### present(driver, parent, by)
###### present(driver, wait, parent, by)

Determines the WebElement is present on DOM using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testPresent() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean visible = WTFUtil.present(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="selected" />
### Selected
###### selected(driver, by)
###### selected(driver, wait, by)
###### selected(driver, parent, by)
###### selected(driver, wait, parent, by)

Determines the given item is selected yet on a Select WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testSelected() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean selected = WTFUtil.notSelected(driver, By.className("some-class-name"));
}
```

---------------------------------------


<a name="textNotPresent" />
### TextNotPresent
###### textNotPresent(driver, by, text)
###### textNotPresent(driver, wait, by, text)
###### textNotPresent(driver, parent, by, text)
###### textNotPresent(driver, wait, parent, by, text)

Determines the given text is not present on the WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance
* `text` - A Webdriver By instance

__Example__

```java
public void testTextNotPresent() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean textPresent = WTFUtil.TextNotPresent(driver, By.className("some-class-name"), "some text");
}
```

---------------------------------------


<a name="textPresent" />
### TextPresent
###### textPresent(driver, by, text)
###### textPresent(driver, wait, by, text)
###### textPresent(driver, parent, by, text)
###### textPresent(driver, wait, parent, by, text)

Determines the given text is present on the WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance
* `text` - A Webdriver By instance

__Example__

```java
public void testTextPresent() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean textPresent = WTFUtil.notSelected(driver, By.className("some-class-name"), "some text");
}
```

---------------------------------------


<a name="type" />
### Type
###### type(driver, by, text)
###### type(driver, wait, by, text)
###### type(driver, parent, by, text)
###### type(driver, wait, parent, by, text)

Types the give text on a WebElement using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance
* `text` - A Webdriver By instance

__Example__

```java
public void testType() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  WTFUtil.type(driver, By.className("some-class-name"), "some text..");
}
```

---------------------------------------
<a name="visible" />
### Visible
###### visible(driver, by)
###### visible(driver, wait, by)
###### visible(driver, parent, by)
###### visible(driver, wait, parent, by)

Returns boolean if an element is visible using `by`.

__Arguments__

* `driver` - An instance of Webdriver
* `wait` - An instance of WebdriverWait (optional) 
* `parent` - The parent element to look in (optional)
* `by` - A Webdriver By instance

__Example__

```java
public void testVisible() {
  Webdriver driver = new FirefoxDriver();
  driver.get("http://www.example.com");
  Boolean visible = WTFUtil.visible(driver, By.className("some-class-name"));
}
```

---------------------------------------

### Team

 * Author & Lead Maintainer: [Venkat Sundramurthy](https://github.com/vsundramurthy)
 * Contributors:
   * [Vineet Kothari](https://github.com/geekdevil)
   * [Vikram](https://github.com/vikram1711)

### License

  [MIT](LICENSE)

