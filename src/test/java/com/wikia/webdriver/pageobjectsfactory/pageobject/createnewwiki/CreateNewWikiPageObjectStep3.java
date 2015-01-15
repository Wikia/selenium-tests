package com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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
		waitForElementByCss(DATA_THEME_LIST);
		String themeName = themeLocator.replace("%name%", name);
		driver.findElement(By.cssSelector(themeName)).click();
		PageObjectLogging.log("selectTheme", "skin " + name + " selected", true, driver);
	}

	public void selectTheme(int skinNumber) {
		waitForElementByCss(DATA_THEME_LIST);
		jQueryClick("li[data-theme]:nth-child(" + skinNumber + ")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			PageObjectLogging.log("selectTheme", e.getMessage(), false);
		}
		PageObjectLogging.log("selectTheme", "skin number: " + skinNumber + " selected", true, driver);
	}

	public ArticlePageObject submit() {
		waitForElementNotPresent(loadingIndicatorBy);
		scrollAndClick(submitButton);
		PageObjectLogging.log("submit", "Submit button clicked", true, driver);
		return new ArticlePageObject(driver);
	}
}
