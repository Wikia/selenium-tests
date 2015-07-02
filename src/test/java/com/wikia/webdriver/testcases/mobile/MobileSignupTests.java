package com.wikia.webdriver.testcases.mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSignupPageObject;

/**
 * Created by rcunningham on 6/30/15.
 */
@Test(groups = {"MobileSignup", "Mobile"})
public class MobileSignupTests extends NewTestTemplate {

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
}
