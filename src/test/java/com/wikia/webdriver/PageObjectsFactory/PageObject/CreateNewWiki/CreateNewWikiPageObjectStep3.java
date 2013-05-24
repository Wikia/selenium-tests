package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;


/**
 * 
 * @author Karol
 *
 */
public class CreateNewWikiPageObjectStep3 extends BasePageObject{

	@FindBy(css="li[data-theme]")
	private WebElement themeList;
	@FindBy(css="li[id='ThemeWiki'] input[class='next enabled']") 
	private WebElement submitButton;

	
	public CreateNewWikiPageObjectStep3(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void selectTheme(int skinNumber)
	{
		waitForElementByCss("li[data-theme]");
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
		waitForElementByElement(submitButton);
		clickAndWait(submitButton);
		PageObjectLogging.log("submit", "Submit button clicked", true, driver);
		return new NewWikiaHomePage(driver);
	}

}
