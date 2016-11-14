package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsBrowserError extends TemplateNoFirstLoad {

  @Test(groups = "AdsScrollHandlerBrowserError")
  public void adsScrollHandlerBrowserError() {
    JavascriptActions jsActions = new JavascriptActions(driver);

    String testPage = urlBuilder.getUrlForPath("project43", "UAP");
    testPage = urlBuilder.appendQueryStringToURL(testPage, "scrollhandler=1");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver);
    adsBaseObject.getUrl(testPage);

    jsActions.addErrorListenerScript();
    jsActions.waitForJavaScriptTruthy("window.scrollY == 0");
    jsActions.scrollBy(0, 5001);
    jsActions.waitForJavaScriptTruthy("window.scrollY != 0");

    Assertion.assertEquals(jsActions.getWindowErrors(), "");
  }
}
