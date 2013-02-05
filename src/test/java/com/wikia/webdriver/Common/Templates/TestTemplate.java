package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.jetty.jetty.Server;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

public class TestTemplate {
	
	public WebDriver driver;
	public ProxyServer server;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite()
	{
		CommonUtils.deleteDirectory("."+File.separator+"logs");
		CommonUtils.createDirectory("."+File.separator+"logs");
		Properties.setProperties();
		PageObjectLogging.startLoggingSuite();
		
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite()
	{
		PageObjectLogging.stopLoggingSuite();
	}

	
	@BeforeMethod(alwaysRun = true)
	public void start(Method method)
	{
		startBrowser();
		PageObjectLogging.startLoggingMethod(getClass().getSimpleName().toString(), method.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void stop()
	{
		stopBrowser();
//		CommonFunctions.MoveCursorTo(0, 0);
		PageObjectLogging.stopLoggingMethod();
	}
	
	
	protected void startBrowser()
	{
		DriverProvider.getInstance();
		driver = DriverProvider.getWebDriver();
		server = DriverProvider.getServer();
	}
	
	protected void stopBrowser()
	{
		driver = DriverProvider.getWebDriver();
		if (driver != null)
		{
//			if (Global.BROWSER.equals("CHROME")||Global.BROWSER.equals("CHROMEMOBILE"))
//			{
			//chrome needs driver.quit();
				driver.quit();
//			}
//			else
//			{
//				System.out.println("before");
//				driver.close();		
//				System.out.println("after");
//			}
			driver = null;
		}
	}
}
