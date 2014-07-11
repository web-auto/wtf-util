package org.wtf.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WTFExpectedConditions {

  private final static Logger log = Logger.getLogger(WTFExpectedConditions.class.getName());

  private WTFExpectedConditions() {
    // Utility class
  }

  /**
   * @return the given element if it is visible and has non-zero size, otherwise null.
   */
  private static WebElement elementIfVisible(WebElement element) {
    return element.isDisplayed() ? element : null;
  }

  /**
   * Looks up an element. Logs and re-throws WebDriverException if thrown. <p/>
   * Method exists to gather data for http://code.google.com/p/selenium/issues/detail?id=1800
   */
  private static WebElement findElement(By by, WebDriver driver) {
    try {
      return driver.findElement(by);
    } catch (NoSuchElementException e) {
      throw e;
    } catch (WebDriverException e) {
      log.log(Level.WARNING,
          String.format("WebDriverException thrown by findElement(%s)", by), e);
      throw e;
    }
  }

  /**
   * Looks up an element. Logs and re-throws WebDriverException if thrown. <p/>
   */
  private static WebElement findElement(By by, WebElement el) {
    try {
      return el.findElement(by);
    } catch (NoSuchElementException e) {
      return null;
    } catch (WebDriverException e) {
      log.log(Level.WARNING, String.format("WebDriverException thrown by findElement(%s)", by), e);
      return null;
    }
  }

  /**
   * Looks up an element. Logs and re-throws WebDriverException if thrown. <p/>
   */
  private static WebElement findElementSilent(By by, WebElement el) {
    try {
      return el.findElement(by);
    } catch (NoSuchElementException e) {
      return null;
    } catch (WebDriverException e) {
      return null;
    }
  }

  /**
   * @see #findElement(By, WebElement)
   */
  private static List<WebElement> findElements(By by, WebElement el) {
    try {
      return el.findElements(by);
    } catch (WebDriverException e) {
      log.log(Level.WARNING,
          String.format("WebDriverException thrown by findElement(%s)", by), e);
      throw e;
    }
  }

  /**
   * An expectation for checking that an element is present on the sub DOM of a page and visible.
   * Visibility means that the element is not only displayed but also has a height and width that
   * is greater than 0.
   *
   * @param parent the WebElement of a sub DOM
   * @param locator used to find the element
   * @return the WebElement once it is located and visible
   */
  public static ExpectedCondition<WebElement> visibilityOfElementLocated_(final WebElement parent,
      final By locator) {
    return new ExpectedCondition<WebElement>() {
      public WebElement apply(WebDriver driver) {
        try {
          return elementIfVisible(findElement(locator, parent));
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return "visibility of element located by " + locator;
      }
    };
  }

  /**
   * An expectation for checking an element is visible and enabled such that you
   * can click it.
   */
  public static ExpectedCondition<WebElement> elementToBeClickable_(final WebElement parent,
      final By locator) {
    return new ExpectedCondition<WebElement>() {

      public ExpectedCondition<WebElement> visibilityOfElementLocated =
        visibilityOfElementLocated_(parent, locator);

      public WebElement apply(WebDriver driver) {
        WebElement element = visibilityOfElementLocated.apply(driver);
        try {
          if (element != null && element.isEnabled()) {
            return element;
          } else {
            return null;
          }
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return "element to be clickable: " + locator;
      }
    };
  }

  /**
   * An expectation for checking that an element is present on the DOM of a
   * page. This does not necessarily mean that the element is visible.
   *
   * @param locator used to find the element
   * @return the WebElement once it is located
   */
  public static ExpectedCondition<WebElement> presenceOfElementLocated_(final WebElement parent,
      final By locator) {
    return new ExpectedCondition<WebElement>() {
      
      public WebElement apply(WebDriver driver) {
        return findElement(locator, parent);
      }

      public String toString() {
        return "presence of element located by: " + locator;
      }
    };
  }

  /**
   * An expectation for checking that an element is either invisible or not
   * present on the DOM.
   *
   * @param locator used to find the element
   */
  public static ExpectedCondition<Boolean> invisibilityOfElementLocated_(final WebElement parent,
      final By locator) {
    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          return !(findElementSilent(locator, parent) != null &&
              findElementSilent(locator, parent).isDisplayed());
        } catch (NoSuchElementException e) {
          // Returns true because the element is not present in DOM. The
          // try block checks if the element is present but is invisible.
          return true;
        } catch (StaleElementReferenceException e) {
          // Returns true because stale element reference implies that element
          // is no longer visible.
          return true;
        }
      }

      public String toString() {
        return "element to no longer be visible: " + locator;
      }
    };
  }

  /**
   * An expectation for checking that there is at least one element present on a
   * web page.
   *
   * @param locator used to find the element
   * @return the list of WebElements once they are located
   */
  public static ExpectedCondition<List<WebElement>> presenceOfAllElementsLocatedBy_(
      final WebElement parent, final By locator) {
    return new ExpectedCondition<List<WebElement>>() {
      
      public List<WebElement> apply(WebDriver driver) {
        List<WebElement> elements = findElements(locator, parent);
        return elements.size() > 0 ? elements : null;
      }

      
      public String toString() {
        return "presence of any elements located by " + locator;
      }
    };
  }

  public static ExpectedCondition<Boolean> elementSelectionStateToBe_(final WebElement parent,
      final By locator, final boolean selected) {
    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          WebElement element = findElement(locator, parent);
          return element.isSelected() == selected;
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return String.format("element found by %s to %sbe selected",
            locator, (selected ? "" : "not "));
      }
    };
  }

  /**
   * An expectation for checking if the given text is present in the specified
   * element.
   */
  public static ExpectedCondition<Boolean> textToBePresentInElement_(final WebElement parent,
      final By locator, final String text) {

    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          String elementText = findElement(locator, parent).getText();
          return elementText.contains(text);
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return String.format("text ('%s') to be present in element found by %s",
            text, locator);
      }
    };
  }

  /**
   * An expectation for checking that an element with text is either invisible
   * or not present on the DOM.
   *
   * @param locator used to find the element
   * @param text of the element
   */
  public static ExpectedCondition<Boolean> invisibilityOfElementWithText_(final WebElement parent,
      final By locator, final String text) {
    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          return !findElement(locator, parent).getText().equals(text);
        } catch (NoSuchElementException e) {
          // Returns true because the element with text is not present in DOM. The
          // try block checks if the element is present but is invisible.
          return true;
        } catch (StaleElementReferenceException e) {
          // Returns true because stale element reference implies that element
          // is no longer visible.
          return true;
        }
      }

      public String toString() {
        return String.format("element containing '%s' to no longer be visible: %s",
            text, locator);
      }
    };
  }

  /**
   * An expectation for checking if the given attribute value is present in the specified
   * elements's attribute name.
   */
  public static ExpectedCondition<Boolean> attributeValueToBePresentInElement(
      final By locator, final String attributeName, final String attributeValue) {

    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          String elementText = findElement(locator, driver).getAttribute(attributeName);
          if (elementText != null) {
            return elementText.contains(attributeValue);
          } else {
            return false;
          }
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return String.format(
            "Attribute value ('%s') of the attribute name ('%s') of element located by %s",
            attributeValue, attributeName, locator);
      }
    };
  }

  /**
   * An expectation for checking if the given attribute value is present in a sub DOM and in the
   * specified elements's attribute name.
   */
  public static ExpectedCondition<Boolean> attributeValueToBePresentInElement_(
      final WebElement parent, final By locator, final String attributeName,
      final String attributeValue) {

    return new ExpectedCondition<Boolean>() {
      
      public Boolean apply(WebDriver driver) {
        try {
          String elementText = findElement(locator, parent).getAttribute(attributeName);
          if (elementText != null) {
            return elementText.contains(attributeValue);
          } else {
            return false;
          }
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      public String toString() {
        return String.format(
            "Attribute value ('%s') of the attribute name ('%s') of element located by %s",
            attributeValue, attributeName, locator);
      }
    };
  }
}
