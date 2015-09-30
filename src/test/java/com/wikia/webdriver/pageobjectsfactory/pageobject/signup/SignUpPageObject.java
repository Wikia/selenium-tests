package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class SignUpPageObject extends WikiBasePageObject {

  @FindBy(css = "#WikiaSignupForm input[name='userloginext01']")
  private WebElement userNameField;
  @FindBy(css = "input[name='email']")
  private WebElement emailField;
  @FindBy(css = "#WikiaSignupForm input[name='userloginext02']")
  private WebElement passwordField;
  @FindBy(css = "select[name='birthmonth']")
  private WebElement birthMonthField;
  @FindBy(css = "select[name='birthday']")
  private WebElement birthDayField;
  @FindBy(css = "select[name='birthyear']")
  private WebElement birthYearField;
  @FindBy(css = "#g-recaptcha-response")
  private WebElement captchaField;
  @FindBy(css = "#wpCaptchaId")
  private WebElement blurryWordHidden;
  @FindBy(css = "input.big")
  private WebElement signupButton;
  @FindBy(css = "#mw-content-text .wikia-button-facebook")
  private WebElement facebookSignUpButton;

  private By errorMsgBy = By.className("error-msg");
  private By recaptchaResponseBy = By.cssSelector("#g-recaptcha-response");
  private By recaptchaErrorMsgBy = By.cssSelector(".captcha .error-msg");

  public SignUpPageObject(WebDriver driver) {
    super(driver);
  }

  private static String md5(InputStream is) {
    try {
      String output;
      MessageDigest digest = MessageDigest.getInstance("MD5");
      byte[] buffer = new byte[8192];
      int read = 0;
      try {
        while ((read = is.read(buffer)) > 0) {
          digest.update(buffer, 0, read);
        }
        byte[] md5sum = digest.digest();
        BigInteger bigInt = new BigInteger(1, md5sum);
        output = String.format("%0" + (md5sum.length << 1) + "x", bigInt);
      } finally {
        is.close();
      }
      return output;
    } catch (NoSuchAlgorithmException e) {
      LOG.log("md5", e.toString(), LOG.Type.ERROR);
      throw new WebDriverException(e);
    } catch (IOException e) {
      LOG.log("md5", e.toString(), LOG.Type.ERROR);
      throw new WebDriverException(e);
    }
  }

  public SignUpPageObject open() {
    driver.get(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_USER_SIGNUP);

    return this;
  }

  public void typeUserName(String userName) {
    userNameField.sendKeys(userName);
    driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
  }

  public void typeEmail(String email) {
    emailField.sendKeys(email);
    emailField.sendKeys(Keys.TAB);
    LOG.log("typeEmail", email + " typed into email field", LOG.Type.SUCCESS);
  }

  public void typePassword(String password) {
    passwordField.sendKeys(password);
    passwordField.sendKeys(Keys.TAB);
    LOG.log("typePassword", "password typed into password field", LOG.Type.SUCCESS);
  }

  public void verifyTooYoungMessage() {
    String message = birthYearField.findElement(parentBy).findElement(errorMsgBy).getText();
    Assertion.assertEquals(message, PageContent.SIGN_UP_TOO_YOUNG_MESSAGE);
  }

  public void enterBirthDate(String month, String day, String year) {
    try {
      new Select(birthMonthField).selectByVisibleText(month);
      Thread.sleep(150);
      new Select(birthDayField).selectByVisibleText(day);
      Thread.sleep(150);
      new Select(birthYearField).selectByVisibleText(year);
      Thread.sleep(150);
      new Select(birthDayField).selectByVisibleText(day);
      Thread.sleep(150);
      new Select(birthYearField).selectByVisibleText(year);
      Thread.sleep(150);
      new Select(birthMonthField).selectByVisibleText(month);
      LOG.log("enterBirthDate ", "Birth date: " + day + "/" + month + "/" + year
                                 + " selected", LOG.Type.SUCCESS);
    } catch (InterruptedException e) {
      LOG.log("enterBirthDate", e, LOG.Type.ERROR);
    }
  }

  public void typeCaptcha(File captchaFile) {
    typeCaptcha(getWordFromCaptcha(captchaFile));
  }

  public void typeCaptcha(String captchaWord) {
    captchaField.sendKeys(captchaWord);
    LOG.log("typeCaptcha ", "captcha typed into captcha field", LOG.Type.SUCCESS);
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementVisible(facebookSignUpButton);
    facebookSignUpButton.click();
    LOG.log("clickFacebookSignUp", "clicked on sign up with facebok button", LOG.Type.SUCCESS);
    return new FacebookSignupModalComponentObject(driver);
  }

  public AlmostTherePageObject submit(String email, String password) {
    MailFunctions.deleteAllEmails(email, password);
    return submit();
  }

  public AlmostTherePageObject submit() {
    scrollAndClick(signupButton);
    LOG.log("submit ", "sign up button clicked", LOG.Type.SUCCESS);
    return new AlmostTherePageObject(driver);
  }

  public void verifyCaptchaInvalidMessage() {
    wait.forElementPresent(recaptchaResponseBy);
    String message = driver.findElement(recaptchaErrorMsgBy).getText();
    Assertion.assertEquals(message, PageContent.SIGN_UP_INVALID_CAPTCHA_MESSAGE);
  }

  public void verifyUserExistsMessage() {
    String message = userNameField.findElement(parentBy).findElement(errorMsgBy).getText();
    Assertion.assertEquals(message, PageContent.SIGN_UP_USER_EXISTS_MESSAGE);
  }

  private String getWordFromCaptcha(File captchaFile) {
    try {
      String captchaId = blurryWordHidden.getAttribute("value");
      String urlAd =
          urlBuilder.getUrlForWiki(Configuration.getWikiName())
          + "wiki/Special:Captcha/image?wpCaptchaId=" + captchaId;
      URL url = new URL(urlAd);

      String md5 = md5(url.openStream());
      if (md5 == null) {
        throw new Exception("Provided captcha url doesn't exists");
      }

      BufferedReader in = new BufferedReader(new FileReader(captchaFile));
      String strLine;
      while ((strLine = in.readLine()) != null) {
        String[] field = strLine.split(" ");
        if (field[1].equals(md5)) {
          in.close();
          LOG.log("getWordFromCaptcha", "Captcha word decoded", LOG.Type.SUCCESS);
          return field[0];
        }
      }
      in.close();
      LOG.log("getWordFromCaptcha", "Captcha word not decoded", LOG.Type.ERROR);
      return null;
    } catch (IOException e) {
      throw new WebDriverException(e);
    } catch (Exception e) {
      throw new WebDriverException(e);
    }

  }

}
