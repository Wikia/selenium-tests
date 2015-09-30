package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class BlogListPageObject extends BasePageObject {

  @FindBy(xpath = "//h2[contains(text(), 'Blog posts')]")
  private WebElement blogListHeader;
  @FindBy(css = "a[data-id='createblogpost']")
  private WebElement createBlogPostButton;
  @FindBy(css = "#WikiaBlogListingPost")
  private List<WebElement> blogList;


  public BlogListPageObject(WebDriver driver) {
    super(driver);
  }

  public BlogListPageObject verifyBlogListPage(String listName) {
    verifyURL(urlBuilder.getUrlForWiki(Configuration.getWikiName())
        + URLsContent.BLOG_LIST.replace("%listName%", listName));
    wait.forElementVisible(blogListHeader);
    wait.forElementVisible(createBlogPostButton);
    return this;
  }

  public BlogListPageObject verifyBlogList() {
    for (WebElement elem : blogList) {
      // header with link to blog post
      elem.findElement(By.cssSelector("h1 a[href*='/wiki/User_blog:']"));
      // comments bubble
      elem.findElement(By.cssSelector(".comments"));
    }
    return this;
  }

  public BlogListPageObject followBlogListingPage(String name) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "wiki/Blog:" + name
        + "?action=watch");
    scrollAndClick(followSubmit);
    wait.forElementVisible(followedButton);
    return this;
  }
}
