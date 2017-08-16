package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import lombok.Getter;
import org.openqa.selenium.By;


public class Activity extends BasePageObject {

  @Getter
  private By userLink = By.cssSelector("span.subtle > a");
  @Getter
  private By titleLink = By.cssSelector("a.title");

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
