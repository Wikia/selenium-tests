package com.wikia.webdriver.elements.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Category {

  @FindBy(css = ".article-footer .collapsible-menu div")
  private WebElement categoryMenu;

  @FindBy(css = ".article-footer .collapsible-menu li")
  private List<WebElement> categoryList;

  private WebDriver driver;
  private Wait wait;

  public Category(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public Category toggle() {
    wait.forElementClickable(categoryMenu);
    categoryMenu.click();

    PageObjectLogging.logInfo("Category was toggled");

    return this;
  }

  public Category openCategoryPage(int index) {
    WebElement link = categoryList.get(index);

    PageObjectLogging.logInfo("Open category link no.: " + index);
    wait.forElementClickable(link);
    link.click();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.WIKIAMOBILE),
                         "Skin is different than Wikia Mobile");
    Assertion.assertTrue(driver.getCurrentUrl().contains("/wiki/Category:"),
                         "Url is different than /wiki/Category:");
    PageObjectLogging.logInfo("You were redirected to /wiki/Category:");

    return this;
  }
}
