package com.wikia.webdriver.Common.DriverProvider;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class NewDriverProvider {

	private static WebDriver driver;
	private static ProxyServer server;
	private static String browserName;
	private static DesiredCapabilities caps = new DesiredCapabilities();

	public static WebDriver getDriverIntstanceForConfig(String browser) {
		browserName = browser;
		PageObjectLogging listener = new PageObjectLogging();

		//If browser equals IE set driver property as IEWebDriver instance
		if (browserName.equals("IE")) {
			driver = getIEInstance(listener);

		//If browser contains FF set driver property as FFWebDriver instance
		} else if(browserName.contains("FF")) {
			driver = getFFInstance(listener);

		//If browser equals CHROME set driver property as ChromeWebDriver instance
		} else if (browserName.contains("CHROME")) {
			driver = getChromeInstance(listener);

		//If browser equals SAFARI set driver property as SafariWebDriver instance
		} else if (browserName.equals("SAFARI")) {
			driver = getSafariInstance(listener);

		} else if (browserName.equals("HTMLUNIT")) {
			driver = new EventFiringWebDriver(new HtmlUnitDriver()).register(listener);
		} else if (browserName.equals("GHOST")){
			driver = getPhantomJSInstance(listener);
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getWebDriver() {
            return driver;
	}

	public static ProxyServer getServer() {
            return server;
	}

	private static WebDriver getIEInstance(PageObjectLogging listener) {
		String sysArch = System.getProperty("os.arch");
		if (sysArch.equals("x86")) {
			File file = new File (
				"." + File.separator
				+ "src" + File.separator
				+ "test" + File.separator
				+ "resources" + File.separator
				+ "IEDriver" + File.separator
				+ "IEDriverServer_x86.exe"
			);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		} else {
			File file = new File (
				"." + File.separator
				+ "src" + File.separator
				+ "test" + File.separator
				+ "resources" + File.separator
				+ "IEDriver" + File.separator
				+ "IEDriverServer_x64.exe"
			);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		}
		return new EventFiringWebDriver(new InternetExplorerDriver(caps)).register(listener);
	}

	private static WebDriver getFFInstance(PageObjectLogging listener) {
		FirefoxProfile profile = new FirefoxProfile();

		//Windows 8 requires to set webdriver.firefox.bin system variable
		//to path where executive file of FF is placed
		if (System.getProperty("os.name").toUpperCase().equals("WINDOWS 8")){
			System.setProperty(
				"webdriver.firefox.bin",
				"c:" + File.separator
				+ "Program Files (x86)" + File.separator
				+ "Mozilla Firefox" + File.separator
				+ "Firefox.exe"
			);
		}
		//If browserName contains CONSOLE activate JSErrorConsole
		if (browserName.contains("CONSOLE")) {
			try {
				File jsErr = new File(
					"." + File.separator
					+ "src" + File.separator
					+ "test" + File.separator
					+ "resources" + File.separator
					+ "Firebug" + File.separator
					+ "JSErrorCollector.xpi"
				);
				profile.addExtension(jsErr);
				//TODO!
				Global.JS_ERROR_ENABLED = true;
			} catch(FileNotFoundException e) {
				System.out.println("JS extension file doesn't exist in provided location");
			} catch (IOException e) {
				System.out.println("Error with adding firefox extension");
			}
		}

		caps.setCapability(FirefoxDriver.PROFILE, profile);
		return new EventFiringWebDriver(new FirefoxDriver(caps)).register(listener);
	}

	private static WebDriver getChromeInstance(PageObjectLogging listener) {
		String chromeBinaryName;
		String OSName = System.getProperty("os.name").toUpperCase();

		if (OSName.contains("WINDOWS")) {
			chromeBinaryName = "chromedriver.exe";

			File chromeBinary = new File (
				"." + File.separator
				+ "src" + File.separator
				+ "test" + File.separator
				+ "resources" + File.separator
				+ "ChromeDriver" + File.separator
				+ chromeBinaryName
			);

			System.setProperty("webdriver.chrome.driver", chromeBinary.getAbsolutePath());
		}



		if (browserName.equals("CHROMEMOBILE")) {
			caps.setCapability(
				"chrome.switches",
				Arrays.asList(
					"--user-agent=Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) "
					+ "AppleWebKit/420+ (KHTML, like Gecko) "
					+ "Version/3.0 Mobile/1A543a Safari/419.3"
				)
			);
		}

		return new EventFiringWebDriver(new ChromeDriver(caps)).register(listener);
	}

	private static WebDriver getPhantomJSInstance(PageObjectLogging listener) {
		String phantomJSBinaryName;
		String OSName = System.getProperty("os.name").toUpperCase();

		if (OSName.contains("WINDOWS")) {
			phantomJSBinaryName = "phantomjs.exe";

			File phantomJSBinary = new File(
				"." + File.separator
				+ "src" + File.separator
				+ "test" + File.separator
				+ "resources" + File.separator
				+ "PhantomJS" + File.separator
				+ phantomJSBinaryName
			);

			caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				phantomJSBinary.getAbsolutePath()
			);
		}

		return new EventFiringWebDriver(new PhantomJSDriver(caps)).register(listener);
	}

	private static WebDriver getSafariInstance(PageObjectLogging listener) {
		/*
		 * clone following repository
		 * https://github.com/senthilnayagam/safari-webdriver.git
		 * webdriver.safari.driver property should be set to path to the SafariDriver.safariextz file
		 */
		System.setProperty("webdriver.safari.driver", "");
		return new EventFiringWebDriver(new SafariDriver()).register(listener);
	}

	public static void setDriverCapabilities(DesiredCapabilities newCaps) {
		caps = newCaps;
	}
}
