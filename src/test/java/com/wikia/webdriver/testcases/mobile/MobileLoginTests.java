package com.wikia.webdriver.testcases.mobile;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSpecialUserLogin;

/**
 * @author PMG
 *         <p/>
 *         Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE
 *         browser 1. Verify that user is able to login successfully using standard login 2. Verify
 *         that user is not logged in when he is using correct login and incorrect password 3.
 *         Verify that user is not logged in when he is using incorrect login and incorrect password
 *         3. Verify that user is not logged in when he is using correct login and empty password 4.
 *         Verify that user is able to login successfully using FB login
 */
public class MobileLoginTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void logIn() {
    new MobileBasePageObject(driver).loginDropDown(credentials.userName, credentials.password);
  }

  @Test(groups = {"MobileLogin_001", "MobileLogin", "Mobile"})
  public void MobileLogin_001_successLoginDropDown() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openRandomPage();
    String url = driver.getCurrentUrl();
    mobile.verifyURLcontains(url);
  }

  @Test(groups = {"MobileLogin_002", "MobileLogin", "Mobile"})
  public void MobileLogin_002_failedLoginWrongPassword() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openRandomPage();
    mobile.logOutMobile(wikiURL);
    MobileSpecialUserLogin login =
        mobile.loginFailedDropDown(credentials.userName12, mobile.getTimeStamp());
    login.verifyWrongPasswordErrorMessage();
  }

  @Test(groups = {"MobileLogin_003", "MobileLogin", "Mobile"})
  public void MobileLogin_003_failedLoginWrongLogin() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openRandomPage();
    mobile.logOutMobile(wikiURL);
    MobileSpecialUserLogin login =
        mobile.loginFailedDropDown(mobile.getTimeStamp(), mobile.getTimeStamp());
    login.verifyWrongLoginErrorMessage();
  }

  @Test(groups = {"MobileLogin_004", "MobileLogin", "Mobile"}, enabled = false)
  public void MobileLogin_004_failedLoginEmptyPassword() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openRandomPage();
    mobile.logOutMobile(wikiURL);
    MobileSpecialUserLogin login = mobile.loginFailedDropDown(credentials.userName12, "");
    login.verifyEmptyPasswordErrorMessage();
  }

  @Test(groups = {"MobileLogin_005", "MobileLogin", "Mobile"}, enabled = false)
  public void MobileLogin_005_facebookLogin() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openRandomPage();
    mobile.clickLoginFBButton();
    mobile.verifyFBLogin();
  }
}
