package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.TimeoutException;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class TrackingOptInModal extends BasePageObject {

    @FindBy(css = "body > div:nth-child(5) > div > div")
    WebElement modal;

    @FindBy(css = "div._36mdTyonPU0bxbU70dDO4f")
    WebElement acceptButton;

    @FindBy(css = "div._3mGMvAUi8MtSBaB1BUvLtz")
    WebElement rejecttButton;

    @FindBy(css="div[data-tracking-opt-in-overlay]")
    private WebElement optInModalOverlay;

    private HarEntry entry;
    private static final Duration WAITNG_TIME_FOR_ALL_REQUESTS = Duration.ofSeconds(10);


    public boolean isVisible() {
        try {
            wait.forElementVisible(optInModalOverlay);
            PageObjectLogging.logInfo("Tracking modal visible");

            return true;
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("Tracking modal not visible");

            return false;
        }
    }

    public void clickAcceptButton() {
        try {
            wait.forElementClickable(acceptButton);
            acceptButton.click();
        } catch (Exception e) {
            PageObjectLogging.log("Accept button clicked", e, false);
        }
    }

    public void clickRejectButton() {
        try {
            wait.forElementClickable(rejecttButton);
            rejecttButton.click();
        } catch (Exception e) {
            PageObjectLogging.log("Reject button clicked", e, false);
        }
    }

    public boolean isModalDisplayed() {
        boolean isVisible = false;
        try {
            wait.forElementVisible(modal);
            isVisible = true;
        } catch (Exception e) {
            PageObjectLogging.log("Modal is visible", e, false);
        }

        return isVisible;
    }

    public boolean isSuccessfulResponseByUrlPattern(final NetworkTrafficInterceptor trafficInterceptor,
                                                    final String pattern) {
        try {
            entry = trafficInterceptor.getEntryByUrlPattern(pattern);
            return entry.getResponse().getStatus() < 400;
        }catch (NullPointerException ex) {
            return false;
        }
    }

    public void verifyTrackingRequestsNotSend(List<String> elementsList, BasePageObject object,
                                              NetworkTrafficInterceptor networkTrafficInterceptor) {
        object.wait.forX(WAITNG_TIME_FOR_ALL_REQUESTS);
        for(int i=0; i<elementsList.size(); i++){
            Assertion.assertFalse(isSuccessfulResponseByUrlPattern(networkTrafficInterceptor, elementsList.get(i)),
                    "Request to " + elementsList.get(i) + " services was found");
        }
    }

    public void verifyTrackingRequestSend(List<String> elementsList, BasePageObject object,
                                          NetworkTrafficInterceptor networkTrafficInterceptor) {
        for(int i=0; i<elementsList.size(); i++){
            object.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,elementsList.get(i));
        }
    }

    public static String appendTrackingOptOutParameters(String url, String[] instantGlobals) {
        UrlBuilder urlBuilder = new UrlBuilder();

        for (String instantGlobal : instantGlobals) {
            url = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, instantGlobal);
        }

        url = urlBuilder.appendQueryStringToURL(url, "trackingoptout=1");

        return url;
    }
}
