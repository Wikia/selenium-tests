package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class WikiCategoryPageObject extends WikiBasePageObject{

	private By categories_listOfCategories = By.cssSelector("div.mw-content-ltr li a");	
	
	public WikiCategoryPageObject(WebDriver driver, String wikiname) {
		super(driver, wikiname);
		
		PageFactory.initElements(driver, this);
	}


		/**
		* click SaveButton
		*
		@author Michal Nowierski
		*/
		public void verifyCategoryContainsPage(String articleName) {
			List<WebElement> lista  = driver.findElements(categories_listOfCategories);
			Boolean result = false;
			articleName = articleName.replaceAll("_", " ");
			// there might be more than one category on a random page. Thus - loop over all of them.
			for (WebElement webElement : lista) {
				waitForElementByElement(webElement);		
				if (webElement.getText().equalsIgnoreCase(articleName)) {
					result = true;
				}
			}
			if (result) {
				PageObjectLogging.log("verifyCategoryContainsPage", "page "+articleName+" present on category page", true, driver);			
			}
			else {
				PageObjectLogging.log("verifyCategoryContainsPage", "page "+articleName+" NOT present on category page", false, driver);						
			}
			
		}		
	
	
}
