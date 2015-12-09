package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class TestAdsBrowserError extends TemplateNoFirstLoad {

  @Test(groups = "AdsScrollHandlerBrowserError")
  public void adsScrollHandlerBrowserError() {
    String testPage = urlBuilder.getUrlForPath("adtest", "Wikia_Ad_Testing");
    testPage = urlBuilder.appendQueryStringToURL(testPage, "scrollhandler=1");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver);
    adsBaseObject.getUrl(testPage);

    executeScript(addErrorListenerScript());
    executeScript(scroll());

    adsBaseObject.waitForJavaScriptTruthy("window.scrollY != 0");
    Assertion.assertEquals(executeScript(getErrors()), "");
  }

  private String addErrorListenerScript() {
    return "var script = document.createElement('script'); "
           + "script.innerHTML = 'window.onerror = "
           + "function (e, u, l, c, errorObj) { window.errors = errorObj.stack }';"
           + "document.querySelector('body').appendChild(script);";
  }

  private String scroll() {
    return "setTimeout(function() { window.scroll(0, 5001); }, 2000);";
  }

  private String getErrors() {
    return "return window.errors || ''";
  }

  private String executeScript(String script) {
    return (String) ((JavascriptExecutor) driver).executeScript(script);
  }
}
