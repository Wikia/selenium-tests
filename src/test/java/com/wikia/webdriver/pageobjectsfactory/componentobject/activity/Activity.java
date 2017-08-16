package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Activity extends BasePageObject {


  @FindBy(css = "span.subtle > a")
  private WebElement userLink;
  @FindBy(css = "a.title")
  private WebElement titleLink;

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
