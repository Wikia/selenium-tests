package com.wikia.webdriver.Common.Templates;

import com.wikia.webdriver.Common.Core.Annotations.UserAgent;
import java.lang.reflect.Method;

import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.DriverProvider.UseUnstablePageLoadStrategy;
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
		runProxyServerIfNeeded(method);
		if (method.getAnnotation(UserAgent.class) != null) {
			setBrowserUserAgent(
				method.getAnnotation(UserAgent.class).userAgent()
			);
		}

		if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)){
			NewDriverProvider.setUnstablePageLoadStrategy(true);
		}

		//Reset unstable page load strategy to default 'false' value
		NewDriverProvider.setUnstablePageLoadStrategy(false);
		logOut();
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		if (isProxyServerRunning) {
			networkTrafficIntereceptor.stop();
		}
		stopBrowser();
	}
}
