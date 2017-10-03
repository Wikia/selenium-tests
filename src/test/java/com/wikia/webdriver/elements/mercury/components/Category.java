package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Category extends WikiBasePageObject {

  @FindBy(css = ".article-footer .collapsible-menu div")
  private WebElement categoryMenu;

  @FindBy(css = ".article-footer .collapsible-menu ul")
  private WebElement categoryList;


  public Category() {
    PageFactory.initElements(driver, this);
  }

  public Category toggleMenu() {
    wait.forElementClickable(categoryMenu);
    categoryMenu.click();

    PageObjectLogging.logInfo("Category component was toggled");

    return this;
  }

  public CategoryPage openCategoryPage(String name) {
    WebElement link = categoryList.findElement(
        By.cssSelector(String.format("a[title=\"%s\"]", name))
    );

    PageObjectLogging.logInfo(String.format("Open category page named: \"%s\".", name));
    wait.forElementClickable(link);
    link.click();
    waitForPageReload();

    Assertion.assertTrue(getCurrentUrl().contains("/wiki/Category:"),
                         "Url is different than /wiki/Category:");
    PageObjectLogging.logInfo("You were redirected to /wiki/Category:");

    return new CategoryPage();
  }
}
