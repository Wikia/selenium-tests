package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpecialFollowPageObject extends SpecialPageObject {

  @FindBy(css = "#wikiafollowedpages-special-heading-blogs span>a")
  private List<WebElement> blogList;
  @FindBy(css = "#wikiafollowedpages-special-heading-media span>a")
  private List<WebElement> imagesList;

  public SpecialFollowPageObject(WebDriver driver, String wikiURL) {
    super(driver);
    getUrl(wikiURL + URLsContent.SPECIAL_FOLLOW);
  }

  public void verifyFollowedArticle(String articleName) {
    wait.forElementVisible(By.xpath(
        "//ul[@id='wikiafollowedpages-special-heading-article']//a[contains(text(), '" + articleName
        + "')]"));
    LOG.log("verifyFollowedArticle",
            articleName + "is visible on followed list", LOG.Type.SUCCESS);
  }

  public void verifyFollowedImageVideo(String imageVideo) {
    boolean isPresent = false;
    for (WebElement elem : imagesList) {
      String title = elem.getAttribute("title");
      if (title.contains(imageVideo)) {
        isPresent = true;
      }
    }
    Assertion.assertTrue(isPresent, "image " + imageVideo
                                    + " is not present on the following list");
    LOG.log("verifyFollowedImageVideo",
            imageVideo + "is visible on followed list", LOG.Type.SUCCESS);
  }

  public void verifyFollowedBlog(String userName, String blogTitle) {
    boolean isPresent = false;
    for (WebElement elem : blogList) {
      String title = elem.getAttribute("title");
      if (title.contains(userName) && title.contains(blogTitle)) {
        isPresent = true;
      }
    }
    Assertion.assertTrue(isPresent, "blog " + blogTitle + " is not present on the following list");
    LOG.log("verifyFollowedBlog",
            userName + " blog is visible on followed list", LOG.Type.SUCCESS);
  }

  public void verifyFollowedBlogPost(String blogPostName) {
    wait.forElementVisible(By.xpath(
        "//ul[@id='wikiafollowedpages-special-heading-blogs']//a[contains(text(), '" + blogPostName
        + "')]"));
    LOG.log("verifyFollowedBlogPost",
            blogPostName + " blog post is visible on followed list", LOG.Type.SUCCESS);
  }
}
