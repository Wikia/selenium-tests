package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSignupPageObject;
import org.testng.annotations.Test;

/**
 * Created by rcunningham on 6/30/15.
 */
public class MobileSignupTests extends NewTestTemplate{

    Credentials credentials = config.getCredentials();

    @Test(groups = {"MobileSignup_001", "MobileSignup", "Mobile"})
    public void successfulSignup() {

        MobileSignupPageObject mobileSignup = new MobileBasePageObject(driver).openMobileSignupPage(wikiURL);
        String userName = "User" + mobileSignup.getTimeStamp();
        String password = "Pass" + mobileSignup.getTimeStamp();
        String email = "qaart001+" + mobileSignup.getTimeStamp() + "@gmail.com";
        mobileSignup.typeEmailAddress(email).typeUsername(userName).typePassword(password).typeBirthdate("12", "12", "1952").register();
        mobileSignup.verifyAvatarAfterSignup();

    }
}
