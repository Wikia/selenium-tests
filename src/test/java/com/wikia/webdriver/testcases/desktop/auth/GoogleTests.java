package com.wikia.webdriver.testcases.desktop.auth;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.Execute;
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
import org.testng.annotations.Test;

import java.time.Instant;

@Test(groups = {"auth-google"})
@Execute(onWikia = "mediawiki119")
public class GoogleTests extends NewTestTemplate {

  private String email = XMLReader.getValue("ci.user.google.email");
  private String password = XMLReader.getValue("ci.user.google.password");
  private String frame = "iframe[class='google-button']";
  private String googleButtonLabel = "//html/body/main/a/span";
  private String googleButton = "//html/body/main/a";

  protected String connect = "CONNECT WITH GOOGLE";
  protected String disconnect = "DISCONNECT FROM GOOGLE";


  @Test
  public void googleButtonIsVisibleOnSignUpPage() {
    AttachedRegisterPage registerPage = new WikiBasePageObject().openUserSignUpPage(wikiURL);
    assertTrue(registerPage.isConnectWithGoogleButtonVisible());
  }

  @Test
  public void googleButtonIsVisibleOnLoginPage() {
    AttachedSignInPage signInPage = new WikiBasePageObject().openUserLoginPage(wikiURL);
    assertTrue(signInPage.isConnectWithGoogleButtonVisible());
  }

  @Test
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

  // User with existing Google Account wants to create a Fandom Account
  @Test
  public void userCanSignUpViaGoogle() {
    String userName = String.format("QA%s", Instant.now().getEpochSecond());
    String redirectUrl = driver.getCurrentUrl();

    new GoogleMainPage().login(email, password);

    AttachedRegisterPage signUp = new AttachedRegisterPage().open(redirectUrl);
    GoogleSignupModalComponent googleModal = signUp.clickGoogleSignUp();
    googleModal.createAccountNoEmailGoogle(userName, 1, 1, 1990);

    PreferencesPageObject prefsPage = new WikiBasePageObject()
        .openSpecialPreferencesPage(wikiURL)
        .selectTab(PreferencesPageObject.tabNames.CONNECTIONS);

    prefsPage.disconnectFromGoogleOnPreferencesPage();
    prefsPage.open().selectTab(PreferencesPageObject.tabNames.CONNECTIONS);
    driver.switchTo().frame(driver.findElement(By.cssSelector(frame)));
    assertEquals(driver.findElement(By.xpath(googleButtonLabel)).getText(), connect);
  }
}