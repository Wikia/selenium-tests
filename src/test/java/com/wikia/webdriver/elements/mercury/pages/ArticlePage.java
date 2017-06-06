package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.old.LightboxComponentObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class ArticlePage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation(driver);

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar();

  @Getter(lazy = true)
  private final CuratedMainPagePageObject curatedMainPage = new CuratedMainPagePageObject();

  @Getter(lazy = true)
  private final LightboxComponentObject lightbox = new LightboxComponentObject();

  @FindBy(css = "article a")
  private List<WebElement> linksList;

  private final By articleContent = By.cssSelector(".article-content");
  private final By categoriesDropdown = By.cssSelector(".article-footer .collapsible-menu");
  private final By categoryLink = By.cssSelector(".article-footer .collapsible-menu li a");

  public CategoryPage openCategoryPageFromCategoriesDropdown() {
    wait.forElementClickable(categoriesDropdown);
    driver.findElement(categoriesDropdown).click();

    wait.forElementClickable(categoryLink);
    driver.findElement(categoryLink).click();

    new Loading(driver).handleAsyncPageReload();

    return new CategoryPage();
  }

  public ArticlePage open(String pageName) {
    getNavigate().toPage(pageName);

    new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI);

    return this;
  }

  public ArticlePage open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
           + TestContext.getCurrentMethodName());

    new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI);

    return this;
  }

  public String getArticleContent() {

    return driver.findElement(articleContent).getText();
  }

  public ArticlePage clickArticleLink(int index) {
    linksList.get(index).click();

    wait.forUrlContains(linksList.get(index).getAttribute("href"));

    return this;
  }

  public Long scrollToLink(int index, int offset) {
    jsActions.scrollToElement(linksList.get(index), offset);

    return jsActions.getCurrentPosition();
  }
}
