package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * 
 * @author Karol
 *
 */
public class CreateNewWikiPageObjectStep3 extends BasePageObject{

	private final String dataThemeList = "li[data-theme]";
	private By loadingIndicatorBy = By.cssSelector(".wikiaThrobber");
	private String themeLocator = "li[data-theme='%name%']";

	@FindBy(css=dataThemeList)
	private WebElement themeList;
	@FindBy(css="li[id='ThemeWiki'] input[class='next enabled']")
	private WebElement submitButton;

	public CreateNewWikiPageObjectStep3(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void selectThemeByName(String name) {
		waitForElementByCss(dataThemeList);
		String themeName = themeLocator.replace("%name%", name);
		driver.findElement(By.cssSelector(themeName)).click();
		PageObjectLogging.log("selectTheme", "skin " + name + " selected", true, driver);
	}

	public void selectTheme(int skinNumber)
	{
		waitForElementByCss(dataThemeList);
		jQueryClick("li[data-theme]:nth-child("+skinNumber+")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PageObjectLogging.log("selectTheme", "skin number: " + skinNumber + " selected", true, driver);
	}

	public NewWikiaHomePage submit(String wikiName)
	{
		waitForElementNotPresent(loadingIndicatorBy);
		waitForElementByElement(submitButton);
		clickAndWait(submitButton);
		PageObjectLogging.log("submit", "Submit button clicked", true, driver);
		return new NewWikiaHomePage(driver);
	}
}
