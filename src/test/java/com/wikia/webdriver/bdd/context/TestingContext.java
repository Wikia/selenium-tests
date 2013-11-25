package com.wikia.webdriver.bdd.context;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;

public interface TestingContext {
	WebDriver getDriver();

	AbstractConfiguration getConfig();

	String getWikiURL();

	String getWikiCorporateURL();

	BasePageObject getPage();

	void setPage(WikiBasePageObject page);
}
