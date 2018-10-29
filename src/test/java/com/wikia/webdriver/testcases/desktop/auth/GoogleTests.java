package com.wikia.webdriver.testcases.desktop.auth;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.GoogleUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.GoogleSignupModalComponent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.google.GoogleMainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.time.Instant;

@Test(groups = {"auth-google"})
public class GoogleTests extends NewTestTemplate {

  private GoogleUser googleUser;
  private String email = XMLReader.getValue("ci.user.google.google1.email");
  private String password = XMLReader.getValue("ci.user.google.google1.password");
  private String email2 = XMLReader.getValue("ci.user.google.google2.email");
  private String password2 = XMLReader.getValue("ci.user.google.google2.password");

  public void googleButtonIsVisibleOnSignUpPage() {
    AttachedRegisterPage registerPage = new WikiBasePageObject().openUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithGoogleButtonVisible());
  }

  public void googleButtonIsVisibleOnLoginPage() {
    AttachedSignInPage signInPage = new WikiBasePageObject().openUserLoginPage(wikiURL);
    assertTrue(signInPage.isConnectWithGoogleButtonVisible());
  }

  public void googleButtonIsVisibleOnForcedLoginModal() {
    new WikiBasePageObject().openSpecialNewFiles(wikiURL).addPhoto();
    assertTrue(new DetachedRegisterPage().isConnectWithGoogleButtonVisible());
  }

  @Test
  @Execute(asUser = User.USER)
  public void googleButtonIsVisibleOnUserPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.CONNECTIONS);
    assertTrue(prefsPage.isGoogleButtonVisible());
  }

  @Test
  public void userCanSignUpViaGoogle() {
    String userName = String.format("QA%s", Instant.now().getEpochSecond());
    String redirectUrl = driver.getCurrentUrl();

    new GoogleMainPage().login(email2, password2);
    AttachedRegisterPage signUp = new AttachedRegisterPage().open(redirectUrl);
    GoogleSignupModalComponent googleModal = signUp.clickGoogleSignUp();
    googleModal.createAccountNoEmailGoogle(userName, 1,1,1990);

    disconnectFromGoogleOnPreferencesPage();

  }



  // Test to connect via Special:Preferences
  @Execute(asUser = User.USER)
  public void userCanConnectAccountWithGoogleOnPreferencesPage() {
    PreferencesPageObject prefsPage = new WikiBasePageObject().openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(PreferencesPageObject.tabNames.CONNECTIONS);

// TODO: FINISH! 1186
  }



  // methods for tests
  @FindBy(css = "a[class='connect-provider-google']")
  protected WebElement disconnectGoogle;


  protected String connect = "Connect with Google";
  protected String disconnect = "Disconnect from Google";

  public void disconnectFromGoogleOnPreferencesPage() {

    PreferencesPageObject prefsPage = new WikiBasePageObject()
        .openSpecialPreferencesPage(wikiURL)
        .selectTab(PreferencesPageObject.tabNames.CONNECTIONS);

   driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='google-button']")));

   assertEquals(prefsPage.googleConnect.getText(), disconnect);
   driver.findElement(By.xpath("//html/body/main/a")).click();

    assertEquals(prefsPage.googleConnect.getText(), connect);

    new GoogleMainPage().googleLogout();
  }
}