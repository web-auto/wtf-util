/**
 * Copyright (C) 2014 WTF org.
 */

package org.wtf.utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.wtf.utils.WTFExpectedConditions.*;

/**
 * WTF Utils. Collection of util methods for invoking browser automation calls
 * more easier and much stable.
 *
 * @author venkatesan.sundramurthy@gmail.com (Venkatesan Sundramurthy)
 */
public class WTFUtil {

  public static Long WAIT_TIMEOUT_IN_SECONDS = 30L;

  private WTFUtil() {
    // Utility class
  }

  private static WebDriverWait getDefaultWait(WebDriver driver) {
    return new WebDriverWait(driver, WAIT_TIMEOUT_IN_SECONDS);
  }

  /**
   * Updates the default wait time out seconds with the user provided value.
   *
   * @param timeout the time out in seconds
   */
  public static void setDefaultWaitTimeout(Long timeout) {
    WAIT_TIMEOUT_IN_SECONDS = timeout;
  }

  /**
   * Clicks the web element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   */
  public static void click(WebDriver driver, WebDriverWait wait, By by) {
    wait.until(elementToBeClickable(by)).click();
  }

  /**
   * Clicks the web element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   */
  public static void click(WebDriver driver, By by) {
    click(driver, getDefaultWait(driver), by);
  }

  /**
   * Clicks the web element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void click(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    wait.until(elementToBeClickable_(parent, by)).click();
  }

  /**
   * Clicks the web element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void click(WebDriver driver, WebElement parent, By by) {
    click(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element's presence using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(presenceOfElementLocated(by))!= null;
  }

  /**
   * Determines the web element's presence using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, By by) {
    return present(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's presence inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by) {
    return wait.until(presenceOfElementLocated_(parent, by)) != null;
  }

  /**
   * Determines the web element's presence inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebElement parent, By by) {
    return present(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the given web element is no longer present on the DOM using driver, wait.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param el the web element to be checked
   * @return true if present
   */
  public static Boolean notPresent(WebDriver driver, WebDriverWait wait, WebElement el) {
    return wait.until(stalenessOf(el));
  }

  /**
   * Determines the given web element is no longer present on the DOM using driver and locator.
   *
   * @param driver the WebDriver
   * @param el the web element to be checked
   * @return true if present
   */
  public static Boolean notPresent(WebDriver driver, WebElement el) {
    return notPresent(driver, getDefaultWait(driver), el);
  }

  /**
   * Determines the web element's visibility using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(visibilityOfElementLocated(by)) != null;
  }

  /**
   * Determines the web element's visibility using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, By by) {
    return visible(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's visibility inside a sub DOM using driver, wait, parent and
   * locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by) {
    return wait.until(visibilityOfElementLocated_(parent, by)) != null;
  }

  /**
   * Determines the web element's visibility inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebElement parent, By by) {
    return visible(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element's invisibility using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(invisibilityOfElementLocated(by));
  }

  /**
   * Determines the web element's invisibility using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, By by) {
    return invisible(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's invisibility inside a sub DOM using driver, wait, parent and
   * locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    return wait.until(invisibilityOfElementLocated_(parent, by));
  }

  /**
   * Determines the web element's invisibility inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebElement parent, By by) {
    return invisible(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Returns the matching web element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(presenceOfElementLocated(by));
  }

  /**
   * Returns the matching web element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, By by) {
    return findElement(driver, getDefaultWait(driver), by);
  }

  /**
   * Returns the matching web element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by) {
    return wait.until(presenceOfElementLocated_(parent, by));
  }

  /**
   * Returns the matching web element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebElement parent, By by) {
    return findElement(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Returns the list of matching web elements using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List <WebElement> findElements(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(presenceOfAllElementsLocatedBy(by));
  }
  /**
   * Returns the list of matching web elements using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List <WebElement> findElements(WebDriver driver, By by) {
    return findElements(driver, getDefaultWait(driver), by);
  }

  /**
   * Returns the list of matching web elements inside a sub DOM using driver, wait, parent and
   * locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List <WebElement> findElements(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(presenceOfAllElementsLocatedBy_(parent, by));
  }

  /**
   * Returns the list of matching web elements inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List <WebElement> findElements(WebDriver driver, WebElement parent, By by) {
    return findElements(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element is selected using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(elementSelectionStateToBe(by, true));
  }

  /**
   * Determines the web element is selected using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, By by) {
    return selected(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element is selected inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by) {
    return wait.until(elementSelectionStateToBe_(parent, by, true)) != null;
  }

  /**
   * Determines the web element is selected inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebElement parent, By by) {
    return selected(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element is not selected using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(elementSelectionStateToBe(by, false));
  }

  /**
   * Determines the web element is not selected using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, By by) {
    return notSelected(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element is not selected inside a sub DOM using driver, wait, parent and
   * locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by) {
    return wait.until(elementSelectionStateToBe_(parent, by, false));
  }

  /**
   * Determines the web element is not selected inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebElement parent, By by) {
    return notSelected(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Types the given text using the driver, wait locator, text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @param text the text to be typed
   */
  public static void type(WebDriver driver, WebDriverWait wait, By by, String text) {
    wait.until(visibilityOfElementLocated(by)).sendKeys(text);
  }

