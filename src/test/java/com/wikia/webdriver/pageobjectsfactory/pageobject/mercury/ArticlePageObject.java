package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class ArticlePageObject extends BasePageObject {

  @FindBy(css = ".wikia-logo")
  private WebElement wikiaLogo;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = ".contributors")
  private WebElement topContributorsSection;
  @FindBy(css = ".contributors img")
  private List<WebElement> topContributorsThumbs;
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

  public ArticlePageObject(WebDriver driver) {
    super(driver);
  }

  public void clickTopContributor(int index) {
    topContributorsLinks.get(index).click();
  }

  public void clickCategoryButton() {
    waitForElementByElement(categoryButton);
    categoryButton.click();
  }

  public void clickOnImage(int index) {
    singleImgLink.get(index).click();
  }

  public boolean isWikiaLogoVisible() {
    return checkIfElementOnPage(wikiaLogo);
  }

  public boolean isSearchButtonVisible() {
    return checkIfElementOnPage(searchButton);
  }

  public boolean isTopContributorsSectionVisible() {
    scrollToElement(topContributorsSection);
    return checkIfElementOnPage(topContributorsSection);
  }

  public boolean isTopContributorsThumbVisible(int index) {
    return checkIfElementOnPage(topContributorsThumbs.get(index));
  }

  public boolean isFooterLogoVisible() {
    waitForElementByElement(footerLogo);
    return footerLogo.isDisplayed();
  }

  public boolean isElementInFooterVisible(String elementName, int index) {
    waitForElementByElement(footerLinks.get(index));
    return footerLinks.get(index).getText().equals(elementName);
  }

  public boolean isUrlContainingUserPage() {
    return driver.getCurrentUrl().contains("/wiki/User:");
  }

  public boolean isChevronCollapsed() throws WebDriverException {
    if (categoryButton.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return categoryButton.getAttribute("class").contains("collapsed");
  }
}
