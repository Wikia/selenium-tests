package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SignupPageObject;
import org.testng.annotations.Test;

/**
 * @ownership Social
 */
@Test(groups = {"MercurySignupTests", "Mercury"})
public class SignupTests extends NewTestTemplate {

  @Test(groups = {"MercurySignupTest_001"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_001_successfulSignup() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
        .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyAvatarAfterSignup();
  }

  @Test(groups = {"MercurySignupTest_002"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_002_signupErrorEmailInUse() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyEmailInUseError();
  }

  @Test(groups = {"MercurySignupTest_003"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_003_signupErrorUsernameTaken() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "bekcunning";
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyUsernameTakenError();
  }

  @Test(groups = {"MercurySignupTest_004"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_004_signupErrorBadPassword() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = userName;
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyPasswordError();
  }

  @Test(groups = {"MercurySignupTest_005"})
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_005_signupErrorTooYoungUser() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "2009").register();
    mobileSignup.verifyBirthdateError();
  }

  @Test(groups = {"MercurySignupTest_006"})
  @Execute(onWikia = "ja.ja-test")
  public void MercurySignupTest_006_japaneseUserSignup() {
    SignupPageObject mobileSignup = new SignupPageObject(driver);
    mobileSignup.openMobileSignupPage(wikiURL);
    String userName = "ユーザー" + mobileSignup.getTimeStamp();
    String password = "パス" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
            .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyAvatarAfterSignup();
  }
}
