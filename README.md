wtf-util
========

[![Build Status](https://travis-ci.org/web-auto/wtf-util.svg?branch=master)](https://travis-ci.org/web-auto/wtf-util)
[![Coverage Status](https://coveralls.io/repos/web-auto/wtf-util/badge.png?branch=master)](https://coveralls.io/r/web-auto/wtf-util?branch=master)

### What is WTF Util?
The WTF Util is a collection of util methods for webdriver testing. Util methods that makes the webdriver testing on browser  much easier and stable.

### Util method pattern
All our util methods supports 4 unique method patterns. All 4 variations does the same thing but it provides options like explicit wait condition to use, options to pass parent element to isolate the DOM lookup etc..

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


### Maven repository and Dependency
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

### Team

 * Author & Lead Maintainer: [Venkat Sundramurthy](https://github.com/vsundramurthy)
 * Contributors:
   * [Vineet Kothari](https://github.com/geekdevil)
   * [Vikram](https://github.com/vikram1711)

### License

  [MIT](LICENSE)

