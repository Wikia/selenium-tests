package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.api.GraphApi;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookSettingsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import org.testng.annotations.Test;

import java.util.HashMap;

@Test(groups = {"auth-facebook"})
public class FacebookTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String wikia_production_app_id = "112328095453510";
  String wikia_preview_app_id = "983287518357559";

  @Test(groups = "Facebook_userCanSignUpViaFacebook")
  public void userCanSignUpViaFacebook() {

    GraphApi api = new GraphApi();
    HashMap<String, String> test_user = api.createFacebookTestUser(wikia_production_app_id);

    FacebookSettingsPageObject settingsFB = new FacebookSettingsPageObject(driver).open();
    new FacebookMainPageObject(driver).login(test_user.get("email"), test_user.get("password"));
    SignUpPageObject signUp = new SignUpPageObject(driver).open();
    FacebookSignupModalComponentObject fbModal = signUp.clickFacebookSignUp();

    String userName = "QA" + signUp.getTimeStamp();
    String password = "Pass" + signUp.getTimeStamp();

    fbModal.createAccountNoEmail(test_user.get("email"), userName, password);
    signUp.verifyUserLoggedIn(userName);
    api.deleteFacebookTestUser(wikia_production_app_id, test_user.get("id"));

  }
  
}
