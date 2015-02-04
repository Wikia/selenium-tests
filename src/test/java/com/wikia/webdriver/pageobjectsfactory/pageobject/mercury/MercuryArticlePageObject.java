package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;

import org.openqa.selenium.By;
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
    if (currentUrl.equals(driver.getCurrentUrl())) {
      PageObjectLogging.log("verifySingleLinkedImageRedirect", "Redirection doesn't work", false);
    } else {
      PageObjectLogging.log("verifySingleLinkedImageRedirect", "Redirection works", true);
    }
  }

  public void verify25CommentsPerPage() {
    if (commentsList.size() == 25) {
      PageObjectLogging.log("verify25CommentsPerPage", "There are 25 comments per comment page",
          true);
    } else {
      PageObjectLogging.log("verify25CommentsPerPage", "There aren't 25 comments per comment page",
          false);
    }
  }

  public void verifyNextAndPreviousPageAreVisible() {
    int numberOfComments =
        Integer.parseInt(showCommentsButton.getText().substring(0,
            showCommentsButton.getText().indexOf(" ")));
    if (numberOfComments - commentsReplies.size() > 25) {
      if (nextCommentPageButton.isDisplayed()) {
        PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
            "Next page button is displayed", true);
        nextCommentPageButton.click();
        if (previousCommentPageButton.isDisplayed()) {
          PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
              "Previous page button is displayed", true);
          previousCommentPageButton.click();
          if (previousCommentPageButton.isDisplayed()) {
            PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
                "Previous page button is displayed", false);
          } else {
            PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
                "Previous page button isn't displayed", true);
          }
        } else {
          PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
              "Previous page button isn't displayed", false);
        }
      } else {
        PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
            "Next page button isn't displayed", false);
      }
    } else {
      PageObjectLogging.log("verifyNextAndPreviousPageAreVisible",
          "There are less than 25 comments on that page", false);
    }
  }

  public void verifyRepliesCounterIsCorrect(int index) {
    int stringStart = showRepliesButtons.get(index).getText().indexOf(" ") + 1;
    int stringEnd = showRepliesButtons.get(index).getText().indexOf(" ", stringStart + 1);
    int numberOfReplies =
        Integer.parseInt(showRepliesButtons.get(index).getText().substring(stringStart, stringEnd));
    if (numberOfReplies == commentsRepliesList.get(index).findElements(By.cssSelector("li")).size()) {
      PageObjectLogging.log("verifyRepliesCounterIsCorrect", "Replies counter works", true);
    } else {
      PageObjectLogging.log("verifyRepliesCounterIsCorrect", "Replies counter doesn't work", false);
    }
  }
  
  public void verifyTapOnUserRedirectToUserPage(int index) {
    String userName = commentsUsernames.get(index).getText();
    commentsUsernames.get(index).click();
    String subUrl = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/wiki/") + 6, driver.getCurrentUrl().length());
    if (subUrl.equals("User:" + userName)) {
      PageObjectLogging.log("verifyTapOnUserRedirectToUserPage", "Redirect to user page works", true);
    } else {
      PageObjectLogging.log("verifyTapOnUserRedirectToUserPage", "Redirect to user page doesn't work", false);
    }
  }
  
  public void verifyCommentsCounterIsCorrect() {
    int numberOfComments =
        Integer.parseInt(commentsHeader.getText().substring(0, commentsHeader.getText().indexOf(" ")));
    while (nextCommentPageButton.isDisplayed()) {
      numberOfComments -= allComments.size();
      nextCommentPageButton.click();
    }
    numberOfComments -= allComments.size();
    if (numberOfComments == 0) {
      PageObjectLogging.log("verifyCommentsCounterIsCorrect", "Comments counter works", true);
    } else {
      PageObjectLogging.log("verifyCommentsCounterIsCorrect", "Comments counter doesn't work", false);
    }
  }
  
  public void verifyImagesAndVideosAreDisplayed() {
    WebElement videoComment = allComments.get(1).findElement(By.cssSelector("figure.comment-video"));
    if (videoComment.isDisplayed()) {
      if (videoComment.findElement(By.cssSelector("img")).isDisplayed()) {
        PageObjectLogging.log("verifyImagesAndVideosAreDisplayed", "Video thumbnail is displayed", true);
        if (videoComment.findElement(By.cssSelector("a")).getAttribute("href").contains("/wiki/File:")) {
          PageObjectLogging.log("verifyImagesAndVideosAreDisplayed", "Video anchor is displayed", true);
        } else {
          PageObjectLogging.log("verifyImagesAndVideosAreDisplayed", "Video anchor isn't displayed", false);
        }
      } else {
        PageObjectLogging.log("verifyImagesAndVideosAreDisplayed", "Video thumbnail isn't displayed", false);
      }
    } else {
      PageObjectLogging.log("verifyImagesAndVideosAreDisplayed", "There is no video in that comment", false);
    }
    //image extract method 
    
  }
  
  public void verifyChevronRotatesWhenTapped() {
    System.out.println(commentChevron.getCssValue("transform"));
  }
}
