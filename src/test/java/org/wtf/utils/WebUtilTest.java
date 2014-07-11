package org.wtf.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.wtf.core.*;
import org.wtf.core.annotation.owner.TestOwner;

import static org.wtf.utils.WTFUtil.*;


@Test(dataProvider = "WEBDRIVER")
@TestOwner(email = "vsundramurthy")
public class WebUtilTest extends WTFTest{

  public static final String SERVER = "http://web-auto.github.io/wtf-util/test/";
  public static final String CLICK_FILE = SERVER + "page.html";

  public static final String parentClassName = "parent";

  // click buttons
  public static final String btn1ClasName = "button1";
  public static final String btn1ClickedText = "clicked";
  public static final String btn2RemovefromDomClassName = "buttonRemovefromDom";
  public static final String btn3Invisible = "buttonInvisible";
  public static final String btnHoverClassName = "buttonHover";

  // check box
  public static final String checkBoxSelectedClasName = "checkboxSelected";
  public static final String checkBoxNotSelectedClasName = "checkboxNotSelected";

  // text box
  public static final String textBox1ClassName = "textbox1";
  public static final String textBoxClearClassName = "textboxClear";

  // div
  public static final String divWithTextClassName = "divWithText";
  public static final String divWithCustomAttributeClassName = "divWithCustomAttribute";

  // select box
  public static final String selectClassName = "select";


  public void testClick(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // click the button using class name
    click(env.getDriver(), By.className(btn1ClasName));

    // verify the button value has changed
    Assert.assertEquals(getValue(env.getDriver(), By.className(btn1ClasName)), btn1ClickedText);

    // load the click test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // click the button using class name
    click(env.getDriver(), parent, By.className(btn1ClasName));

    // verify the button value has changed
    Assert.assertEquals(getValue(env.getDriver(), By.className(btn1ClasName)), btn1ClickedText);
  }

  public void testPresent(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the button present.
    Assert.assertTrue(present(env.getDriver(), By.className(btn1ClasName)));

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

 // check the button not present.
    Assert.assertTrue(present(env.getDriver(), parent, By.className(btn1ClasName)));
  }

  public void testNotPresent(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // get the button element 
    WebElement button = findElement(env.getDriver(), By.className(btn2RemovefromDomClassName));

    // click the button to get it removed
    button.click();

    // check the button not present.
    Assert.assertTrue(notPresent(env.getDriver(), button));
  }

  public void testVisible(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the button present.
    Assert.assertTrue(visible(env.getDriver(), By.className(btn1ClasName)));

    // load the click test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // verify the button value has changed
    Assert.assertTrue(visible(env.getDriver(), parent, By.className(btn1ClasName)));
  }

  public void testInvisible(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check search button is invisible on the DOM.
    Assert.assertTrue(invisible(env.getDriver(), By.className(btn3Invisible)));

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check search button is invisible on the sub DOM.
    Assert.assertTrue(invisible(env.getDriver(), parent, By.className(btn3Invisible)));
  }

  public void testFindElement(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the visible button
    Assert.assertNotNull(findElement(env.getDriver(), By.className(btn1ClasName)));

    // find the in visible button
    Assert.assertNotNull(findElement(env.getDriver(), By.className(btn3Invisible)));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // find the visible button
    Assert.assertNotNull(findElement(env.getDriver(), parent, By.className(btn1ClasName)));

    // find the in visible button
    Assert.assertNotNull(findElement(env.getDriver(), parent, By.className(btn3Invisible)));
  }

  public void testFindElements(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the visible button
    Assert.assertNotNull(findElements(env.getDriver(), By.className(btn1ClasName)).size() > 2);

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // find the visible button
    Assert.assertNotNull(findElements(env.getDriver(), parent, By.className(btn1ClasName)).size() > 2);
  }

  public void testSelecetd(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the check box is selected.
    Assert.assertTrue(selected(env.getDriver(), By.className(checkBoxSelectedClasName)));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check the check box is selected.
    Assert.assertTrue(selected(env.getDriver(), parent, By.className(checkBoxSelectedClasName)));
  }

  public void testNotSelected(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the check box is not selected.
    Assert.assertTrue(notSelected(env.getDriver(), By.className(checkBoxNotSelectedClasName)));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check the check box is not selected.
    Assert.assertTrue(notSelected(env.getDriver(), parent, By.className(checkBoxNotSelectedClasName)));
  }

