package com.wikia.webdriver.common.driverprovider;

import com.wikia.webdriver.common.contentpatterns.PathsContent;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.logging.PageObjectLogging;

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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverProvider {

	private static final DriverProvider INSTANCE = new DriverProvider();
	private static WebDriver driver;
	private static DesiredCapabilities caps = new DesiredCapabilities();
	private static FirefoxProfile profile = new FirefoxProfile();

	/**
	 * creating webdriver instance based on given browser string
	 *
	 * @return instance of webdriver
	 * @author Karol Kujawiak
	 */
	public static DriverProvider getInstance() {
		AbstractWebDriverEventListener listener = new PageObjectLogging();
		Global.JS_ERROR_ENABLED = false;
		if (Global.BROWSER.equals("IE")) {
			setIEProperties();
			driver = new EventFiringWebDriver(
				new InternetExplorerDriver(caps)
			).register(listener);
		} else if (Global.BROWSER.contains("FF")) {
			if (System.getProperty("os.name").toLowerCase().equals("windows 8")) {
				System.setProperty("webdriver.firefox.bin", "c:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
			}
			if (Global.BROWSER.contains("CONSOLE")) {
				try {
					File jsErr = new File("./src/test/resources/Firebug/JSErrorCollector.xpi");
					profile.addExtension(jsErr);
					Global.JS_ERROR_ENABLED = true;
				} catch (FileNotFoundException e) {
					System.out.println("JS extension file doesn't exist in provided location");
				} catch (IOException e) {
					System.out.println("Error with adding firefox extension");
				}
			}
			if (Global.BROWSER.contains("eventTracking")) {
				try {
					profile.addExtension(new File(PathsContent.FIREBUG_PATH));
					profile.addExtension(new File(PathsContent.NET_EXPORT_PATH));
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
					profile.setPreference(domain + "netexport.defaultLogDir", PathsContent.HAR_DIR_PATH);
				} catch (IOException e) {
					System.out.println("Error while setting up event tracking. " + e.getMessage());
				}
			}
			caps.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new EventFiringWebDriver(
				new FirefoxDriver(caps)
			).register(listener);
		} else if (Global.BROWSER.equals("CHROME")) {
			setChromeProperties();
			driver = new EventFiringWebDriver(
				new ChromeDriver(caps)
			).register(listener);
		} else if (Global.BROWSER.equals("SAFARI")) {
				/*
                * clone following repository
                * https://github.com/senthilnayagam/safari-webdriver.git
                * webdriver.safari.driver property should be set to path to the SafariDriver.safariextz file
                */
			System.setProperty("webdriver.safari.driver", "");
			driver = new EventFiringWebDriver(new SafariDriver()).register(listener);
		}
//            else if (Global.BROWSER.contains("FFPROXY")) {
//                server = new ProxyServer(4569);
//                try {
//                    server.start();
//                    server.setCaptureHeaders(true);
//                    server.setCaptureContent(true);
//                    server.newHar("test");
//                    Proxy proxy = server.seleniumProxy();
//                    FirefoxProfile profile = new FirefoxProfile();
//                    profile.setAcceptUntrustedCertificates(true);
//                    profile.setAssumeUntrustedCertificateIssuer(true);
//                    profile.setPreference("network.proxy.http", "localhost");
//                    profile.setPreference("network.proxy.http_port", 4569);
//                    profile.setPreference("network.proxy.ssl", "localhost");
//                    profile.setPreference("network.proxy.ssl_port", 4569);
//                    profile.setPreference("network.proxy.type", 1);
//                    profile.setPreference("network.proxy.no_proxies_on", "");
//                    profile.setProxyPreferences(proxy);
//                    caps.setCapability(FirefoxDriver.PROFILE,profile);
//                    caps.setCapability(CapabilityType.PROXY, proxy);
//                    driver = new EventFiringWebDriver(new FirefoxDriver(caps)).register(listener);
//                } catch(Exception e){
//                    e.printStackTrace();
//                }
//            } 
		else if (Global.BROWSER.equals("CHROMEMOBILE")) {
			setChromeProperties();
			ChromeOptions o = new ChromeOptions();
			o.addArguments(
				"--user-agent="
					+ "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
			);
			driver = new EventFiringWebDriver(new ChromeDriver(o)).register(listener);
		} else if (Global.BROWSER.equals("HTMLUNIT")) {
			driver = new EventFiringWebDriver(new HtmlUnitDriver()).register(listener);
		} else if (Global.BROWSER.equals("GHOST")) {
			System.setProperty("phantomjs.binary.path", "src/test/resources/PhantomJS/phantomjs.exe");
			driver = new EventFiringWebDriver(new PhantomJSDriver(caps)).register(listener);
		} else {
			System.out.println("This browser is not supported. Check -Dbrowser property value");
		}
		if (!(Global.BROWSER.equals("CHROME") || Global.BROWSER.equals("CHROMEMOBILE") || Global.BROWSER.equals("SAFARI"))) {
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} else {
			System.out.print(Global.BROWSER + " browser detected. Unable to set pageLoadTimeout()");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return INSTANCE;

	}

	/**
	 * creating webdriver instance based on given browser string
	 *
	 * @return
	 * @author Karol Kujawiak
	 */
	public static DriverProvider getInstanceFF() {
		AbstractWebDriverEventListener listener = new PageObjectLogging();
		driver = new EventFiringWebDriver(new FirefoxDriver(caps)).register(listener);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return INSTANCE;
	}

	/**
	 * @return
	 * @author Karol Kujawiak
	 */
	public static WebDriver getWebDriver() {
		return driver;
	}

	/**
	 * @author Karol Kujawiak
	 */
	private static void setIEProperties() {
		File file = new File(
			"." + File.separator
				+ "src" + File.separator
				+ "test" + File.separator
				+ "resources" + File.separator
				+ "IEDriver" + File.separator
				+ "IEDriverServer.exe"
		);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	}

	/**
	 * @author Karol Kujawiak
	 */
	private static void setChromeProperties() {
		String chromeBinaryName;
		String OSName = System.getProperty("os.name").toUpperCase();

		if (OSName.contains("WINDOWS")) {
			chromeBinaryName = "chromedriver.exe";

			File chromeBinary = new File(
				"." + File.separator
					+ "src" + File.separator
					+ "test" + File.separator
					+ "resources" + File.separator
					+ "ChromeDriver" + File.separator
					+ chromeBinaryName
			);

			System.setProperty("webdriver.chrome.driver", chromeBinary.getAbsolutePath());
		}
	}
}
