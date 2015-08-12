package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SignupPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership Social
 */
@Test(groups = {"MercurySignupTests", "Mercury"})
public class SignupTests extends NewTestTemplate {

  private String regularUserName;
  private String regularPassword;
  private String regularEmail;
  private DateTime regularBirthday;

  @BeforeMethod(alwaysRun = true)
  public void beforeTest() {
    regularUserName = "User" + BasePageObject.getTimeStamp();
    regularPassword = "Pass" + BasePageObject.getTimeStamp();
    regularEmail = "qaart001+" + BasePageObject.getTimeStamp() + "@gmail.com";
    regularBirthday = new DateTime(1952, 12, 12, 12, 0, 0);
  }

  @Test(groups = "MercurySignupTest_001")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_001_successfulSignup() {
    signUp(regularUserName, regularPassword, regularEmail, regularBirthday)
        .verifyAvatarAfterSignup();
  }

  @Test(groups = "MercurySignupTest_002")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_002_signupErrorEmailInUse() {
    signUp(regularUserName, regularPassword, "qaart001@gmail.com", regularBirthday)
        .verifyEmailInUseError();
  }

  @Test(groups = "MercurySignupTest_003")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_003_signupErrorUsernameTaken() {
    signUp("bekcunning", regularPassword, regularEmail, regularBirthday)
        .verifyUsernameTakenError();
  }

  @Test(groups = "MercurySignupTest_004")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_004_signupErrorBadPassword() {
    signUp(regularUserName, regularUserName, regularEmail, regularBirthday)
        .verifyPasswordError();
  }

  @Test(groups = "MercurySignupTest_005")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_005_signupErrorTooYoungUser() {
    signUp(regularUserName, regularPassword, regularEmail, new DateTime(2009, 12, 12, 12, 0, 0))
        .verifyBirthdateError();
  }

  @Test(groups = "MercurySignupTest_006")
  @Execute(onWikia = "ja.ja-test")
  public void MercurySignupTest_006_japaneseUserSignup() {
    signUp("ユーザー" + BasePageObject.getTimeStamp(), "パス" + BasePageObject.getTimeStamp(),
           regularEmail, regularBirthday)
        .verifyAvatarAfterSignup();
  }

  private SignupPageObject signUp(String user, String password, String email, DateTime birthday) {
    return new SignupPageObject(driver)
        .openMobileSignupPage(wikiURL)
        .signUp(user, password, email, birthday);
  }
}
