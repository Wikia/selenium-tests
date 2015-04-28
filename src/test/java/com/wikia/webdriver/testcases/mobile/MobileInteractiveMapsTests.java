package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author: Tomasz Napieralski
 */
public class MobileInteractiveMapsTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"MobileInteractiveMapsTests_001", "MobileInteractiveMapsTests", "Mobile"})
  public void MobileInteractiveMapsTests_001_MapVisibilityWhenUserLoggedOut() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.openHome(wikiURL);
    mobile.openArticleByName(wikiURL, "Special:Maps");
    List<WebElement> mapsList = driver.findElements(By.cssSelector(".map-list a"));
    mapsList.get(0).click();
    try{Thread.sleep(5000);}catch(InterruptedException e){}
    WebElement mapBox = driver.findElement(By.cssSelector(".wikia-interactive-map"));
    Assertion.assertTrue(mapBox.isDisplayed(), "Map isn't displayed");
  }

  @Test(groups = {"MobileInteractiveMapsTests_002", "MobileInteractiveMapsTests", "Mobile"})
  public void MobileInteractiveMapsTests_002_MapVisibilityWhenUserLoggedIn() {
    MobileBasePageObject mobile = new MobileBasePageObject(driver);
    mobile.loginDropDown(credentials.userName, credentials.password);
    mobile.openHome(wikiURL);
    mobile.openArticleByName(wikiURL, "Special:Maps");
    List<WebElement> mapsList = driver.findElements(By.cssSelector(".map-list a"));
    mapsList.get(0).click();
    try{Thread.sleep(5000);}catch(InterruptedException e){}
    WebElement mapBox = driver.findElement(By.cssSelector(".wikia-interactive-map"));
    Assertion.assertTrue(mapBox.isDisplayed(), "Map isn't displayed");
  }
}
