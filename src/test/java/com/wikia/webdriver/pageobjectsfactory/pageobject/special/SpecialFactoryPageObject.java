package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.contentpatterns.WikiFactoryVariablesProvider.WikiFactoryVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

public class SpecialFactoryPageObject extends SpecialPageObject {

  @FindBy(css = "a[href*=close]")
  private WebElement closeWikiButton;
  @FindBy(css = "#flag_1")
  private WebElement dumpCheckBox;
  @FindBy(css = "#flag_2")
  private WebElement imageArchiveCheckBox;
  @FindBy(css = "input[name='close_saveBtn']")
  private WebElement confirmCloseButton;
  @FindBy(css = "a.free")
  private WebElement closedWikiaLink;
  @FindBy(css = "select#wk-variable-select")
  private WebElement variableList;
  @FindBy(css = "div > div[style*=left] > pre")
  private WebElement variableValue;
  @FindBy(css = "div > div[style*=right] > pre")
  private WebElement defaultVariableValue;

  public SpecialFactoryPageObject(WebDriver driver) {
    super(driver);
  }

  private void clickCloseWikiButton() {
    scrollAndClick(closeWikiButton);
    LOG.success("clickCloseWikiButton ", "Close wiki button clicked", true);
  }

  private void deselectCreateDumpCheckBox() {
    scrollAndClick(dumpCheckBox);
    LOG.success("deselectCreateDumpCheckBox ", "Create dump checkbox deselected",true);
  }

  private void deselectImageArchiveCheckBox() {
    scrollAndClick(imageArchiveCheckBox);
    LOG.logResult("deselectImageArchiveCheckBox ", "Create image archive checkbox deselected",
                  true, driver);
  }

  private void confirmClose() {
    scrollAndClick(confirmCloseButton);
    LOG.success("confirmClose ", "Close confirmation button clicked", true);
  }

  private void clickClosedWikiaLink() {
    scrollAndClick(closedWikiaLink);
    LOG.success("clickClosedWikiaLink ", "Closed wikia link clicked", true);
  }

  private void verifyWikiaClosed() {
    wait.forElementVisible(By.cssSelector("#close-title"));
    wait.forElementVisible(By.cssSelector("#close-info"));
    LOG.success("verifyWikiaClosed ", "Closed wikia verified", true);
  }

  public void deleteWiki() {
    clickCloseWikiButton();
    deselectCreateDumpCheckBox();
    deselectImageArchiveCheckBox();
    confirmClose();
    confirmClose();
    clickClosedWikiaLink();
    verifyWikiaClosed();
  }

  public void verifyVariableValue(WikiFactoryVariables variableName, String expectedValue) {
    selectVariableByVisibleText(variableName);
    Assertion.assertEquals(variableValue.getText(), expectedValue);
  }

  private void selectVariableByVisibleText(WikiFactoryVariables variableName) {
    Select select = new Select(variableList);
    select.selectByVisibleText(variableName.toString());
  }

}
