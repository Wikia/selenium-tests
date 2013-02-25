package com.wikia.webdriver.Common.DriverProvider;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class DriverProvider {
	
	private static final DriverProvider instance = new DriverProvider();
	private static WebDriver driver;
	private static ProxyServer server;

	/**
	 * creating webdriver instance based on given browser string
	 * @return instance of webdriver
	 * @author Karol Kujawiak
	 */
	public static DriverProvider getInstance()
	{
		PageObjectLogging listener = new PageObjectLogging();
		
		if (Global.BROWSER.equals("IE")){
			setIEProperties();
			driver = new EventFiringWebDriver(new InternetExplorerDriver()).register(listener);
		}
		else if (Global.BROWSER.equals("FF")){
			FirefoxProfile profile = new FirefoxProfile();
//			try {
//				profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\firebug-1.7.2.xpi"));
//				profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\netExport-0.8b13.xpi"));
//				profile.addExtension(new File("c:\\WebdriverTestsGit\\src\\test\\resources\\Firebug\\consoleExport-0.5b5.xpi"));
//				profile.setPreference("extensions.firebug.currentVersion", "1.7.2");
//				profile.setPreference("extensions.firebug.previousPlacement", 1);
//				profile.setPreference("extensions.firebug.net.enableSites", true);
//				profile.setPreference("extensions.firebug.defaultPanelName", "console");
//				profile.setPreference("extensions.firebug.console.enableSites", "true");
//				profile.setPreference("extensions.firebug.consoleexport.active", "true");
//				profile.setPreference("extensions.firebug.consoleexport.serverURL", "file://c:/asdf");
//				profile.setPreference("extensions.firebug.showJSErrors", "true");
//				profile.setPreference("extensions.firebug.allPagesActivation", "on");
//				profile.setPreference("extensions.firebug.consoleexport.defaultLogDir", "c:\\console");
//				profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
//				profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
//				profile.setPreference("extensions.firebug.consoleexport.saveFiles", true);		
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			driver = new EventFiringWebDriver(new FirefoxDriver(profile)).register(listener);
		}
		else if (Global.BROWSER.equals("CHROME")){
			setChromeProperties();
			driver = new EventFiringWebDriver(new ChromeDriver()).register(listener);
		}
		else if (Global.BROWSER.equals("SAFARI")){
			/*
			 * clone following repository
			 * https://github.com/senthilnayagam/safari-webdriver.git
			 * webdriver.safari.driver property should be set to path to the SafariDriver.safariextz file
			 */
			System.setProperty("webdriver.safari.driver", "");
			driver = new EventFiringWebDriver(new SafariDriver()).register(listener);
		}
		else if (Global.BROWSER.equals("FFPROXY")){
			server = new ProxyServer(4569);
			try{
				server.start();
				server.setCaptureHeaders(true);
				server.setCaptureContent(true);
				server.newHar("test");
				DesiredCapabilities caps = new DesiredCapabilities();
				Proxy proxy = server.seleniumProxy();
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(true);
				profile.setPreference("network.proxy.http", "localhost");
		        profile.setPreference("network.proxy.http_port", 4569);
		        profile.setPreference("network.proxy.ssl", "localhost");
		        profile.setPreference("network.proxy.ssl_port", 4569);
		        profile.setPreference("network.proxy.type", 1);
		        profile.setPreference("network.proxy.no_proxies_on", "");
		        profile.setProxyPreferences(proxy);
		        caps.setCapability(FirefoxDriver.PROFILE,profile);
		        caps.setCapability(CapabilityType.PROXY, proxy);
		        driver = new EventFiringWebDriver(new FirefoxDriver(caps)).register(listener);
		        }
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if (Global.BROWSER.equals("CHROMEMOBILE")){
			setChromeProperties();
			ChromeOptions o = new ChromeOptions();
			o.addArguments("--user-agent="+"Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
			driver = new EventFiringWebDriver(new ChromeDriver(o)).register(listener);
		}
		else if (Global.BROWSER.equals("HTMLUNIT")){
			driver = new EventFiringWebDriver(new HtmlUnitDriver()).register(listener);
		}		
		else{
			System.out.println("This browser is not supported. Check -Dbrowser property value");
		}
		if (!(Global.BROWSER.equals("CHROME")||Global.BROWSER.equals("CHROMEMOBILE")||Global.BROWSER.equals("SAFARI"))){
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		else{
			System.out.print(Global.BROWSER+" browser detected. Unable to set pageLoadTimeout()");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return instance;
	}
	
	/**
	 * creating webdriver instance based on given browser string
	 * @return
	 * @author Karol Kujawiak
	 */
	public static DriverProvider getInstanceFF(){
		PageObjectLogging listener = new PageObjectLogging();
		driver = new EventFiringWebDriver(new FirefoxDriver()).register(listener);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return instance;
	}
	
	/**
	 * 
	 * @return
	 * @author Karol Kujawiak
	 */
	public static WebDriver getWebDriver(){
		return driver;
	}
	
	public static ProxyServer getServer(){
		return server;
	}

	/**
	 * @author Karol Kujawiak
	 */
	private static void setIEProperties(){
		String sysArch = System.getProperty("os.arch");
		if (sysArch.equals("x86")){
			File file = new File("."+File.separator+
					"src"+File.separator+
					"test"+File.separator+
					"resources"+File.separator+
					"IEDriver"+File.separator+
					"IEDriverServer_x86.exe");	
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		}
		else{
			File file = new File("."+File.separator+
					"src"+File.separator+
					"test"+File.separator+
					"resources"+File.separator+
					"IEDriver"+File.separator+
					"IEDriverServer_x64.exe");	
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		}
		
	}
	
	/**
	 * @author Karol Kujawiak
	 */
	private static void setChromeProperties(){
		File file = new File("."+File.separator+
				"src"+File.separator+
				"test"+File.separator+
				"resources"+File.separator+
				"ChromeDriver"+File.separator+
				"chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
}
