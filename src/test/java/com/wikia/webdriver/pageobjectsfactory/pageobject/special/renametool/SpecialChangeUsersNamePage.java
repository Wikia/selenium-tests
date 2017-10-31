package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialChangeUsersNamePage extends SpecialPageObject {
  @FindBy(css = "")
  private WebElement currentNameTextBox;
  @FindBy(css = "")
  private WebElement newUsernameTextBox;
  @FindBy(css = "")
  private WebElement reasonForRenameTextBox;
  @FindBy(css = "")
  private WebElement sendEmailCheckbox;
  @FindBy(css = "")
  private WebElement staffLogLink;

  public SpecialChangeUsersNamePage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public SpecialStaffLogPage openStaffLog() {
    wait.forElementVisible(staffLogLink);
    staffLogLink.click();
    return new SpecialStaffLogPage(driver);
  }

  public void renameUser(String currentUsername, String newUsername, String reason, boolean sendEmail) {
    wait.forElementClickable(currentNameTextBox);
    currentNameTextBox.sendKeys(currentUsername);
    newUsernameTextBox.sendKeys(newUsername);
    reasonForRenameTextBox.sendKeys(reason);
    if (sendEmail)
      sendEmailCheckbox.click();
  }
}
