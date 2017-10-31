package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialRenameUserPage extends SpecialPageObject {
  @FindBy(css = "input[name=\"newusername\"]")
  private WebElement newUsernameTextBox;
  @FindBy(css = "input[name=\"newusernamerepeat\"]")
  private WebElement confirmNewUsernameTextBox;
  @FindBy(css = "input[name=\"reason\"]")
  private WebElement reasonForRenameTextBox;
  @FindBy(css = "input[name=\"submit\"]")
  private WebElement submitButton;
  @FindBy(xpath = "//a[text()=\"Staff log\"")
  private WebElement staffLogLink;
  @FindBy(css = ".errorbox")
  private WebElement errorMessageTextBox;


  public SpecialRenameUserPage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public SpecialRenameUserPage open() {
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_RENAME_TOOL);
    return this;
  }

  public SpecialStaffLogPage openStaffLog() {
    wait.forElementVisible(staffLogLink);
    staffLogLink.click();
    return new SpecialStaffLogPage(driver);
  }

  public SpecialRenameUserPage fillFormData(String newUsername, String reason) {
    wait.forElementClickable(confirmNewUsernameTextBox);
    newUsernameTextBox.sendKeys(newUsername);
    confirmNewUsernameTextBox.sendKeys(newUsername);
    reasonForRenameTextBox.sendKeys(reason);
    return this;
  }

  public SpecialRenameUserPage submitChange() {
    submitButton.click();
    return this;
  }

  public String getErrorMessage() {
    return errorMessageTextBox.getText();
  }
}
