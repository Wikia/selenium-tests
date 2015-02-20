package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class OpenGraphPageObject extends MobileBasePageObject {

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

  public OpenGraphPageObject(WebDriver driver) {
    super(driver);
  }

  public String getDescription() {
    return ogDescription.getAttribute("content");
  }

  public boolean isOgTypeWebsite() {
    return ogType.getAttribute("content").contains("website");
  }

  public boolean isOgTypeArticle() {
    return ogType.getAttribute("content").contains("article");
  }

  public boolean isOgTitleMainPage() {
    return ogTitle.getAttribute("content").contains("Wiki");
  }

  public boolean isOgTitleArticlePage() {
    return !ogTitle.getAttribute("content").isEmpty();
  }

  public boolean isOgSiteName() {
    try {
      return !ogSiteName.getAttribute("content").isEmpty();
    } catch (NoSuchElementException e) {}
    return false;
  }

  public boolean isOgDescription() {
    return !ogDescription.getAttribute("content").isEmpty();
  }

  public boolean isOgUrlTag() {
    return ogUrl.getAttribute("content").equals(canonicalUrl.getAttribute("href"));
  }

  public boolean isOgImage() {
    return !ogImage.getAttribute("content").isEmpty();
  }

  public boolean isOgFbApp() {
    return !ogFbApp.getAttribute("content").isEmpty();
  }
}
