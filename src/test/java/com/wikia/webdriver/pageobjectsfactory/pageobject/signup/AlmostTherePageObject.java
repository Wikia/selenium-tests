package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class AlmostTherePageObject extends WikiBasePageObject {

  @FindBy(xpath = "//h2[contains(text(), 'Almost there')]")
  private WebElement almostThereText;
  @FindBy(css = "input.link[value='Send me another confirmation email']")
  private WebElement sendAnotherMail;
  @FindBy(css = "a.change-email-link")
  private WebElement changeMyEmail;

  private String default_lang = "en";

  public AlmostTherePageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyAlmostTherePage() {
    wait.forElementVisible(almostThereText);
    wait.forElementVisible(sendAnotherMail);
    wait.forElementVisible(changeMyEmail);
  }

  private String getActivationLinkFromMail(String email, String password, String language) {
    String mailSubject;
    if ("ja".equals(language)) {
      mailSubject = "メールアドレスの認証を行ってウィキアを始めよう！";
    } else {
      mailSubject = "Confirm your email and get started on Wikia!";
    }
    String www = MailFunctions.getActivationLinkFromEmailContent(
        MailFunctions.getFirstEmailContent(email, password, mailSubject));
    LOG.log("getActivationLinkFromMail",
            "activation link is visible in email content: " + www, LOG.Type.SUCCESS);
    return www;
  }

  public ConfirmationPageObject enterActivationLink(String email, String password, String wikiURL, String language) {
    getUrl(getActivationLinkFromMail(email, password, language));
    LOG.log("enterActivationLink", "activation page is displayed", true, driver);
    return new ConfirmationPageObject(driver);
  }

  public ConfirmationPageObject enterActivationLink(String email, String password, String wikiURL) {
    return enterActivationLink(email, password, wikiURL, default_lang);
  }

  public void confirmAccountAndLogin(String email, String emailPassword, String userName, String password, String wikiURL) {
    verifyAlmostTherePage();
    ConfirmationPageObject confirmation = enterActivationLink(email, emailPassword, wikiURL);
    confirmation.typeInUserName(userName);
    confirmation.typeInPassword(password);
    UserProfilePageObject userProfile = confirmation.clickSubmitButton(email, emailPassword);
    userProfile.verifyUserLoggedIn(userName);
  }

}
