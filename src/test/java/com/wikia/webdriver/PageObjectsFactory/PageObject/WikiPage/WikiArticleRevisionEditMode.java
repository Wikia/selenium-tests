package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WikiArticleRevisionEditMode extends WikiArticleEditMode{

	public WikiArticleRevisionEditMode(WebDriver driver, String Domain,
			String articlename) {
		super(driver, Domain, articlename);
		PageFactory.initElements(driver, this);
	}

}
