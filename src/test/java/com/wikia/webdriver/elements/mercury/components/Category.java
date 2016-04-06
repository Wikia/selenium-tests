package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Category {

  @FindBy(css = ".article-footer .collapsible-menu div")
  private WebElement categoryMenu;

  @FindBy(css = ".article-footer .collapsible-menu ul")
  private WebElement categoryList;

  private WebDriver driver;
  private Wait wait;
  private Loading loading;

  public Category(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.loading = new Loading(driver);

    PageFactory.initElements(driver, this);
  }

  public Category toggle() {
    wait.forElementClickable(categoryMenu);
    categoryMenu.click();

    PageObjectLogging.logInfo("Category was toggled");

    return this;
  }

  private CategoryPage navigateToCategoryPage(String href) {
    WebElement link = categoryList.findElement(By.cssSelector("a[href=\"" + href + "\"]"));

    PageObjectLogging.logInfo("Open category link to: " + href);
    wait.forElementClickable(link);
    link.click();
    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("/wiki/Category:"),
                         "Url is different than /wiki/Category:");
    PageObjectLogging.logInfo("You were redirected to /wiki/Category:");

    return new CategoryPage();
  }

  public CategoryPage navigateToCategoryPageWithArticleAndWithMembers() {
    return navigateToCategoryPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITH_MEMBERS)
        .articleContainerIsVisible()
        .noMembersMessageIsVisible();
  }

  public CategoryPage navigateToCategoryPageWithoutArticleAndWithMembers() {
    return navigateToCategoryPage(MercurySubpages.CATEGORY_WITHOUT_ARTICLE_AND_WITH_MEMBERS)
        .articleContainerIsNotPresent()
        .categorySectionsContainerIsVisible();
  }
}
