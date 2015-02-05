package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MercuryArticlePageObject extends MercuryBasePageObject {

  @FindBy(css = ".article-gallery img")
  private List<WebElement> galleryImagesArray;
  @FindBy(css = ".article-gallery")
  private List<WebElement> articleGalleryFigure;
  @FindBy(css = ".wikia-logo")
  private WebElement wikiaLogo;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = ".contributors")
  private WebElement topContributorsSection;
  @FindBy(css = ".contributors img")
  private List<WebElement> topContributorsThumbs;
  @FindBy(css = ".article-comments > button")
  private WebElement commentsHeader;
  @FindBy(css = ".avatar")
  private List<WebElement> commentsAvatars;
  @FindBy(css = ".username")
  private List<WebElement> commentsUsernames;
  @FindBy(css = ".timestamp")
  private List<WebElement> commentsTimeStamps;
  @FindBy(css = ".content p")
  private List<WebElement> commentsContent;
  @FindBy(css = ".show-reply-btn")
  private List<WebElement> showRepliesButtons;
  @FindBy(css = ".expanded > .article-comment > .content")
  private List<WebElement> repliesContent;
  @FindBy(css = "span[class='title collapsed']")
  private WebElement tocCollapsed;
  @FindBy(css = "span[class='title']")
  private WebElement tocUncollapsed;
  @FindBy(css = ".table-of-contents a")
  private List<WebElement> tocElements;
  @FindBy(css = "head link[rel='canonical']")
  private WebElement canonicalUrl;
  @FindBy(css = "figure.article-image a")
  private List<WebElement> singleImgLink;
  @FindBy(css = ".view-map")
  private WebElement viewMapButton;
  @FindBy(css = ".linked-gallery-image a")
  private WebElement linkedImage;

  public MercuryArticlePageObject(WebDriver driver) {
    super(driver);
  }

  public LightBoxMercuryComponentObject clickGalleryImage(int index) {
    scrollToElement(galleryImagesArray.get(index));
    waitForElementVisibleByElement(galleryImagesArray.get(index));
    tapOnElement(galleryImagesArray.get(index));
    PageObjectLogging.log("clickGalleryImage", "Image was clicked by test", true);
    return new LightBoxMercuryComponentObject(driver);
  }

  public void clickCommentsHeader() {
    waitForElementVisibleByElement(commentsHeader);
    scrollAndClick(commentsHeader);
    PageObjectLogging.log("clickCommentsHeader", "Comments header was clicked", true, driver);
  }

  public void clickViewReplies() {
    waitForElementVisibleByElement(showRepliesButtons.get(0));
    scrollAndClick(showRepliesButtons.get(0));
    PageObjectLogging.log("clickViewReplies", "View replies was clicked", true, driver);
  }

  public void clickLinkedImage() {
    waitForElementVisibleByElement(linkedImage);
    tapOnElement(linkedImage);
  }

  public String getLinkedImageHref() {
    waitForElementVisibleByElement(linkedImage);
    return linkedImage.getAttribute("href");
  }

  public SearchNavSideMenuComponentObject clickSearchButton() {
    waitForElementVisibleByElement(searchButton);
    searchButton.click();
    PageObjectLogging.log("clickSearchButton", "Search button was clicked", true, driver);
    return new SearchNavSideMenuComponentObject(driver);
  }

  public void clickTocCaption() {
    waitForElementVisibleByElement(tocCollapsed);
    scrollAndClick(tocCollapsed);
    PageObjectLogging.log("clickTocCaption", "Toc was clicked", true, driver);
  }

  public InteractiveMapsMercuryComponentObject clickViewMapButton() {
    waitForElementVisibleByElement(viewMapButton);
    viewMapButton.click();
    PageObjectLogging.log("clickMapButton", "Map button was clicked", true);
    return new InteractiveMapsMercuryComponentObject(driver);
  }

  public void verifyLinkedImageRedirection(String hrefUrl) {
    Assertion.assertTrue(driver.getCurrentUrl().toString().contains(hrefUrl));
  }

  public void verifyCommentsAreUncollapsed() {
    Assertion.assertFalse(commentsHeader.getAttribute("class").contains("collapsed"));
  }

  public void verifyWikiaLogoIsVisible() {
    Assertion.assertTrue(checkIfElementOnPage(wikiaLogo));
    PageObjectLogging.log("verifyWikiaLogoIsVisible", "Wikia logo was visible", true);
  }

  public void verifySearchButtonIsVisible() {
    Assertion.assertTrue(checkIfElementOnPage(searchButton));
    PageObjectLogging.log("verifySearchButtonIsVisible", "Search button was visible", true);
  }

  public void verifyTopContributorsSectionIsVisible() {
    scrollToElement(topContributorsSection);
    Assertion.assertTrue(checkIfElementOnPage(topContributorsSection));
    PageObjectLogging
        .log("verifyTopContributorsSectionIsVisible", "Top contributors section is visible", true);
  }

  public void verifyTopContributorsThumb() {
    Assertion.assertTrue(checkIfElementOnPage(topContributorsThumbs.get(0)));
    PageObjectLogging
        .log("verifyTopContributorsThumb", "Top contributors thumbs are visible", true);
  }

  public void verifyCommentsElements() {
    Assertion.assertTrue(checkIfElementOnPage(commentsAvatars.get(0)));
    Assertion.assertTrue(checkIfElementOnPage(commentsUsernames.get(0)));
    Assertion.assertTrue(checkIfElementOnPage(commentsTimeStamps.get(0)));
    Assertion.assertTrue(checkIfElementOnPage(commentsContent.get(0)));
    PageObjectLogging.log("verifyCommentsElements", "Comments elements were visible", true, driver);
  }

  public void verifyCanonicalUrl() {
    waitForElementInViewPort(canonicalUrl);
    String pageURL = driver.getCurrentUrl();
    Assertion.assertEquals(pageURL, canonicalUrl.getAttribute("href"));
  }

  public void verifyRepliesAreExpanded() {
    Assertion.assertTrue(checkIfElementOnPage(repliesContent.get(0)));
  }
  
  public void verifySingleLinkedImageRedirect(int index) {
    String currentUrl = driver.getCurrentUrl();
    singleImgLink.get(index).click();
    if (currentUrl.equals(driver.getCurrentUrl())) {
      PageObjectLogging.log("verifySingleLinkedImageRedirect", "Redirection doesn't work", false);
    } else {
      PageObjectLogging.log("verifySingleLinkedImageRedirect", "Redirection works", true);
    }
  }
}
