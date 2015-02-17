package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Łukasz Nowak, Tomasz Napieralski
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

  public boolean isOgTypeWebsite() {
    if (ogType.getAttribute("content").contains("website")) {
      return true;
    }
    return false;
  }

  public boolean isOgTypeArticle() {
    if (ogType.getAttribute("content").contains("article")) {
      return true;
    }
    return false;
  }

  public boolean isOgTitleMainPage() {
    if (ogTitle.getAttribute("content").contains("Wiki")) {
      return true;
    }
    return false;
  }

  public boolean isOgTitleArticlePage() {
    if (ogTitle.getAttribute("content").isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean isOgSiteName() {
    try {
      if (ogSiteName.getAttribute("content").isEmpty()) {
        return false;
      }
      return true;
    } catch (NoSuchElementException e) {}
    return false;
  }

  public boolean isOgDescription() {
    if (ogDescription.getAttribute("content").isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean isOgUrlTag() {
    if (ogUrl.getAttribute("content").equals(canonicalUrl.getAttribute("href"))) {
      return true;
    }
    return false;
  }

  public boolean isOgImage() {
    if (ogImage.getAttribute("content").isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean isOgFbApp() {
    if (ogFbApp.getAttribute("content").isEmpty()) {
      return false;
    }
    return true;
  }
}
