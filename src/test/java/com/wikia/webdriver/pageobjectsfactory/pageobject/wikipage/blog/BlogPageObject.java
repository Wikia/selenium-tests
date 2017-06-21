package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPageObject extends ArticlePageObject {

  @FindBy(css = ".page-header__title")
  private WebElement blogHeader;

  By image = By.cssSelector("img");
  By firstSpan = By.cssSelector("span:nth-child(2) a");
  By secondSpan = By.cssSelector("span:nth-child(3)");
  By thirdSpan = By.cssSelector("span:nth-child(4) a");

  public BlogPageObject(WebDriver driver) {
    super();
  }

  public WatchPageObject unfollowBlogPage() {
    String url = urlBuilder.appendQueryStringToURL(getCurrentUrl(), "action=unwatch");
    getUrl(url);
    return new WatchPageObject(driver);
  }

  public String getBlogTitle() {
    wait.forElementVisible(blogHeader);

    return blogHeader.getText();
  }

  public String getBlogName() {
    return blogHeader.getText();
  }
}
