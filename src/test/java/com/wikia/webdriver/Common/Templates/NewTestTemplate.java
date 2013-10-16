package com.wikia.webdriver.Common.Templates;

import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class NewTestTemplate extends NewTestTemplateCore {

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		prepareURLs();
	}

	@BeforeMethod(alwaysRun = true)
	public void start(Method method, Object[] data) {
		startBrowser();
		logOut();
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopBrowser();
	}
}
