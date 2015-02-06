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
    String methodName = "verifySingleLinkedImageRedirect";
    String currentUrl = driver.getCurrentUrl();
    singleImgLink.get(index).click();
    if (currentUrl.equals(driver.getCurrentUrl())) {
      PageObjectLogging.log(methodName, "Redirection doesn't work", false);
    } else {
      PageObjectLogging.log(methodName, "Redirection works", true);
    }
  }

  public void verify25CommentsPerPage() {
    String methodName = "verify25CommentsPerPage";
    if (commentsList.size() == 25) {
      PageObjectLogging.log(methodName, "There are 25 comments per comment page", true);
    } else {
      PageObjectLogging.log(methodName, "There aren't 25 comments per comment page", false);
    }
  }

  public void verifyNextAndPreviousPageAreVisible() {
    String methodName = "verifyNextAndPreviousPageAreVisible";
    int numberOfComments =
        Integer.parseInt(showCommentsButton.getText().substring(0,
            showCommentsButton.getText().indexOf(" ")));
    if (numberOfComments - commentsReplies.size() > 25) {
      if (nextCommentPageButton.isDisplayed()) {
        PageObjectLogging.log(methodName, "Next page button is displayed", true);
        nextCommentPageButton.click();
        if (previousCommentPageButton.isDisplayed()) {
          PageObjectLogging.log(methodName, "Previous page button is displayed", true);
          previousCommentPageButton.click();
          if (previousCommentPageButton.isDisplayed()) {
            PageObjectLogging.log(methodName, "Previous page button is displayed", false);
          } else {
            PageObjectLogging.log(methodName, "Previous page button isn't displayed", true);
          }
        } else {
          PageObjectLogging.log(methodName, "Previous page button isn't displayed", false);
        }
      } else {
        PageObjectLogging.log(methodName, "Next page button isn't displayed", false);
      }
    } else {
      PageObjectLogging.log(methodName, "There are less than 25 comments on that page", false);
    }
  }

  public void verifyRepliesCounterIsCorrect(int index) {
    String methodName = "verifyRepliesCounterIsCorrect";
    int stringStart = showRepliesButtons.get(index).getText().indexOf(" ") + 1;
    int stringEnd = showRepliesButtons.get(index).getText().indexOf(" ", stringStart + 1);
    int numberOfReplies =
        Integer.parseInt(showRepliesButtons.get(index).getText().substring(stringStart, stringEnd));
    if (numberOfReplies == commentsRepliesList.get(index).findElements(By.cssSelector("li")).size()) {
      PageObjectLogging.log(methodName, "Replies counter works", true);
    } else {
      PageObjectLogging.log(methodName, "Replies counter doesn't work", false);
    }
  }

  public void verifyTapOnUserRedirectToUserPage(int index) {
    String methodName = "verifyTapOnUserRedirectToUserPage";
    String userName = commentsUsernames.get(index).getText();
    commentsUsernames.get(index).click();
    String subUrl =
        driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/") + 6,
            driver.getCurrentUrl().length());
    if (subUrl.equals("User:" + userName)) {
      PageObjectLogging.log(methodName, "Redirect to user page works", true);
    } else {
      PageObjectLogging.log(methodName, "Redirect to user page doesn't work", false);
    }
  }

  public void verifyCommentsCounterIsCorrect() {
    String methodName = "verifyCommentsCounterIsCorrect";
    int numberOfComments =
        Integer.parseInt(commentsHeader.getText().substring(0,
            commentsHeader.getText().indexOf(" ")));
    while (nextCommentPageButton.isDisplayed()) {
      numberOfComments -= allComments.size();
      nextCommentPageButton.click();
    }
    numberOfComments -= allComments.size();
    if (numberOfComments == 0) {
      PageObjectLogging.log(methodName, "Comments counter works", true);
    } else {
      PageObjectLogging.log(methodName, "Comments counter doesn't work", false);
    }
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
      if (mediaInComment.findElement(By.cssSelector("img")).isDisplayed()) {
        PageObjectLogging.log(methodName, mediaType + " thumbnail is displayed", true);
        if (mediaInComment.findElement(By.cssSelector("a")).getAttribute("href")
            .contains("/wiki/File:")) {
          PageObjectLogging.log(methodName, mediaType + " anchor is displayed", true);
        } else {
          PageObjectLogging.log(methodName, mediaType + " anchor isn't displayed", false);
        }
      } else {
        PageObjectLogging.log(methodName, mediaType + " thumbnail isn't displayed", false);
      }
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, "There is no " + mediaType + " in that comment", false);
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  public void verifyChevronRotatesWhenTapped() {
    String methodName = "verifyChevronRotatesWhenTapped";
    boolean collapsed = showCommentsButton.getAttribute("class").contains("collapsed");
    clickCommentsHeader();
    if (collapsed != showCommentsButton.getAttribute("class").contains("collapsed")) {
      PageObjectLogging.log(methodName, "Chevron turned", true);
    } else {
      PageObjectLogging.log(methodName, "Chevron didn't turn", false);
    }
  }
  
  public void verifyFooterElements() {
    String methodName = "verifyFooterElements";
    int elementCounter = 0;
    try {
      waitForElementByElement(footerLogo);
      if (footerLogo.isDisplayed()) {
        PageObjectLogging.log(methodName, "Wikia logo is displayed", true);
      } else {
        PageObjectLogging.log(methodName, "Wikia logo isn't displayed", false);
      }
      for (WebElement element : footerLinks) {
        if (element.isDisplayed()) {
          PageObjectLogging.log(methodName, element.getText() + " is displayed", true);
        } else {
          PageObjectLogging.log(methodName, element.getText() + " isn't displayed", false);
        }
        ++elementCounter;
      }
      if (elementCounter == 11) {
        PageObjectLogging.log(methodName, "All elements are displayed", true);
      } else {
        PageObjectLogging.log(methodName, "Some elements aren't displayed", false);
      }
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, "Some elements are missing", false);
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }
}
