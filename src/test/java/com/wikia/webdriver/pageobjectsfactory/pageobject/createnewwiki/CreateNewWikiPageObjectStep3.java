package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author Karol
 */
public class CreateNewWikiPageObjectStep3 extends BasePageObject {

  private static final String DATA_THEME_LIST = "li[data-theme]";
  private By loadingIndicatorBy = By.cssSelector(".wikiaThrobber");
  private String themeLocator = "li[data-theme='%name%']";

  @FindBy(css = "li[id='ThemeWiki'] input[class='next enabled']")
  private WebElement submitButton;

  public CreateNewWikiPageObjectStep3(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void selectThemeByName(String name) {
    wait.forElementVisible(By.cssSelector(DATA_THEME_LIST));
    String themeName = themeLocator.replace("%name%", name);
    scrollAndClick(driver.findElement(By.cssSelector(themeName)));
    LOG.logResult("selectTheme", "skin " + name + " selected", true, driver);
  }

  public ArticlePageObject submit() {

    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, 90).until(CommonExpectedConditions
          .elementNotPresent(loadingIndicatorBy));
    } finally {
      restoreDeaultImplicitWait();
    }

    scrollAndClick(submitButton);
    LOG.logResult("submit", "Submit button clicked", true, driver);
    return new ArticlePageObject(driver);
  }
}
