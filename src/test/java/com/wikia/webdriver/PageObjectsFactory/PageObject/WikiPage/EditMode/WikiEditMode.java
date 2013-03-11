package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * 
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class WikiEditMode extends BasePageObject{

		
	public WikiEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		}
	
	

}
