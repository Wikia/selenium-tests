package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class AdsPageFairObject extends AdsBaseObject {
    private static final String PATTERN_PAGEFAIR =
            "http://stats.pagefair.com/stats/page_view_event/[0-9A-Z]{16}/a\\.js.*";
    private static final String PAGEFAIR_URL_PARAM = "pagefairdetection=1";
    private static final String PATTERN_PAGEFAIR_ADBLOCK_DETECTED = PATTERN_PAGEFAIR + "is_ab=1.*";
    private static final String PATTERN_PAGEFAIR_ADBLOCK_NOT_DETECTED = PATTERN_PAGEFAIR + "is_ab=0.*";

    public AdsPageFairObject(WebDriver driver, Page page) {
        super(driver, AdsPageFairObject.addPageFairDetectionParam(page));
    }

    private static String addPageFairDetectionParam(Page page) {
        UrlBuilder urlBuilder = new UrlBuilder();

        String url = urlBuilder.getUrlForPage(page);
        return urlBuilder.appendQueryStringToURL(url, PAGEFAIR_URL_PARAM);
    }

    public void assertPageFairSendCorrectRequest(boolean expectedResult, NetworkTrafficInterceptor networkTrafficInterceptor) {
        String pattern = expectedResult ? PATTERN_PAGEFAIR_ADBLOCK_DETECTED : PATTERN_PAGEFAIR_ADBLOCK_NOT_DETECTED;
        try {
            this.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern);
        } catch (TimeoutException exception) {
            PageObjectLogging.log("TIMEOUT", exception, true);
            Assert.fail(getAssertionFailMessage(expectedResult));
        }
    }

    private String getAssertionFailMessage(boolean expectedResult) {
        String expectedValue = expectedResult ? "blocking detected" : "not detected";
        return String.format("Detection result not correct, expected: %s", expectedValue);
    }
}
