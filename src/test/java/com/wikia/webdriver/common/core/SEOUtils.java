package com.wikia.webdriver.common.core;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

/**
 * @ownership: Content X-Wing
 *
 *             This class serves to perform search engine optimization tests The class methods can
 *             be used to verify meta tags attributes
 */
public class SEOUtils extends BasePageObject {

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
  @FindBy(css = "meta[name='robots']")
  private WebElement robots;

  public SEOUtils(WebDriver driver) {
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

  public boolean isOgTitleWithWiki() throws WebDriverException {
    if (ogTitle.getAttribute("content") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return ogTitle.getAttribute("content").contains("Wiki");
  }

  public boolean isOgSiteName() throws WebDriverException {
    try {
      if (ogSiteName.getAttribute("content") == null) {
        throw new WebDriverException("Expected String but got null");
      }
      return !ogSiteName.getAttribute("content").isEmpty();
    } catch (NoSuchElementException e) {
      LOG.info("NO SUCH ELEMENT", e);
      return false;
    }
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

  public boolean isAttributesListPresentInRobotsMetaTag(List<String> expectedAttributes) {
    List<String> currentAttributes = Arrays.asList(robots.getAttribute("content").split("[, ]+"));

    LOG.result("Robots Meta Tag passed: " + expectedAttributes, "Robots Meta Tag found on page: "
        + currentAttributes, true);

    return currentAttributes.equals(expectedAttributes);
  }

  public boolean isRobotsMetaTagSet() {
    LOG.result("Robots Meta Tag", "Checking if robots meta tag is present on page", true);
    return isElementOnPage(robots);
  }
}
