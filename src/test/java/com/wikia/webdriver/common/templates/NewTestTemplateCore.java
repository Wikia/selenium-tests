package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.lang.reflect.Method;

@Listeners({com.wikia.webdriver.common.logging.PageObjectLogging.class,
    com.wikia.webdriver.common.testnglisteners.InvokeMethodAdapter.class})
public class NewTestTemplateCore {

  protected WikiaWebDriver driver;
  protected UrlBuilder urlBuilder;
  protected String wikiURL;
  protected String wikiCorporateURL;
  protected String wikiCorpSetupURL;
  protected NetworkTrafficInterceptor networkTrafficInterceptor;
  protected boolean isProxyServerRunning = false;
  private DesiredCapabilities capabilities;

  protected void refreshDriver() {
    driver = DriverProvider.getActiveDriver();
  }

  @BeforeSuite(alwaysRun = true)
  public void beforeSuite() {
    prepareDirectories();
  }

  @BeforeMethod(alwaysRun = true)
  public void initTestContext(Method method) {
    TestContext.writeMethodName(method);
    PageObjectLogging.start(method);
  }

  protected void prepareDirectories() {
    CommonUtils.deleteDirectory("." + File.separator + "logs");
    CommonUtils.createDirectory("." + File.separator + "logs");
  }

  protected void prepareURLs() {
    urlBuilder = new UrlBuilder();
    wikiURL = urlBuilder.getUrlForWiki(Configuration.getWikiName());
    wikiCorporateURL = urlBuilder.getUrlForWiki("wikia");
    wikiCorpSetupURL = urlBuilder.getUrlForWiki("corp");
  }

  protected void setWindowSize() {
    Dimension browserSize = Configuration.getBrowserSize();

    if (!driver.isAndroid()) {
      if (browserSize != null) {
        driver.manage().window().setSize(browserSize);
      } else {
        driver.manage().window().maximize();
      }
    }
  }

  protected void loadFirstPage() {
    driver.get(wikiURL + URLsContent.SPECIAL_VERSION);
  }
}