  public void testType(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // type a text.
    type(env.getDriver(), By.className(textBox1ClassName), "Hello");

    // verify the text
    Assert.assertEquals(getValue(env.getDriver(), By.className(textBox1ClassName)), "Hello");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // type a text.
    type(env.getDriver(), parent, By.className(textBox1ClassName), "Hello");

    // verify the text
    Assert.assertEquals(getValue(env.getDriver(), By.className(textBox1ClassName)), "Hello");
  }

  public void testTextPresent(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the DIV el with text Hello
    Assert.assertTrue(textPresent(env.getDriver(), By.className(divWithTextClassName), "Hello"));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check the DIV el with text Hello
    Assert.assertTrue(textPresent(env.getDriver(), parent, By.className(divWithTextClassName), "Hello"));
  }

  public void testTextNotPresent(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check the DIV el text not present
    Assert.assertTrue(textNotPresent(env.getDriver(), By.className(divWithTextClassName), "Hello123"));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check the DIV el text not present
    Assert.assertTrue(textNotPresent(env.getDriver(), parent, By.className(divWithTextClassName), "Hello123"));
  }

  public void testGetText(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // get text.
    Assert.assertEquals(getText(env.getDriver(), By.className(divWithTextClassName)), "Hello");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // get text.
    Assert.assertEquals(getText(env.getDriver(), parent, By.className(divWithTextClassName)), "Hello");
  }

  public void testGetValue(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check value
    Assert.assertEquals(getValue(env.getDriver(), By.className(textBoxClearClassName)), "Test clear");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check value
    Assert.assertEquals(getValue(env.getDriver(), parent, By.className(textBoxClearClassName)), "Test clear");
  }

  public void testClear(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // clear the text
    clear(env.getDriver(), By.className(textBoxClearClassName));

    // check check the text is cleared
    Assert.assertEquals(getValue(env.getDriver(), By.className(textBox1ClassName)), "");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check check the text is cleared
    Assert.assertEquals(getValue(env.getDriver(), parent, By.className(textBox1ClassName)), "");
  }

  public void testAttributeValuePresent(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // check custom attribute name and value present on a DIV el
    Assert.assertTrue(attributeValuePresent(env.getDriver(), By.className(divWithCustomAttributeClassName), "qaID", "qaid"));

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // check custom attribute name and value present on a DIV el
    Assert.assertTrue(attributeValuePresent(env.getDriver(), parent, By.className(divWithCustomAttributeClassName), "qaID", "qaid"));
  }

  public void testGetAttributeValue(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // type a text.
    Assert.assertEquals(getAttributeValue(env.getDriver(), By.className(divWithCustomAttributeClassName), "qaID"), "qaid");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // type a text.
    Assert.assertEquals(getAttributeValue(env.getDriver(), parent, By.className(divWithCustomAttributeClassName), "qaID"), "qaid");
  }

  public void testGetSelectElement(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // Get Select el for the year drop down
    Select selEl = getSelectElement(env.getDriver(), By.className(selectClassName));

    // check the select option list size is 4
    Assert.assertEquals(selEl.getOptions().size(), 4);

    // check the selected text
    Assert.assertEquals(selEl.getFirstSelectedOption().getText(), "Volvo");

    // Select by index 1
    selEl.selectByIndex(3);

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // Get Select el for the year drop down
    selEl = getSelectElement(env.getDriver(), parent, By.className(selectClassName));

    // check the select option list size is 4
    Assert.assertEquals(selEl.getOptions().size(), 4);

    // check the selected text
    Assert.assertEquals(selEl.getFirstSelectedOption().getText(), "Volvo");

    // Select by index 1
    selEl.selectByIndex(3);
  }

  public void testHover(WTFEnv env) {
    // load the test page
    env.getDriver().get(CLICK_FILE);

    // hover on a button
    hover(env.getDriver(), By.className(btnHoverClassName));

    // verify the button value has changed
    Assert.assertEquals(getValue(env.getDriver(), By.className(btnHoverClassName)), "hoverd");

    // load the test page
    env.getDriver().get(CLICK_FILE);

    // find the sub DOM
    WebElement parent = env.getDriver().findElement(By.className(parentClassName));

    // hover on a button
    hover(env.getDriver(), parent, By.className(btnHoverClassName));

    // verify the button value has changed
    Assert.assertEquals(getValue(env.getDriver(), By.className(btnHoverClassName)), "hoverd");
  }
}
