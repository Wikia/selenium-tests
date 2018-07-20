package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage extends ArticlePageObject {

  private static final String BLOG_PAGE_FORMAT = "User_blog:%s/%s";
  @FindBy(css = ".page-header__title")
  private WebElement blogHeader;

  public BlogPage open(String userName, String postName) {
    getUrl(urlBuilder.getUrlForWikiPage(String.format(BLOG_PAGE_FORMAT, userName, postName)));

    return this;
  }

  public WatchPageObject unfollowBlogPage() {
    String url = urlBuilder.appendQueryStringToURL(getCurrentUrl(), "action=unwatch");
    getUrl(url);
    return new WatchPageObject();
  }

  public String getBlogTitle() {
    wait.forElementVisible(blogHeader);

    return blogHeader.getText();
  }

  public String getBlogName() {
    return blogHeader.getText();
  }
}
