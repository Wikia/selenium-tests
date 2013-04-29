package com.wikia.webdriver.Common.Templates;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestTemplate {

	public WebDriver driver;
	public ProxyServer server;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
            CommonUtils.deleteDirectory("." + File.separator + "logs");
            CommonUtils.createDirectory("." + File.separator + "logs");
            Properties.setProperties();
            PageObjectLogging.startLoggingSuite();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
            PageObjectLogging.stopLoggingSuite();
	}

	@BeforeMethod(alwaysRun = true)
	public void start(Method method) {
            startBrowser();
            PageObjectLogging.startLoggingMethod(
                getClass().getSimpleName().toString(), method.getName()
            );
	}

	@AfterMethod(alwaysRun = true)
	public void stop() {
		stopBrowser();
		PageObjectLogging.stopLoggingMethod();
	}

	protected void startBrowser() {
            DriverProvider.getInstance();
            driver = DriverProvider.getWebDriver();
            server = DriverProvider.getServer();
	}

	protected void stopBrowser() {
            driver = DriverProvider.getWebDriver();
            if (driver != null) {
                driver.quit();
                driver = null;
            }
	}

	protected DesiredCapabilities setServerCaps(GeoEdgeProxyServer server) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try {
			capabilities.setCapability(CapabilityType.PROXY,
					server.seleniumProxy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return capabilities;
	}
	
	/**
	 *  sets new profile to the firefox browser, with netExport enabled
	 */
	protected void enableNetExportOnFirebug() {
		FirefoxProfile profile = new FirefoxProfile();
		try {
			profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\firebug-1.7.2.xpi"));
			profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\netExport-0.8b13.xpi"));
			profile.setPreference("app.update.enabled", false);

		        String domain = "extensions.firebug.";

		        // Set default Firebug preferences
		    profile.setPreference(domain + "currentVersion", "2.0");
		    profile.setPreference(domain + "allPagesActivation", "on");
		    profile.setPreference(domain + "defaultPanelName", "net");
		    profile.setPreference(domain + "net.enableSites", true);

		        // Set default NetExport preferences
		    profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
		    profile.setPreference(domain + "netexport.showPreview", false);
		    profile.setPreference(domain + "netexport.defaultLogDir", "C:\\Downloads\\_har\\");
//			profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\consoleExport-0.5b5.xpi"));
//			profile.setPreference("extensions.firebug.currentVersion", "1.7.2");
//			profile.setPreference("extensions.firebug.previousPlacement", 1);
//			profile.setPreference("extensions.firebug.net.enableSites", true);
//			profile.setPreference("extensions.firebug.defaultPanelName", "console");
//			profile.setPreference("extensions.firebug.console.enableSites", "true");
//			profile.setPreference("extensions.firebug.consoleexport.active", "true");
//			profile.setPreference("extensions.firebug.consoleexport.serverURL", "file://c:/asdf");
//			profile.setPreference("extensions.firebug.showJSErrors", "true");
//			profile.setPreference("extensions.firebug.allPagesActivation", "on");
//			profile.setPreference("extensions.firebug.consoleexport.defaultLogDir", "c:\\console");
//			profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
//			profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
//			profile.setPreference("extensions.firebug.consoleexport.saveFiles", true);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverProvider.setFirefoxProfile(profile);
		DriverProvider.getInstance();
		driver = DriverProvider.getWebDriver();
	}
}
