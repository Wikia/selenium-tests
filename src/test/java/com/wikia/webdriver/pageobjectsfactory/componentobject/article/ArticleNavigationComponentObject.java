package com.wikia.webdriver.pageobjectsfactory.componentobject.article;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Content X-Wing Wikia
 *
 * Represents the wikia navigation present on an article page
 */
public class ArticleNavigationComponentObject extends ArticlePageObject {

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
