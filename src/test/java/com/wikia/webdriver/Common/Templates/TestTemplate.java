package com.wikia.webdriver.Common.Templates;

import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import java.io.File;
import java.lang.reflect.Method;

import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.http.BrowserMobHttpRequest;
import org.browsermob.proxy.http.RequestInterceptor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.AdsProperties;
import com.wikia.webdriver.Common.Properties.Properties;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.codec.binary.Base64;


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

        /**
         * Start ProxyServer on port 4444 and set options for server
         *
         * @param ipAddress
         * @return ProxyServer
         * @throws Exception
         */
        protected ProxyServer startServerWithOptions(String ipAddress) {
            server = new ProxyServer(4444);
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, String> options = new HashMap<String,String>();
            options.put("httpProxy", ipAddress);
            server.setOptions(options);
            return server;
        }

        protected DesiredCapabilities setServerCaps(ProxyServer server) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            try {
                capabilities.setCapability(
                    CapabilityType.PROXY, server.seleniumProxy()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return capabilities;
        }

        protected ProxyServer setHeadersWithGeoEdgeCredentials(ProxyServer server) {
            String credentials = AdsProperties.geoEdgeUserName + ":" + AdsProperties.geoEdgeUserPass;
            Base64 base = new Base64();
            byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
            final String encodedString = new String(encodedCredentials);

            server.addRequestInterceptor(new RequestInterceptor() {
                @Override
                public void process(BrowserMobHttpRequest request) {
                    request.getMethod().removeHeaders("Proxy-Authorization");
                    try {
                    request.getMethod().addHeader(
                        "Proxy-Authorization",
                        "Basic " + encodedString
                    );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return server;
        }

}
