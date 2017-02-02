package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;

public class ArticlePage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation(driver);

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar(driver);

  private By articleContent = By.cssSelector(".article-content");
  private By categoriesDropdown = By.cssSelector(".article-footer .collapsible-menu");
  private By categoryLink = By.cssSelector(".article-footer .collapsible-menu li a");

  public CategoryPage openCategoryPageFromLink() {
    wait.forElementClickable(categoriesDropdown);
    driver.findElement(categoriesDropdown).click();

    wait.forElementClickable(categoryLink);
    driver.findElement(categoryLink).click();

    new Loading(driver).handleAsyncPageReload();

    return new CategoryPage();
  }

  public ArticlePage open(String pageName) {
    getNavigate().toPage(pageName);

    return this;
  }

  public ArticlePage open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
           + TestContext.getCurrentMethodName());

    return this;
  }

  public String getArticleContent() {
    return driver.findElement(articleContent).getText();
  }

  public ArticlePage openDiscussions() {
    return this.open("/d/");
  }
}
