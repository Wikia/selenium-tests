package com.wikia.webdriver.Common.Templates;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ com.wikia.webdriver.Common.Logging.PageObjectLogging.class })
public class NewTestTemplate extends NewTestTemplateSkeleton {

	public NewTestTemplate() {
		super();
	}

	@BeforeClass
	public void beforeClass() {
		prepareURLs();
	}

	@BeforeMethod
	public void start(Method method, Object[] data) {
		startBrowser();
	}

	@AfterMethod
	public void stop() {
		stopBrowser();
	}
}
