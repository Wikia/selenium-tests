package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSignupPageObject;

import org.testng.annotations.Test;

/**
 * Created by rcunningham on 6/30/15.
 */
@Test(groups = {"MobileSignup", "Mobile"})
public class MobileSignupTests extends NewTestTemplate {

  @RelatedIssue(issueID = "QAART-635", comment = "Jenkins problem: Manual testing should be done. ASk Social team how to do it")
  @Test(groups = {"MobileSignup_001"})
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

  @RelatedIssue(issueID = "QAART-635", comment = "Jenkins problem: Manual testing should be done. ASk Social team how to do it")
  @Test(groups = {"MobileSignup_002"})
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

  @RelatedIssue(issueID = "QAART-635", comment = "Jenkins problem: Manual testing should be done. ASk Social team how to do it")
  @Test(groups = {"MobileSignup_003"})
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

  @RelatedIssue(issueID = "QAART-635", comment = "Jenkins problem: Manual testing should be done. ASk Social team how to do it")
  @Test(groups = {"MobileSignup_004"})
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

  @RelatedIssue(issueID = "QAART-635", comment = "Jenkins problem: Manual testing should be done. ASk Social team how to do it")
  @Test(groups = {"MobileSignup_005"})
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
