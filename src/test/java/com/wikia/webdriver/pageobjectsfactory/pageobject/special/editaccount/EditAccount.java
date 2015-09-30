package com.wikia.webdriver.pageobjectsfactory.pageobject.special.editaccount;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class EditAccount extends BasePageObject {

  private static final String USER_ACCOUNT_REOPEN_MESSAGE =
      "Successfully removed disabled bit for account";
  private static final String USER_ACCOUNT_CLOSED_MESSAGE = "Successfully disabled account";
  @FindBy(css = "[name=wpUserName]")
  private WebElement userNameField;
  @FindBy(css = "[value='Close account']")
  private WebElement closeAccountButton;
  @FindBy(css = "#wpReason")
  private WebElement closeResonField;
  @FindBy(css = "#wpActionSetPass")
  private WebElement newPasswordRadio;
  @FindBy(css = "[name=wpNewPass]")
  private WebElement newPasswordField;
  @FindBy(css = "[value='Clear disable flag']")
  private WebElement clearDisableFlagButton;
  @FindBy(css = "fieldset > span")
  private WebElement statusMessage;

  public EditAccount(WebDriver driver) {
    super(driver);
  }

  public EditAccount navigateToSpecialEditAccount(String communityWikiURL) {
    driver.get(communityWikiURL + URLsContent.SPECIAL_EDIT_ACCOUNT);

    return this;
  }

  public EditAccount goToAccountManagement(String userName) {
    userNameField.sendKeys(userName);
    userNameField.submit();
    LOG.log("editAccount", URLsContent.SPECIAL_EDIT_ACCOUNT + " page opened", LOG.Type.SUCCESS);

    return this;
  }

  public void closeAccount(String reason) {
    scrollAndClick(closeAccountButton);
    wait.forElementVisible(closeResonField);
    closeResonField.sendKeys(reason);
    closeResonField.submit();
    LOG.log("closeAccount", "account closed", LOG.Type.SUCCESS);
  }

  public void verifyAccountClosedMessage() {
    wait.forTextInElement(statusMessage, USER_ACCOUNT_CLOSED_MESSAGE);
    LOG.log("verifyAccountClosedMessage", "verified account closed", LOG.Type.SUCCESS);
  }

  public void reopenAccount(String newPassword) {
    newPasswordRadio.click();
    newPasswordField.sendKeys(newPassword);
    newPasswordField.submit();
    scrollAndClick(clearDisableFlagButton);
    LOG.log("reopenAccount", "account reopened", LOG.Type.SUCCESS);
  }

  public void verifyAccountReopenedMessage() {
    wait.forElementVisible(statusMessage);
    wait.forTextInElement(statusMessage, USER_ACCOUNT_REOPEN_MESSAGE);
    LOG.log("verifyAccountReopenedMessage", "verified account reopened", LOG.Type.SUCCESS);
  }
}
