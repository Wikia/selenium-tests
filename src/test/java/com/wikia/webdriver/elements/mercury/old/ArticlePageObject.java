package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ArticlePageObject {

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
  @FindBy(css = ".contributors > ul > li > a")
  private List<WebElement> topContributorsLinks;
  @FindBy(xpath = "//nav/div[node()=\"Categories\"]")
  private WebElement categoryButton;
  @FindBy(xpath = "//nav/div[node()=\"Categories\"]/../ul/li")
  private List<WebElement> categoryList;
  @FindBy(css = ".wiki-page-title")
  private WebElement articleTitle;
  @FindBy(css = ".article-content a")
  private List<WebElement> anchorsInContent;
  @FindBy(css = ".mstAvaImg")
  private WebElement userAvatar;

  private Wait wait;
  private WebDriver driver;
  private JavascriptActions jsActions;

  public ArticlePageObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.driver = driver;
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public void clickTopContributor(int index) {
    wait.forElementVisible(topContributorsLinks.get(0), 5, 500);
    jsActions.scrollToElement(topContributorsLinks.get(index));
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
    wait.forElementVisible(wikiaLogo);

    return true;
  }

  public boolean isSearchButtonVisible() {
    wait.forElementVisible(searchButton);

    return true;
  }

  public boolean isTopContributorsSectionVisible() {
    jsActions.scrollToElement(topContributorsSection);
    wait.forElementVisible(topContributorsSection);

    return true;
  }

  public boolean isTopContributorsThumbVisible(int index) {
    wait.forElementVisible(topContributorsThumbs.get(index));

    return true;
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
    wait.forElementVisible(articleTitle);
    return articleTitle.getText();
  }

  public void waitForFooterToBeVisible() {
    wait.forElementVisible(footerLogo, 10, 500);
  }
}
