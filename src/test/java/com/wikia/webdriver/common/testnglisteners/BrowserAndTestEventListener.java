package com.wikia.webdriver.common.testnglisteners;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.*;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.logging.VelocityWrapper;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.*;

import java.lang.reflect.Method;
import java.util.Date;

public class BrowserAndTestEventListener extends AbstractWebDriverEventListener
    implements ITestListener {

  private By lastFindBy;
  private WikiaWebDriver driver;

  @Override
  public void beforeNavigateTo(String url, WebDriver driver) {
    new JavascriptActions(driver).execute("window.stop()");

    Log.ok("Navigate to", VelocityWrapper.fillLink(url, url));
    Log.logJSError();
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    Method method = TestContext.getCurrentTestMethod();
    if (method != null) {
      Class<?> declaringClass = method.getDeclaringClass();

      String cookieDomain = String.format(".%s", Configuration.getEnvType().getWikiaDomain());

      Date cookieDate = new Date(new DateTime().plusYears(10).getMillis());

      if (!AlertHandler.isAlertPresent(driver)) {
        String command = "Url after navigation";
        if (url.equals(driver.getCurrentUrl())) {
          Log.ok(command, VelocityWrapper.fillLink(driver.getCurrentUrl(), driver.getCurrentUrl()));
        } else {
          // A fast lane to stop executing any test on "not a valid community" page
          if (driver.getCurrentUrl().contains(URLsContent.NOT_A_VALID_COMMUNITY)) {
            throw new SkipException(String.format("Wrong redirect to: %s", driver.getCurrentUrl()));
          }
          if (driver.getCurrentUrl().contains("data:text/html,chromewebdata ")) {
            driver.get(url);
            Log.warning(command, driver.getCurrentUrl());
          } else {
            Log.warning(command, driver.getCurrentUrl());
          }
        }
      } else {
        Log.warning("Url after navigation", "Unable to check URL after navigation - alert present");
      }

      if (driver.getCurrentUrl().contains(Configuration.getWikiaDomain())) {
        // HACK FOR DISABLING NOTIFICATIONS
        try {
          new JavascriptActions(driver).execute("$(\".sprite.close-notification\")[0].click()");
        } catch (WebDriverException e) {
          Log.info("Hack for disabling notifications", "Failed to execute js action");
        }
        /**
         * All of tests should be executed as an user who opted in (agreed) on using ads tracking.
         * Manually user would need to click 'agree' in the tracking opt in modal.
         */

        if (TestContext.isFirstLoad()) {
          boolean userOptedIn = true;
          boolean userOptedOut = false;

          try {
            JavascriptExecutor js = DriverProvider.getActiveDriver();
            Object mobileWikiVersion = js.executeScript(
                "return requirejs.entries['mobile-wiki/config/environment'].module.exports.default.APP.version");
            Configuration.setTestValue("mobileWikiVersion", mobileWikiVersion.toString());
          } catch (WebDriverException e) {
            Configuration.setTestValue("mobileWikiVersion", null);
          }

          if (method.isAnnotationPresent(Execute.class) && !method.getAnnotation(Execute.class)
              .trackingOptIn()) {
            userOptedIn = false;
          }

          if (method.isAnnotationPresent(Execute.class) && method.getAnnotation(Execute.class)
              .trackingOptOut()) {
            userOptedOut = true;
          }

          if (userOptedIn) {
            driver.manage()
                .addCookie(new Cookie("tracking-opt-in-status",
                                      "accepted",
                                      cookieDomain,
                                      "/",
                                      cookieDate
                ));
          } else if (userOptedOut) {
            driver.manage()
                .addCookie(new Cookie("tracking-opt-in-status",
                                      "rejected",
                                      cookieDomain,
                                      "/",
                                      cookieDate
                ));
          }
        }

        /**
         * We want to disable sales pitch dialog for new potential contributors to avoid hiding other
         * UI elements. see https://wikia-inc.atlassian.net/browse/CE-3768
         */
        if (TestContext.isFirstLoad()
            && "true".equals(Configuration.getDisableCommunityPageSalesPitchDialog())) {
          driver.manage()
              .addCookie(new Cookie("cpBenefitsModalShown", "1", cookieDomain, "/", cookieDate));
        }

        if (TestContext.isFirstLoad() && "true".equals(Configuration.getMockAds())) {
          driver.manage()
              .addCookie(new Cookie("mock-ads",
                                    XMLReader.getValue("mock.ads_token"),
                                    cookieDomain,
                                    "/",
                                    cookieDate
              ));
          Log.info(String.format("Adding moc-ads cookie with value: %s, and domain: %s",
                                 XMLReader.getValue("mock.ads_token"),
                                 String.format(".%s", Configuration.getEnvType().getWikiaDomain())
          ));
        }
      }

      if (TestContext.isFirstLoad()) {
        User user = null;
        TestContext.setFirstLoad(false);

        if (declaringClass.isAnnotationPresent(Execute.class)) {
          user = declaringClass.getAnnotation(Execute.class).asUser();
        }

        if (method.isAnnotationPresent(Execute.class)) {
          user = method.getAnnotation(Execute.class).asUser();
        }

        if (user != null && user != User.ANONYMOUS) {
          // log in, make sure user is logged in and flow is on the requested url
          new WikiBasePageObject().loginAs(user);
        }

        NetworkTrafficInterceptor networkTrafficInterceptor = DriverProvider.getActiveDriver()
            .getProxy();
        if (networkTrafficInterceptor != null) {
          networkTrafficInterceptor.startIntercepting();
        }
      }
    }
    Log.logJSError();
  }

  @Override
  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    lastFindBy = by;
    SelectorStack.write(by);
    if (element != null) {
      SelectorStack.contextWrite();
    }
  }

  @Override
  public void beforeClickOn(WebElement element, WebDriver driver) {
    Log.logJSError();
  }

  @Override
  public void afterClickOn(WebElement element, WebDriver driver) {
    Log.info("click", lastFindBy.toString());
  }

  @Override
  public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
    Log.info("ChangeValueOfField", lastFindBy.toString());
  }

  @Override
  public void onTestStart(ITestResult result) {
    Log.clearLogStack();
    String testName = result.getName();
    String className = result.getTestClass().getName();
    System.out.println(className + " " + testName);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    Log.stop();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    driver = DriverProvider.getActiveDriver();

    if ("true".equals(Configuration.getLogEnabled())) {
      Log.logError("Test Failed", result.getThrowable());
      Log.logJSError();
      Log.stop();
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    if (!Log.isTestStarted()) {
      Log.startTest(result.getMethod().getConstructorOrMethod().getMethod());
    }
    if (result.getMethod()
        .getConstructorOrMethod()
        .getMethod()
        .isAnnotationPresent(DontRun.class)) {
      Log.ok("Test SKIPPED", "this test is not supported in this environment");
      result.setStatus(ITestResult.SUCCESS);
      onTestSuccess(result);
    } else {
      result.setStatus(ITestResult.FAILURE);
      if (result.getThrowable() == null) {
        result.setThrowable(new SkipException("TEST SKIPPED"));
      }
      onTestFailure(result);
    }
    if (Log.isTestStarted()) {
      Log.stop();
    }
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  @Override
  public void onStart(ITestContext context) {
    Log.startReport();
  }

  @Override
  public void beforeNavigateBack(WebDriver driver) {
    Log.ok("Navigate Back", "attempting to navigate back");
  }

  @Override
  public void afterNavigateBack(WebDriver driver) {
    Log.log("Navigate Back", "previous page loaded", true);
  }

  @Override
  public void beforeNavigateForward(WebDriver driver) {
    Log.ok("Navigate Froward", "attempting to navigate forward");
  }

  @Override
  public void afterNavigateForward(WebDriver driver) {
    Log.log("Navigate Froward", "forward page loaded", true);
  }

  @Override
  public void onFinish(ITestContext context) {
    CommonUtils.appendTextToFile(Log.LOG_PATH, "</body></html>");
  }
}
