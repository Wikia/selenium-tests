package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MercuryArticlePageObject extends MercuryBasePageObject {

  @FindBy(css = ".wikia-logo")
  private WebElement wikiaLogo;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = ".contributors")
  private WebElement topContributorsSection;
  @FindBy(css = ".contributors img")
  private List<WebElement> topContributorsThumbs;
  @FindBy(css = "head link[rel='canonical']")
  private WebElement canonicalUrl;
  @FindBy(css = "figure.article-image a")
  private List<WebElement> singleImgLink;
  @FindBy(css = "svg.logo")
  private WebElement footerLogo;
  @FindBy(css = "ul.footer-links a")
  private List<WebElement> footerLinks;
  @FindBy(css = "div.contributors a")
  private List<WebElement> topContributorsLinks;
  @FindBy(css = "nav.article-categories-list button")
  private WebElement categoryButton;

  public static final String MEDIA_TYPE_VIDEO = "Video";
  public static final String MEDIA_TYPE_IMAGE = "Image";

  public MercuryArticlePageObject(WebDriver driver) {
    super(driver);
  }

  public void clickTopContributor(int index) {
    topContributorsLinks.get(index).click();
  }

  public void clickCategoryButton() {
    waitForElementByElement(categoryButton);
    categoryButton.click();
  }

  public boolean isWikiaLogoVisible() {
    if (checkIfElementOnPage(wikiaLogo)) {
      return true;
    }
    return false;
  }

  public boolean isSearchButtonVisible() {
    if (checkIfElementOnPage(searchButton)) {
      return true;
    }
    return false;
  }

  public boolean isTopContributorsSectionVisible() {
    scrollToElement(topContributorsSection);
    if (checkIfElementOnPage(topContributorsSection)) {
      return true;
    }
    return false;
  }

  public boolean isTopContributorsThumbVisible(int index) {
    if (checkIfElementOnPage(topContributorsThumbs.get(index))) {
      return true;
    }
    return false;
  }

  public boolean isUrlCanonical() {
    waitForElementInViewPort(canonicalUrl);
    if (driver.getCurrentUrl().equals(canonicalUrl.getAttribute("href"))) {
      return true;
    }
    return false;
  }

  public boolean isSingleLinkedImageRedirectionWorking(int index) {
    String currentUrl = driver.getCurrentUrl();
    singleImgLink.get(index).click();
    waitForElementByElement(footerLogo);
    if (currentUrl.equals(driver.getCurrentUrl())) {
      return false;
    }
    return true;
  }

  public boolean isFooterLogoVisible() {
    waitForElementByElement(footerLogo);
    if (footerLogo.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isElementInFooterVisible(String elementName, int index) {
    waitForElementByElement(footerLinks.get(index));
    if (footerLinks.get(index).getText().equals(elementName)) {
      return true;
    }
    return false;
  }

  public boolean isUrlContainingUserPage() {
    if (driver.getCurrentUrl().contains("/wiki/User:")) {
      return true;
    }
    return false;
  }

  public boolean isChevronCollapsed() {
    if (categoryButton.getAttribute("class").contains("collapsed")) {
      return true;
    }
    return false;
  }
}
