package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.ActivityPageFactory;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.ActivityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SpecialWikiActivityPageObject extends SpecialPageObject {

  @FindBys(@FindBy(css = ".activityfeed > li"))
  private List<WebElement> activitiesList;

  private List<Activity> activities;

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
   * @param title title of new Wall Thread that is expected to be title of entry
   * @param threadAuthor name of user who posted new thread who is expected to be author of entry
   * @param threadContent content of new Wall Thread that is expected to be shown in entry
   */
  public boolean isNewWallThreadActivityDisplayed(String title, String threadAuthor, String threadContent) {
    return activities.stream()
      .filter(activity -> activity.getType() == ActivityType.WALL_POST)
      .filter(activity -> activity.getTitleLink().getText().contains(title))
      .filter(activity -> activity.getWallThreadAuthor().equals(threadAuthor))
      .anyMatch(activity -> activity.getWallThreadContent().contains(threadContent));
  }

  public boolean isNewBlogPostActivityDisplayed(String blogPostName, String userName, String blogPostContent) {
    return isActivityDisplayedWithType(ActivityType.NEW_PAGE, blogPostName, userName, blogPostContent);
  }

  public boolean isNewArticleActivityDisplayed(String articleName, String userName) {
    return isActivityDisplayedWithType(ActivityType.NEW_PAGE, articleName, userName);
  }

  public boolean isCategorizationActivityDisplayed(String articleName, String userName) {
    return isActivityDisplayedWithType(ActivityType.CATEGORIZATION, articleName, userName);
  }

  public boolean isArticleEditionActivityDisplayed(String articleName, String userName) {
    return isActivityDisplayedWithType(ActivityType.EDIT, articleName, userName);
  }

  public Activity getMostRecentArticleActivity() {
    return getMostRecentActivityOfType(ActivityType.NEW_PAGE);
  }

  public Activity getMostRecentEditActivity() {
    return getMostRecentActivityOfType(ActivityType.EDIT);
  }

  private boolean isActivityDisplayedWithType(ActivityType type, String title, String author) {
    return activities.stream()
      .filter(activity -> activity.getType() == type)
      .filter(activity -> activity.containsArticleName(title))
      .anyMatch(activity -> activity.containsAuthor(author));
  }

  private boolean isActivityDisplayedWithType(ActivityType type, String title, String author, String description) {
    return activities.stream()
      .filter(activity -> activity.getType() == type)
      .filter(activity -> activity.containsArticleName(title))
      .filter(activity -> activity.containsAuthor(author))
      .anyMatch(activity -> activity.containsDescription(description));
  }

  private Activity getMostRecentActivityOfType(ActivityType type) {
    return activities.stream()
      .filter(activity -> activity.getType() == type)
      .findFirst()
      .orElseThrow(() -> new RuntimeException(String.format("Could not find any activity of type: %s", type)));
  }

}
