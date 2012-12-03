package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class WikiArticleEditModeBasePageObject extends WikiBasePageObject{

	public WikiArticleEditModeBasePageObject(WebDriver driver, String Domain) {
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}

}
