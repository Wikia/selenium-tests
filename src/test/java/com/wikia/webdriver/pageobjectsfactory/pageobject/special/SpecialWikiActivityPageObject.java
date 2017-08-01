package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.ActivityPageFactory;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialWikiActivityPageObject extends SpecialPageObject {

  @FindBys(@FindBy(css = "li[class*=\"activity-type\"]"))
  private List<WebElement> activityWebElementList;
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

  public SpecialWikiActivityPageObject(WebDriver driver) {
    super();
  }

  public SpecialWikiActivityPageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName())
           + URLsContent.SPECIAL_WIKI_ACTIVITY);

    this.refreshPageAddingCacheBuster();
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

  public Boolean doesLastNRecentEditionsContain(int n, String articleName, String userName) {
    Boolean ifPassed = false;

    if(n < editActivitiesList.size()){
      System.out.println(n);
      System.out.println(editActivitiesList.size());
      n = editActivitiesList.size();
    }

    for (int i = 0; i < n; i++) {
      ifPassed = ifDetailsPresent(editActivitiesList.get(i), articleName, userName);
      if (ifPassed == true) {
        break;
      }
    }

    return ifPassed;
  }

  public Boolean doesLastNRecentActivitiesContain(int n, String articleName, String userName) {

    if(n < editActivitiesList.size()){
      n = editActivitiesList.size();
    }

    Boolean ifPassed = false;
    for (int i = 0; i < n; i++) {
      ifPassed = ifDetailsPresent(activitiesList.get(i), articleName, userName);
      if (ifPassed == true) {
        break;
      }
    }

    return ifPassed;
  }

  public Boolean doesLastNRecentBlogActivitiesContain(int n, String blogPostContent, String blogPostName,
                                                      String userName) {

    if(n < editActivitiesList.size()){
      n = editActivitiesList.size();
    }

    Boolean ifPassed = false;
    for (int i = 0; i < n; i++) {
      ifPassed = ifNewBlogDetailsPresent(activitiesList.get(i), blogPostContent, blogPostName, userName);
      if (ifPassed == true) {
        break;
      }
    }

    return ifPassed;
  }

  /**
   * Gets activities from special page, limited by provided value
   * @param numberOfActivities number of activities to fetch (counting from top)
   * @return list of activities
   */
  public List<Activity> getActivities(int numberOfActivities) {

    List<Activity> activityList = new ArrayList<>();
    //numberOfActivitiesToFetch set to max number of possible elements to fetch
    int numberOfActivitiesToFetch;
    if (!activityWebElementList.isEmpty()){
      numberOfActivitiesToFetch = numberOfActivities > activityWebElementList.size()
              ? activityWebElementList.size() : numberOfActivities;
    } else{
      throw new NoSuchElementException("No activities found, populate a list before fetching.");
    }
    List<WebElement> activityWebElementSubList = activityWebElementList.subList(0, numberOfActivitiesToFetch);

    for (int i = 0; i < numberOfActivitiesToFetch - 1; i++) {
      Activity activity;
      List<ActivityPageCreator> creators = Arrays.asList(
              new EditActivityPageCreator(),
              new NewActivityPageCreator(),
              new CategorizationActivityPageCreator(),
              new WallPostActivityPageCreator()
      );
      activity = new ActivityPageFactory(driver, activityWebElementSubList.get(i), creators).createActivityPage();

      activityList.add(activity);
    }
    return activityList;
  }

  private Boolean ifDetailsPresent(WebElement webElement, String articleName,
                                   String userName) {
    boolean condition1 = webElement.findElement(By.cssSelector("strong a"))
        .getText().replaceAll("_", " ").contains(articleName);
    boolean condition2 = webElement.findElement(By.cssSelector("cite span a"))
        .getText().contains(userName);
    return (condition1 & condition2);
  }

  private Boolean ifNewBlogDetailsPresent(WebElement webElement,
                                          String blogContent, String blogTitle, String userName) {
    boolean condition1 = ifDetailsPresent(webElement, "User blog:" + userName
                                                      + "/" + blogTitle, userName);
    boolean condition2 = webElement.findElement(By.cssSelector("td em"))
        .getText().contains("New blog");
    boolean condition3 = webElement
        .findElement(By.cssSelector("td:nth-of-type(2)")).getText()
        .contains(blogContent);
    return (condition1 & condition2 & condition3);
  }
}
