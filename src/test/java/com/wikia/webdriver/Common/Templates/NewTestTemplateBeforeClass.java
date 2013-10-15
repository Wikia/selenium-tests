package com.wikia.webdriver.Common.Templates;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class NewTestTemplateBeforeClass extends NewTestTemplateCore {

	public NewTestTemplateBeforeClass() {
		super();
	}

	@BeforeClass(alwaysRun = true)
	public void start() {
		prepareURLs();
		startBrowser();
	}

	@AfterClass(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}
}
