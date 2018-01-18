package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URLEncoder;

public class SpecialRenameUserPage extends SpecialPageObject {

  @FindBy(css = "input[name=\"newUsername\"]")
  private WebElement newUsernameTextBox;
  @FindBy(css = "input[name=\"newUsernameRepeat\"]")
  private WebElement confirmNewUsernameTextBox;
  @FindBy(css = "input[name=\"submitbutton\"]")
  private WebElement submitButton;
  @FindBy(css = ".errorbox")
  private WebElement errorMessageTextBox;
  @FindBy(css = ".extiw")
  private WebElement helpLink;
  @FindBy(css = "#password")
  private WebElement currentPasswordTextBox;
  @FindBy(css = "#understand-consequences")
  private WebElement termsAndConditionsCheckBox;
  @FindBy(css = ".successbox")
  private WebElement successBox;
  @FindBy(css = "#mw-content-text")
  private WebElement renamedMessage;

  public SpecialRenameUserPage open() {
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_RENAME_TOOL);
    return this;
  }

  public String encodeToURL(String toEncode) {
    String encodedString = "";

    try {
      encodedString = URLEncoder.encode(toEncode, "UTF-8").replace("+", "_");
    } catch (Exception e) {
      PageObjectLogging.logInfo("Could not encode URLs");
    }

    return encodedString;
  }

  public SpecialRenameUserPage fillFormData(String newUsername, String confirmUsername, String
      password) {
    wait.forElementClickable(confirmNewUsernameTextBox);
    newUsernameTextBox.sendKeys(newUsername);
    confirmNewUsernameTextBox.sendKeys(confirmUsername);
    currentPasswordTextBox.sendKeys(password);
    return this;
  }

  public SpecialRenameUserPage submitChange() {
    jsActions.scrollToElement(submitButton);
    submitButton.click();
    return this;
  }

  public String getSuccessBoxMessage() {
    return successBox.getText();
  }

  public String getErrorMessage() {
    return errorMessageTextBox.getText();
  }

  public String getPageText() {
    return renamedMessage.getText();
  }

  public HelpPage goToHelpPage() {
    helpLink.click();

    return new HelpPage(driver);
  }

  public SpecialRenameUserPage agreeToTermsAndConditions() {
    termsAndConditionsCheckBox.click();

    return this;
  }
}
