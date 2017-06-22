package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  private By articleContent = By.cssSelector(".article-content");
  private By categoryMembersContainer = By.cssSelector(".category-members-grouped");
  private By categoryMembers = By.cssSelector(".category-members-grouped__list li a");
  private By nextButton = By.cssSelector(".category-navigation__next");
  private By previousButton = By.cssSelector(".category-navigation__prev");

  private Navigate navigate;

  public CategoryPage() {
    super();

    navigate = new Navigate();
  }

  public CategoryPage open(String categoryName) {
    this.navigate.toPageByPath(String.format("%s%s", URLsContent.WIKI_DIR, categoryName));
    new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI);

    PageObjectLogging.logInfo(String.format("%s category page opened", categoryName));

    return this;
  }


  public ArticlePage navigateToCategoryMemberPage() {
    new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI);

    WebElement member = driver.findElement(categoryMembers);
    String memberName = member.getText();

    wait.forElementClickable(member);
    member.click();

    new Loading(driver).handleAsyncPageReload();
    PageObjectLogging.logInfo(String.format("You were redirected to page: \"%s\".", memberName));

    new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI);

    return new ArticlePage();
  }

  public String getArticleContent() {
    return driver.findElement(articleContent).getText();
  }

  public boolean categoryMembersContainerIsVisible() {
    wait.forElementVisible(categoryMembersContainer);
    PageObjectLogging.logInfo("Category members container is visible.");

    return true;
  }

  public boolean hasCategoryMembers() {
    wait.forElementVisible(categoryMembers);
    PageObjectLogging.logInfo("Category has members.");

    return driver.findElements(categoryMembers).size() > 0;
  }

  public boolean nextButtonIsVisible() {
    wait.forElementVisible(nextButton);
    PageObjectLogging.logInfo("Next page button is visible.");

    return driver.findElement(nextButton).isDisplayed();
  }

  public boolean previousButtonIsVisible() {
    wait.forElementVisible(previousButton);
    PageObjectLogging.logInfo("Previous page button is visible.");

    return driver.findElement(previousButton).isDisplayed();
  }

  public CategoryPage clickNextButton() {
    this.scrollAndClick(driver.findElement(nextButton));
    PageObjectLogging.logInfo("Next page button clicked.");

    return this;
  }

  public CategoryPage clickPreviousButton() {
    this.scrollAndClick(driver.findElement(previousButton));
    PageObjectLogging.logInfo("Previous page button clicked.");

    return this;
  }
}
