package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
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

  public String getDescription() throws WebDriverException {
    if (ogDescription.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogDescription.getAttribute("content");
  }

  public boolean isOgTypeWebsite() throws WebDriverException {
    if (ogType.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogType.getAttribute("content").contains("website");
  }

  public boolean isOgTypeArticle() throws WebDriverException {
    if (ogType.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogType.getAttribute("content").contains("article");
  }

  public boolean isOgTitleMainPage() throws WebDriverException {
    if (ogTitle.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogTitle.getAttribute("content").contains("Wiki");
  }

  public boolean isOgTitleArticlePage() throws WebDriverException {
    if (ogTitle.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !ogTitle.getAttribute("content").isEmpty();
  }

  public boolean isOgSiteName() throws WebDriverException {
    if (ogSiteName.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    try {
      return !ogSiteName.getAttribute("content").isEmpty();
    } catch (NoSuchElementException e) {
    }
    return false;
  }

  public boolean isOgDescription() throws WebDriverException {
    if (ogDescription.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !ogDescription.getAttribute("content").isEmpty();
  }

  public boolean isOgUrlTag() throws WebDriverException {
    if (ogUrl.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogUrl.getAttribute("content").equals(canonicalUrl.getAttribute("href"));
  }

  public boolean isOgImage() throws WebDriverException {
    if (ogImage.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !ogImage.getAttribute("content").isEmpty();
  }

  public boolean isOgFbApp() throws WebDriverException {
    if (ogFbApp.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !ogFbApp.getAttribute("content").isEmpty();
  }
}
