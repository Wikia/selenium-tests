package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.logging.LOG;

public class SpecialWikiActivityPageObject extends SpecialPageObject {

  @FindBys(@FindBy(css = "li.activity-type-edit"))
  private List<WebElement> editActivitiesList;
  @FindBys(@FindBy(css = "li.activity-type-new"))
  private List<WebElement> newPageActivitiesList;
  @FindBys(@FindBy(css = "li.activity-type-categorization"))
  private List<WebElement> categorizationActivitiesList;

  public SpecialWikiActivityPageObject(WebDriver driver) {
    super(driver);
  }

  /**
   * verifies if there is the wanted edition on WikiActivity, searching through 5 recent editions.
   */
  public void verifyRecentEdition(String articleName, String userName) {
    Boolean ifPassed = false;
    for (int i = 0; i < 4; i++) {
      ifPassed = ifDetailsPresent(editActivitiesList.get(i), articleName, userName);
      if (ifPassed) {
        LOG.success("verifyRecentEdition",
            "WikiActivity module found recent edition that affected '" + articleName
                + "' and was done by user: " + userName);
        break;
      }
    }
    if (!ifPassed) {
      LOG.error("verifyRecentEdition", "edition on article  '" + articleName + "' by user: '"
          + userName + "', not found");
    }
  }

  /**
   * verifies if there is the wanted NewPage on WikiActivity, searching through 5 recent editions.
   */
  public void verifyRecentNewPage(String articleName, String userName) {
    Boolean ifPassed = false;
    for (int i = 0; i < 4; i++) {
      ifPassed = ifDetailsPresent(newPageActivitiesList.get(i), articleName, userName);
      if (ifPassed) {
        LOG.success("verifyRecentNewPage", "WikiActivity module found recent new page: '"
            + articleName + "' that was created by user: " + userName);
        break;
      }
    }
    if (!ifPassed) {
      LOG.error("verifyRecentNewPage", "new page:  '" + articleName + "' by user: '" + userName
          + "', not found");
    }

  }

  /**
   * verifies if there is the wanted new Blog on WikiActivity, searching through 5 recent editions.
   */
  public void verifyRecentNewBlogPage(String blogContent, String blogTitle, String userName) {
    Boolean ifPassed = false;
    for (int i = 0; i < 4; i++) {
      ifPassed =
          ifNewBlogDetailsPresent(newPageActivitiesList.get(i), blogContent, blogTitle, userName);
      if (ifPassed) {
        LOG.success("verifyRecentNewBlogPage",
            "WikiActivity module found recent new blog titled as: '" + blogTitle
                + "' that was created by user: " + userName + " and contains content: "
                + blogContent);
        break;
      }
    }
    if (!ifPassed) {
      LOG.error("verifyRecentNewBlogPage",
          "WikiActivity module didn't find recent new blog titled as: '" + blogTitle
              + "' that was created by user: " + userName + " and containted content: "
              + blogContent);
    }

  }

  /**
   * verifies if there is the wanted new Categorization on WikiActivity, searching through 5 recent
   * categorizations.
   */
  public void verifyRecentNewCategorization(String articleName, String userName) {
    Boolean ifPassed = false;
    for (int i = 0; i < 4; i++) {
      ifPassed = ifDetailsPresent(categorizationActivitiesList.get(i), articleName, userName);
      if (ifPassed) {
        LOG.success("verifyRecentNewCategorization",
            "WikiActivity module found recent new Categorization on artile: '" + articleName
                + "' that was done by user: " + userName);
        break;
      }
    }
    if (!ifPassed) {
      LOG.error("verifyRecentNewCategorization",
          "WikiActivity didn't find recent new Categorization on artile: '" + articleName
              + "' that was done by user: " + userName);
    }

  }

  private Boolean ifDetailsPresent(WebElement webElement, String articleName, String userName) {
    boolean condition1 =
        webElement.findElement(By.cssSelector("strong a")).getText().contains(articleName);
    boolean condition2 =
        webElement.findElement(By.cssSelector("span a")).getText().contains(userName);
    return (condition1 & condition2);
  }

  private Boolean ifNewBlogDetailsPresent(WebElement webElement, String blogContent,
      String blogTitle, String userName) {
    boolean condition1 =
        ifDetailsPresent(webElement, "blog:" + userName + "/" + blogTitle, userName);
    boolean condition2 =
        webElement.findElement(By.cssSelector("td em")).getText().contains("New blog");
    boolean condition3 =
        webElement.findElement(By.cssSelector("td:nth-of-type(2)")).getText().contains(blogContent);
    return (condition1 & condition2 & condition3);
  }

}
