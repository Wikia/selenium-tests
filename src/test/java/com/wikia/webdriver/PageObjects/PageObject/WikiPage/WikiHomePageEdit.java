package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;

public class WikiHomePageEdit extends BasePageObject{

	public WikiHomePageEdit(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	

}
