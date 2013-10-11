package com.wikia.webdriver.Common.Templates;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class VisualEditorTestTemplate extends NewTestTemplateSkeleton {

	public VisualEditorTestTemplate() {
		super();
	}

	@BeforeClass
	public void start() {
		prepareURLs();
		startBrowser();
	}

	@AfterClass
	public void stop() {
		stopBrowser();
	}
}
