package com.wikia.webdriver.Common.DriverProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.wikia.webdriver.Common.Core.Global;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class NewDriverProvider {

	private static EventFiringWebDriver driver;
	private static EventFiringWebDriver driverFF;
	private static String browserName;
	private static DesiredCapabilities caps = new DesiredCapabilities();

	public static EventFiringWebDriver getDriverInstanceForBrowser(String browser) {
		browserName = browser;

		//If browser equals IE set driver property as IEWebDriver instance
		if (browserName.equals("IE")) {
			driver = getIEInstance();

		//If browser contains FF set driver property as FFWebDriver instance
		} else if(browserName.contains("FF")) {
			driver = getFFInstance();

		//If browser equals CHROME set driver property as ChromeWebDriver instance
		} else if (browserName.contains("CHROME")) {
			driver = getChromeInstance();

		//If browser equals SAFARI set driver property as SafariWebDriver instance
		} else if (browserName.equals("SAFARI")) {
			driver = getSafariInstance();

		} else if (browserName.equals("HTMLUNIT")) {
			driver = new EventFiringWebDriver(new HtmlUnitDriver());
		} else if (browserName.equals("GHOST")){
			driver = getPhantomJSInstance();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	public static WebDriver getWebDriverFirefox() {
		return driverFF;
	}

	private static EventFiringWebDriver getIEInstance() {
		File file = new File (
			"." + File.separator
			+ "src" + File.separator
			+ "test" + File.separator
			+ "resources" + File.separator
			+ "IEDriver" + File.separator
			+ "IEDriverServer.exe"
		);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		return new EventFiringWebDriver(new InternetExplorerDriver(caps));
	}

	private static EventFiringWebDriver getFFInstance() {
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

		//Check if user who is running tests have write access in ~/.mozilla dir and home dir
		 if (System.getProperty("os.name").toUpperCase().equals("LINUX")) {
                        File homePath = new File(System.getenv("HOME") + File.separator);
                        File mozillaPath = new File(homePath + File.separator + ".mozilla");
                        File tmpFile;
                        if (mozillaPath.exists()) {
                                try {
                                        tmpFile = File.createTempFile("webdriver", null, mozillaPath);
                                } catch (IOException ex) {
                                        throw new RuntimeException("Can't create file in path: %s".replace("%s", mozillaPath.getAbsolutePath()));
                                }
                        } else {
                                try {
                                        tmpFile = File.createTempFile("webdriver", null, homePath);
                                } catch (IOException ex) {
                                        throw new RuntimeException("Can't create file in path: %s".replace("%s", homePath.getAbsolutePath()));
                                }
                        }
                        tmpFile.delete();
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
		FirefoxDriver a = new FirefoxDriver(caps);	
	return new EventFiringWebDriver(a);
	}

	private static EventFiringWebDriver getChromeInstance() {
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
			ChromeOptions options = new ChromeOptions();
            options.addArguments(
                "--user-agent="+
                "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) " +
                "AppleWebKit/420+ (KHTML, like Gecko) " +
                "Version/3.0 Mobile/1A543a Safari/419.3"
            );
            return new EventFiringWebDriver(new ChromeDriver(options));
		}

		return new EventFiringWebDriver(new ChromeDriver(caps));
	}

	private static EventFiringWebDriver getPhantomJSInstance() {
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

		return new EventFiringWebDriver(new PhantomJSDriver(caps));
	}

	private static EventFiringWebDriver getSafariInstance() {
		/*
		 * clone following repository
		 * https://github.com/senthilnayagam/safari-webdriver.git
		 * webdriver.safari.driver property should be set to path to the SafariDriver.safariextz file
		 */
		System.setProperty("webdriver.safari.driver", "");
		return new EventFiringWebDriver(new SafariDriver());
	}

	public static void setDriverCapabilities(DesiredCapabilities newCaps) {
		caps = newCaps;
	}
}
