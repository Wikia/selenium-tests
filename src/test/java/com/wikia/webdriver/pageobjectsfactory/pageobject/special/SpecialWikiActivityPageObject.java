package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.ActivityPageFactory;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.ActivityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SpecialWikiActivityPageObject extends SpecialPageObject {

  @FindBys(@FindBy(css = ".activityfeed > li"))
  private List<WebElement> activitiesList;

  @FindBys(@FindBy(css = "li.activity-type-edit"))
  private List<WebElement> editActivitiesList;

  @FindBys(@FindBy(css = "li.activity-type-new"))
  private List<WebElement> newPageActivitiesList;

  @FindBys(@FindBy(css = "li.activity-type-categorization"))
  private List<WebElement> categorizationActivitiesList;

  @FindBys(@FindBy(css = "li.activity-type-talk.activity-ns-1201"))
  private List<WebElement> wallPostActivitiesList;

  By entryTitleBy = By.cssSelector("strong a");
  By wallThreadAuthorBy = By.cssSelector(".wallfeed tr:first-child .real-name");
  By wallThreadMainContentBy = By.cssSelector(".wallfeed tr:first-child p:nth-child(2)");

  List<Activity> activities;

  public SpecialWikiActivityPageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName())
           + URLsContent.SPECIAL_WIKI_ACTIVITY);

    this.refreshPageAddingCacheBuster();
    this.activities = new ActivityPageFactory(activitiesList).getActivities();

    return this;
  }

  /**
   * SUS-1309: Verify title, content and author of newly submitted Wall thread is visible on Wiki Activity
   *
   * @see com.wikia.webdriver.testcases.messagewall.MessageWallTests
   * @param postTitle title of new Wall Thread that is expected to be title of entry
   * @param postContent content of new Wall Thread that is expected to be shown in entry
   * @param authorName name of user who posted new thread who is expected to be author of entry
   */
  public void verifyNewWallThreadEntry(String postTitle, String postContent, String authorName) {
    // To prevent race conditions (something may have created new wall message while we were switching to WA)
    // we check all entries on WA which belong to wall posts
    WebElement result =
        wallPostActivitiesList
            .stream()
            .filter(
                postEntry ->
                    postEntry.findElement(entryTitleBy).getText().equals(postTitle) &&
                    postEntry.findElement(wallThreadAuthorBy).getText().equals(authorName) &&
                    postEntry.findElement(wallThreadMainContentBy).getText().startsWith(postContent)
            )
            .findFirst()
            .orElse(null);

    Assertion.assertNotNull(result);
  }

  public boolean isNewBlogPostActivityDisplayed(String blogPostContent, String blogPostName, String userName) {
    return activities.stream()
      .filter(activity -> activity.getType() == ActivityType.NEW_PAGE)
      .filter(activity -> activity.containsArticleName(blogPostName))
      .filter(activity -> activity.containsAuthor(userName))
      .anyMatch(activity -> activity.containsDescription(blogPostContent));
  }

  public boolean isNewArticleActivityDisplayed(String articleName, String userName) {
    return isArticleActivityDisplayedWithType(ActivityType.NEW_PAGE, articleName, userName);
  }

  public boolean isCategorizationActivityDisplayed(String articleName, String userName) {
    return isArticleActivityDisplayedWithType(ActivityType.CATEGORIZATION, articleName, userName);
  }

  public boolean isArticleEditionActivityDisplayed(String articleName, String userName) {
    return isArticleActivityDisplayedWithType(ActivityType.EDIT, articleName, userName);
  }

  private boolean isArticleActivityDisplayedWithType(ActivityType type, String title, String author) {
    return activities.stream()
      .filter(activity -> activity.getType() == type)
      .filter(activity -> activity.containsArticleName(title))
      .anyMatch(activity -> activity.containsAuthor(author));
  }

  private Activity getMostRecentActivityOfType(ActivityType type) {
    return activities.stream()
      .filter(activity -> activity.getType() == type)
      .findFirst()
      .orElseThrow(() -> new RuntimeException(String.format("Could not find any activity of type: %s", type)));
  }

  public Activity getMostRecentArticleActivity() {
    return getMostRecentActivityOfType(ActivityType.NEW_PAGE);
  }

  public Activity getMostRecentEditActivity() {
    return getMostRecentActivityOfType(ActivityType.EDIT);
  }
}
