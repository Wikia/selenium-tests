package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by lukasz on 2015-01-13.
 */
public class OpenGraphPageObject extends MobileBasePageObject {

  public OpenGraphPageObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "meta[property='og:type']")
  private WebElement ogType;
  @FindBy(css = "meta[property='og:title']")
  private WebElement ogTitle;
  @FindBy(css = "meta[property='og:url']")
  private WebElement ogUrl;
  @FindBy(css = "meta[property='fb:app_id']")
  private WebElement ogFbApp;
  @FindBy(css = "meta[property='og:description']")
  private WebElement ogDescription;
  @FindBy(css = "meta[property='og:image']")
  private WebElement ogImage;
  @FindBy(css = "meta[property='og:site_name']")
  private WebElement ogSiteName;
  @FindBy(css = "link[rel='canonical']")
  private WebElement canonicalUrl;

  public String getDescription() {
    return ogDescription.getAttribute("content");
  }

  public void verifyOgTypeWebsite() {
    Assertion.assertTrue(ogType.getAttribute("content").contains("website"));
    PageObjectLogging.log("verifyOgTypeWebsite", "og:type meta tag was set to website", true);
  }

  public void verifyOgTypeArticle() {
    Assertion.assertTrue(ogType.getAttribute("content").contains("article"));
    PageObjectLogging.log("verifyOgTypeArticle", "og:type meta tag was set to article", true);
  }

  public void verifyOgTitleMainPage() {
    Assertion.assertTrue(ogTitle.getAttribute("content").contains("Wiki"));
    PageObjectLogging
        .log("verifyOgTitleMainPage", "og:title meta tag on main wiki page was set correctly",
             true);
  }

  public void verifyOgTitleArticlePage() {
    Assertion.assertFalse(ogTitle.getAttribute("content").isEmpty());
    PageObjectLogging.log("verifyOgTitleArticlePage", "og:title meta tag was set correctly", true);
  }

  public void verifyOgSiteNameNotExists() {
    Assertion.assertFalse(checkIfElementOnPage(ogSiteName));
    PageObjectLogging.log("verifyOgSiteNameNotExists", "og:site_name was in DOM", true);
  }

  public void verifyOgSiteNameExists() {
    Assertion.assertFalse(ogSiteName.getAttribute("content").isEmpty());
    PageObjectLogging.log("verifyOgSiteNameExists",
                          "og:site_name was in DOM" + ogSiteName.getAttribute("content"), true);
  }

  public void verifyOgDescription() {
    Assertion.assertFalse(ogDescription.getAttribute("content").isEmpty());
    PageObjectLogging.log("verifyOgDescription", "og:description tag was set", true);
  }

  public void verifyOgDescriptionTagWasChanged(String descriptionOne, String descriptionTwo) {
    Assertion.assertFalse(descriptionOne.contains(descriptionTwo));
    PageObjectLogging.log("verifyOgDescriptionTagWasChanged",
                          "og:description tag was changed after redirection to article", true);
  }

  public void verifyOgUrlTag() {
    Assertion.assertTrue(ogUrl.getAttribute("content").equals(canonicalUrl.getAttribute("href")));
    PageObjectLogging
        .log("verifyUrlTag", "og:url tag was set and was the same like canonical url", true);
  }

  public void verifyOgImage() {
    Assertion.assertFalse(ogImage.getAttribute("content").isEmpty());
    PageObjectLogging.log("verifyOgImage", "og:image tag was on page and was set correctly", true);
  }

  public void verifyOgFbApp() {
    Assertion.assertFalse(ogFbApp.getAttribute("content").isEmpty());
    PageObjectLogging.log("verifyOgFbApp", "fb:app_id tag was set correctly", true);
  }
}
