package com.wikia.webdriver.pageobjectsfactory.componentobject.article;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * wikia navigation present on an oasis article page
 */
public class ArticleNavigationComponentObject extends BasePageObject {

  @FindBy(css = "a[data-canonical='random']")
  private WebElement randomArticleButton;

  public ArticleNavigationComponentObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject clickRandomArticle() {
    waitAndClick(randomArticleButton);
    return new ArticlePageObject(driver);
  }
}
