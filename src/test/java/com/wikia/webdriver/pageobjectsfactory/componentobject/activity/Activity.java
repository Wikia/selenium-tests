package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Activity extends BasePageObject {

  private WebElement userLink;
  private WebElement titleLink;

  public Activity(WebDriver driver, WebElement parentElement) {
    super();

    By userLinkSelector = By.cssSelector("span.subtle > a");
    By titleSelector = By.cssSelector("a.title");
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
    scrollAndClick(titleLink);
    return new ArticlePageObject();
  }

  public UserProfilePage clickOnUserLink() {
    scrollAndClick(userLink);
    return new UserProfilePage();
  }
}
