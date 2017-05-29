package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by szymonczerwinski on 10.03.2017.
 */
public class Activity extends BasePageObject {

  By userLinkSelector = By.cssSelector("span.subtle > a");
  By titleSelector = By.cssSelector("a.title");

  WebElement userLink;
  WebElement titleLink;

  public Activity(WebDriver driver, WebElement parentElement) {
    super();

    userLink = parentElement.findElement(userLinkSelector);
    titleLink = parentElement.findElement(titleSelector);
  }

  public String getTitle() {
    return titleLink.getText();
  }

  public String getUser(){
    return userLink.getText();
  }

  public ArticlePageObject clickOnTitle() {
    titleLink.click();
    return new ArticlePageObject();
  }

  public UserProfilePageObject clickOnUserLink() {
    userLink.click();
    return new UserProfilePageObject(driver);
  }
}
