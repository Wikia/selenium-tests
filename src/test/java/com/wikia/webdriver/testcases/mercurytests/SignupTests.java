package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSignupPageObject;
import org.testng.annotations.Test;

/**
 * Created by rcunningham on 6/30/15.
 */
@Test(groups = {"MobileSignup", "Mobile"})
public class SignupTests extends NewTestTemplate {

  @Test(groups = {"MobileSignup_001"})
  @Execute(onWikia = "mobileregressiontesting")
  public void successfulSignup() {

    MobileSignupPageObject mobileSignup =
        new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
        .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyAvatarAfterSignup();
  }

  @Test(groups = {"MobileSignup_002"})
  @Execute(onWikia = "mobileregressiontesting")
  public void signupErrorEmailInUse() {

    MobileSignupPageObject mobileSignup =
          new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyEmailInUseError();
  }

  @Test(groups = {"MobileSignup_003"})
  @Execute(onWikia = "mobileregressiontesting")
  public void signupErrorUsernameTaken() {

    MobileSignupPageObject mobileSignup =
          new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
    String userName = "bekcunning";
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyUsernameTakenError();
  }

  @Test(groups = {"MobileSignup_004"})
  @Execute(onWikia = "mobileregressiontesting")
  public void signupErrorBadPassword() {

    MobileSignupPageObject mobileSignup =
          new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = userName;
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "1952").register();
    mobileSignup.verifyPasswordError();
  }

  @Test(groups = {"MobileSignup_005"})
  @Execute(onWikia = "mobileregressiontesting")
  public void signupErrorTooYoungUser() {

    MobileSignupPageObject mobileSignup =
          new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
    String userName = "User" + mobileSignup.getTimeStamp();
    String password = "Pass" + mobileSignup.getTimeStamp();
    String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
    mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password)
          .typeBirthdate("12", "12", "2009").register();
    mobileSignup.verifyBirthdateError();
  }
}
