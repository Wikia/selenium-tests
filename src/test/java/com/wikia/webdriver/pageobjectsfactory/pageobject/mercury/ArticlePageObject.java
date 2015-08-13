package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
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
  @FindBy(css = "nav.article-categories-list div")
  private WebElement categoryButton;
  @FindBy(css = "nav.article-categories-list li")
  private List<WebElement> categoryList;
  @FindBy(css = ".article-title")
  private WebElement articleTitle;
  @FindBy(css = ".article-content a")
  private List<WebElement> anchorsInContent;
  @FindBy(css = ".mstAvaImg")
  private WebElement userAvatar;


  public ArticlePageObject(WebDriver driver) {
    super(driver);
  }

  public void clickTopContributor(int index) {
    wait.forElementVisible(topContributorsLinks.get(0), 5, 500);
    scrollToElement(topContributorsLinks.get(index));
    topContributorsLinks.get(index).click();
  }

  public void clickCategoryButton() {
    wait.forElementVisible(categoryButton);
    categoryButton.click();
  }

  public void clickOnImage(int index) {
    singleImgLink.get(index).click();
  }

  public void clickOnAnchorInContent(int index) {
    wait.forElementVisible(anchorsInContent.get(index));
    anchorsInContent.get(index).click();
  }

  public void clickOnCategoryListElement(int index) {
    wait.forElementVisible(categoryList.get(index));
    categoryList.get(index).click();
  }

  public boolean isWikiaLogoVisible() {
    return isElementOnPage(wikiaLogo);
  }

  public boolean isSearchButtonVisible() {
    return isElementOnPage(searchButton);
  }

  public boolean isTopContributorsSectionVisible() {
    scrollToElement(topContributorsSection);
    return isElementOnPage(topContributorsSection);
  }

  public boolean isTopContributorsThumbVisible(int index) {
    return isElementOnPage(topContributorsThumbs.get(index));
  }

  public boolean isFooterLogoVisible() {
    wait.forElementVisible(footerLogo);
    return footerLogo.isDisplayed();
  }

  public boolean isElementInFooterVisible(String elementName, int index) {
    wait.forElementVisible(footerLinks.get(index));
    return footerLinks.get(index).getText().equals(elementName);
  }

  public boolean isUrlContainingUserPage() {
    wait.forElementVisible(userAvatar, 5, 500);
    return driver.getCurrentUrl().contains("/wiki/User:");
  }

  public boolean isUrlContainingCategoryPage() {
    return driver.getCurrentUrl().contains("/wiki/Category:");
  }

  public boolean isChevronCollapsed() throws WebDriverException {
    if (categoryButton.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return categoryButton.getAttribute("class").contains("collapsed");
  }

  public String getArticleTitle() {
    return articleTitle.getText();
  }
}
