package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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

  public AlmostTherePageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyAlmostTherePage() {
    waitForElementByElement(almostThereText);
    waitForElementByElement(sendAnotherMail);
    waitForElementByElement(changeMyEmail);
  }

  private String getActivationLinkFromMail(String email, String password, String language) {
    String mailSubject;
    if (language == "ja") {
      mailSubject = "メールアドレスの認証を行ってウィキアを始めよう！";
    } else {
      mailSubject = "Confirm your email and get started on Wikia!";
    }
    String www = MailFunctions.getActivationLinkFromEmailContent(
        MailFunctions.getFirstEmailContent(email, password, mailSubject));
    PageObjectLogging.log("getActivationLinkFromMail",
                          "activation link is visible in email content: " + www, true);
    return www;
  }

  public ConfirmationPageObject enterActivationLink(String email, String password, String wikiURL, String language) {
    getUrl(getActivationLinkFromMail(email, password, language));
    PageObjectLogging.log("enterActivationLink", "activation page is displayed", true, driver);
    return new ConfirmationPageObject(driver);
  }

  public ConfirmationPageObject enterActivationLink(String email, String password, String wikiURL) {
    ConfirmationPageObject confirmPage = enterActivationLink(email, password, wikiURL, "english");
    return confirmPage;
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
