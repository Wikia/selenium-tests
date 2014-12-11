package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

public class WikiArticleRevisionEditMode extends WikiArticleEditMode {

	public WikiArticleRevisionEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
