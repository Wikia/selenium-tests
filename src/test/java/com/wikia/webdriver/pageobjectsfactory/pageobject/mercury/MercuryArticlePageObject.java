package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
  @FindBy(css = "ul.comments > li")
  private List<WebElement> commentsList;
  @FindBy(css = "ul.comments > li li")
  private List<WebElement> commentsReplies;
  @FindBy(css = "ul.comments > li ul")
  private List<WebElement> commentsRepliesList;
  @FindBy(css = "button.show-comments-btn.page-btn")
  private WebElement showCommentsButton;
  @FindBy(xpath = "//button[text()='Next page']")
  private WebElement nextCommentPageButton;
  @FindBy(xpath = "//button[text()='Previous page']")
  private WebElement previousCommentPageButton;
  @FindBy(css = "svg.chevron")
  private WebElement commentChevron;
  @FindBy(css = "li.article-comment")
  private List<WebElement> allComments;
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
    PageObjectLogging.log("verifyTopContributorsSectionIsVisible",
        "Top contributors section is visible", true);
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
    Assertion.assertFalse(currentUrl.equals(driver.getCurrentUrl()), "Redirection doesn't work");
  }

  public void verifyCommentsPerPage(int expectedNumber) {
    Assertion.assertNumber(expectedNumber, commentsList.size(), "Number of comments per page");
  }

  public void verifyNextAndPreviousPageAreVisible() {
    int numberOfComments =
        Integer.parseInt(showCommentsButton.getText().substring(0,
            showCommentsButton.getText().indexOf(" ")));
    Assertion.assertTrue(numberOfComments - commentsReplies.size() > 25,
        "There is less than 25 on that page");
    Assertion.assertTrue(nextCommentPageButton.isDisplayed(), "Next page button isn't displayed");
    nextCommentPageButton.click();
    Assertion.assertTrue(previousCommentPageButton.isDisplayed(),
        "Previous page button isn't displayed");
    previousCommentPageButton.click();
    Assertion.assertFalse(previousCommentPageButton.isDisplayed(),
        "Previous page button is displayed");
  }

  public void verifyRepliesCounterIsCorrect(int index) {
    int stringStart = showRepliesButtons.get(index).getText().indexOf(" ") + 1;
    int stringEnd = showRepliesButtons.get(index).getText().indexOf(" ", stringStart + 1);
    int numberOfReplies =
        Integer.parseInt(showRepliesButtons.get(index).getText().substring(stringStart, stringEnd));
    Assertion
        .assertTrue(
            numberOfReplies == commentsRepliesList.get(index).findElements(By.cssSelector("li"))
                .size(), "Replies counter doesn't work");
  }

  public void verifyTapOnUserRedirectToUserPage(int index) {
    String userName = commentsUsernames.get(index).getText();
    commentsUsernames.get(index).click();
    String subUrl =
        driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/") + 6,
            driver.getCurrentUrl().length());
    Assertion.assertTrue(subUrl.equals("User:" + userName), "Url doesn't contain user page");
  }

  public void verifyCommentsCounterIsCorrect() {
    int numberOfComments =
        Integer.parseInt(commentsHeader.getText().substring(0,
            commentsHeader.getText().indexOf(" ")));
    while (nextCommentPageButton.isDisplayed()) {
      numberOfComments -= allComments.size();
      nextCommentPageButton.click();
    }
    numberOfComments -= allComments.size();
    Assertion.assertTrue(numberOfComments == 0, "There are untracked comments");
  }

  public void verifyMediaInComments(String mediaType, int index) {
    WebElement mediaInComment;
    String methodName = "verifyMediaInComments";
    try {
      if (mediaType.equals("Video")) {
        mediaInComment = allComments.get(index).findElement(By.cssSelector("figure.comment-video"));
      } else {
        mediaInComment = allComments.get(index).findElement(By.cssSelector("figure"));
      }
      Assertion.assertTrue(mediaInComment.findElement(By.cssSelector("img")).isDisplayed(),
          mediaType + " thumbnail isn't displayed");
      Assertion.assertTrue(mediaInComment.findElement(By.cssSelector("a")).getAttribute("href")
          .contains("/wiki/File:"), mediaType + " anchor isn't displayed");
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, "There is no " + mediaType + " in that comment", false);
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  public void verifyChevronRotatesWhenTapped() {
    boolean collapsed = showCommentsButton.getAttribute("class").contains("collapsed");
    clickCommentsHeader();
    Assertion.assertFalse(
        collapsed == showCommentsButton.getAttribute("class").contains("collapsed"),
        "Chevron didn't turn");
  }

  public void verifyFooterElements() {
    String methodName = "verifyFooterElements";
    int elementCounter = 0;
    try {
      waitForElementByElement(footerLogo);
      Assertion.assertTrue(footerLogo.isDisplayed(), "Wikia logo isn't displayed");
      for (WebElement element : footerLinks) {
        Assertion.assertTrue(element.isDisplayed(), element.getText() + " isn't displayed");
        ++elementCounter;
      }
      Assertion.assertTrue(elementCounter == 11, "Some elements aren't displayed");
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, "Some elements are missing", false);
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  public void verifyTapContributorRedirectToUserPage(int index) {
    topContributorsLinks.get(index).click();
    String newUrl = driver.getCurrentUrl();
    Assertion.assertTrue(newUrl.contains("/wiki/User:"), "Redirection to user page doesn't work");
  }

  public void verifyChevronRotation() {
    String methodName = "verifyChevronRotation";
    try {
      waitForElementByElement(categoryButton);
      Assertion.assertTrue(categoryButton.getAttribute("class").contains("collapsed"),
          "Chevron isn't collapsed");
      categoryButton.click();
      Assertion.assertFalse(categoryButton.getAttribute("class").contains("collapsed"),
          "Chevron is collapsed");
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }
}
