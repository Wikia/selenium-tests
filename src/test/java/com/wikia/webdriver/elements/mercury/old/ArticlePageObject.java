package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ArticlePageObject {

  @FindBy(css = ".fandom-logo")
  private WebElement wikiaLogo;
  @FindBy(css = ".site-head-icon-search")
  private WebElement searchButton;
  @FindBy(css = ".contributors")
  private WebElement topContributorsSection;
  @FindBy(css = ".contributors img")
  private List<WebElement> topContributorsThumbs;
  @FindBy(css = "figure.article-image a")
  private List<WebElement> singleImgLink;
  @FindBy(css = "footer.wds-global-footer")
  private WebElement footer;
  @FindBy(css = ".wds-global-footer__header-logo")
  private WebElement footerLogo;
  @FindBy(css = ".contributors > ul > li > a")
  private List<WebElement> topContributorsLinks;
  @FindBy(css = ".wiki-page-header__title")
  private WebElement articleTitle;
  @FindBy(css = ".article-content p a")
  private List<WebElement> anchorsInContent;
  @FindBy(css = ".masthead-avatar")
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

  public void clickOnImage(int index) {
    singleImgLink.get(index).click();
  }

  public void clickOnAnchorInContent(int index) {
    wait.forElementClickable(anchorsInContent.get(index));
    anchorsInContent.get(index).click();
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

  public boolean isFooterVisible() {
    wait.forElementVisible(footer, 10, 500);

    return true;
  }

  public boolean isFooterLogoVisible() {
    wait.forElementVisible(footerLogo);

    return footerLogo.isDisplayed();
  }

  public boolean isUrlContainingUserPage() {
    wait.forElementVisible(userAvatar, 5, 500);

    return driver.getCurrentUrl().contains("/wiki/User:");
  }

  public String getArticleTitle() {
    wait.forElementVisible(articleTitle);

    return articleTitle.getText();
  }
}