  /**
   * Types the given text using the using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @param text the text to be typed
   */
  public static void type(WebDriver driver, By by, String text) {
    type(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Types the given text on an element inside a sub DOM using driver, wait, parent, locator and
   * text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be typed
   */
  public static void type(WebDriver driver, WebDriverWait wait, WebElement parent, By by,
      String text) {
    wait.until(visibilityOfElementLocated_(parent, by)).sendKeys(text);
  }

  /**
   * Types the given text on an element inside a sub DOM using driver, parent, locator and text
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be typed
   */
  public static void type(WebDriver driver, WebElement parent, By by, String text) {
    type(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Clears the text using the given driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   */
  public static void clear(WebDriver driver, WebDriverWait wait, By by) {
    wait.until(visibilityOfElementLocated(by)).clear();
  }

  /**
   * Clears the text using the given driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   */
  public static void clear(WebDriver driver, By by) {
    clear(driver, getDefaultWait(driver), by);
  }

  /**
   * Clears the text on an element inside a sub DOM using driver, wait, parent, locator and text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void clear(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    wait.until(visibilityOfElementLocated_(parent, by)).clear();
  }

  /**
   * Clears the text on an element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void clear(WebDriver driver, WebElement parent, By by) {
    clear(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the give text is present on a web element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  @SuppressWarnings("deprecation")
  public static Boolean textPresent(WebDriver driver, WebDriverWait wait, By by, String text) {
    return wait.until(textToBePresentInElement(by, text));
  }

  /**
   * Determines the give text is present on a web element using driver, locator and text.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, By by, String text) {
    return textPresent(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Determines the give text is present on a web element inside a sub DOM using driver, wait,
   * parent, locator and text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by, String text) {
    return wait.until(textToBePresentInElement_(parent, by, text));
  }

  /**
   * Determines the give text is present on a web element inside a sub DOM using driver, parent,
   * locator, text.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, WebElement parent, By by, String text) {
    return textPresent(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Determines the give text not present on a web element using driver, wait, locator and text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebDriverWait wait, By by, String text) {
    return wait.until(invisibilityOfElementWithText(by, text));
  }

  /**
   * Determines the give text not present on a web element using driver, locator and text.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, By by, String text) {
    return textNotPresent(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Determines the give text not present on a web element inside a sub DOM using driver, wait,
   * parent, locator and text.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebDriverWait wait, WebElement parent,
      By by, String text) {
    return wait.until(invisibilityOfElementWithText_(parent, by, text));
  }

  /**
   * Determines the give text not present on a web element inside a sub DOM using driver, parent,
   * locator, text.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param text the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebElement parent, By by, String text) {
    return textNotPresent(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Determines the give attribute name and value present on a web element using driver, wait,
   * locator, attribute name and attribute value.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @param attributeValue the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver, WebDriverWait wait, By by,
      String attributeName, String attributeValue) {
    return wait.until(attributeValueToBePresentInElement(by, attributeName, attributeValue));
  }

  /**
   * Determines the give attribute name and value present on a web element using driver, locator,
   * attribute name and attribute value.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @param attributeValue the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver, By by, String attributeName,
      String attributeValue) {
    return attributeValuePresent(driver, getDefaultWait(driver), by, attributeName, attributeValue);
  }

  /**
   * Determines the give attribute name and value present on a web element inside a sub DOM using
   * driver, wait, parent, locator, attribute name and attribute value.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @param attributeValue the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String attributeName, String attributeValue) {
    return wait.until(attributeValueToBePresentInElement_(parent, by, attributeName,
        attributeValue));
  }

  /**
   * Determines the give attribute name and value present on a web element inside a sub DOM using
   * driver, parent, locator, attribute name and attribute value.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @param attributeValue the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver, WebElement parent, By by,
      String attributeName, String attributeValue) {
    return attributeValuePresent(driver, getDefaultWait(driver), parent, by, attributeName,
        attributeValue);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element using driver, wait,
   * locator and attribute name.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebDriverWait wait, By by,
      String attributeName) {
    return findElement(driver, wait, by).getAttribute(attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element using driver, locator
   * and attribute name.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, By by, String attributeName) {
    return getAttributeValue(driver, getDefaultWait(driver), by, attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element inside a sub DOM using
   * driver, wait, parent, locator and attribute name.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String attributeName) {
    return findElement(driver, wait, parent, by).getAttribute(attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element inside a sub DOM using
   * driver, parent, locator and attribute name.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @param attributeName the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebElement parent, By by,
      String attributeName) {
    return getAttributeValue(driver, getDefaultWait(driver), parent, by, attributeName);
  }

  /**
   * Gets the text from a web element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebDriverWait wait, By by) {
    return findElement(driver, wait, by).getText();
  }

  /**
   * Gets the text from a web element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, By by) {
    return getText(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the text from a web element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    return findElement(driver, wait, parent, by).getText();
  }

  /**
   * Gets the text from a web element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebElement parent, By by) {
    return getText(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Gets the value from a web element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebDriverWait wait, By by) {
    return getAttributeValue(driver, by, "value");
  }

  /**
   * Gets the value from a web element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, By by) {
    return getValue(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the value from a web element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    return getAttributeValue(driver, wait, parent, by, "value");
  }

  /**
   * Gets the value from a web element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebElement parent, By by) {
    return getValue(driver, getDefaultWait(driver), parent, by);
  }


  /**
   * Gets the Select element using using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   * @return Select object
   */
  public static Select getSelectElement(WebDriver driver, WebDriverWait wait, By by) {
    return new Select(findElement(driver, wait, by));
  }

  /**
   * Gets the Select element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   * @return Select object
   */
  public static Select getSelectElement(WebDriver driver, By by) {
    return getSelectElement(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the Select element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return Select value
   */
  public static Select getSelectElement(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    return new Select(findElement(driver, wait, parent, by));
  }

  /**
   * Gets the Select element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   * @return Select value
   */
  public static Select getSelectElement(WebDriver driver, WebElement parent, By by) {
    return getSelectElement(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Hovers on element using driver, wait and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param by locator used to find the element
   */
  public static void hover(WebDriver driver, WebDriverWait wait, By by) {
    new Actions(driver).moveToElement(findElement(driver, by)).build().perform();
  }

  /**
   * Hovers on element using driver and locator.
   *
   * @param driver the WebDriver
   * @param by locator used to find the element
   */
  public static void hover(WebDriver driver, By by) {
    findElement(driver, by);
    hover(driver, getDefaultWait(driver), by);
  }

  /**
   * Hovers on element inside a sub DOM using driver, wait, parent and locator.
   *
   * @param driver the WebDriver
   * @param wait the WebDriverWait
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void hover(WebDriver driver, WebDriverWait wait, WebElement parent, By by) {
    new Actions(driver).moveToElement(findElement(driver, parent, by)).build().perform();
  }

  /**
   * Hovers on element inside a sub DOM using driver, parent and locator.
   *
   * @param driver the WebDriver
   * @param parent the sub DOM to look
   * @param by locator used to find the element
   */
  public static void hover(WebDriver driver, WebElement parent, By by) {
    hover(driver, getDefaultWait(driver), parent, by);
  }
}
