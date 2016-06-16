package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation(driver);

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar(driver);

  @FindBy(css = "article a[href=\"/wiki/Category:Category_page_test"
                + "_-_Category_with_description_and_no_members\"]")
  private WebElement linkToCategoryPage;

  public ArticlePage openPageWithLinkToCategoryPage() {
    getNavigate().toPage(MercurySubpages.ARTICLE_WITH_LINK_TO_CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS);

    return this;
  }

  /**
   * This method expects openPageWithLinkToCategoryPage method to be executed first,
   * but it will also work on pages with the linkToCategoryPage element.
   */
  public CategoryPage openCategoryPageFromLink() {
    wait.forElementClickable(linkToCategoryPage);
    linkToCategoryPage.click();

    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("/wiki/Category:"),
                         "URL does not contain pattern: /wiki/Category:");
    PageObjectLogging.logInfo("URL contains pattern: /wiki/Category:");

    return new CategoryPage();
  }

  public ArticlePage open(String pageName){
    getNavigate().toPage(pageName);

    return this;
  }

  public ArticlePage openDiscussions() {
    return this.open("/d/");
  }
}
