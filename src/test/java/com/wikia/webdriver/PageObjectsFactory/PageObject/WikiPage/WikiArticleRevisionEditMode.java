package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class WikiArticleRevisionEditMode extends WikiArticleEditMode{

	public WikiArticleRevisionEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
