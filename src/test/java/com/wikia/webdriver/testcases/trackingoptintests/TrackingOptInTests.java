package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.annotations.Test;
import java.util.List;


@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTests extends TemplateNoFirstLoad {

    private static final Page ADS_HOME_PAGE = new Page("project43", "Project43_Wikia");
    private static final String MODAL_INSTANT_GLOBAL = "InstantGlobals.wgEnableTrackingOptInModal=1";

    //    @Execute(trackingOptIn = "false")
    @NetworkTrafficDump(useMITM = false)
    @Test
    public void verifyTrackingForUserOptedIn() {
        TrackingOptInModal modal = new TrackingOptInModal();
        new PostsListPage().open();

        networkTrafficInterceptor.startIntercepting();
        Assertion.assertTrue(modal.isModalDisplayed());
        modal.clickAcceptButton();
        networkTrafficInterceptor.stop();
        List<HarEntry> entries = networkTrafficInterceptor.getHar().getLog().getEntries();

    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsDataProvider",
            groups = "adsOptInRejected"
    )
    public void verifyTrackingOptInRejectedForAdsInUE(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();

        TrackingOptInModal modal = new TrackingOptInModal();
        String url = urlBuilder.getUrlForPage(ADS_HOME_PAGE);
        String urlWithInstantGlobals = modal.appendTrackingOptOutParameters(url, instantGlobals);
        String urlWithModalOn = urlBuilder.appendQueryStringToURL(urlWithInstantGlobals, MODAL_INSTANT_GLOBAL);

        BasePageObject ads = new BasePageObject();
        ads.getUrl(urlWithModalOn);

        modal.clickRejectButton();
        modal.verifyTrackingRequestsNotSend(urlPatterns, ads, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsDataProvider",
            groups = "adsOptInAccepted"
    )
    public void verifyTrackingOptInAcceptedForAdsInUE(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();

        TrackingOptInModal modal = new TrackingOptInModal();
        String url = urlBuilder.getUrlForPage(ADS_HOME_PAGE);
        String urlWithInstantGlobals = modal.appendTrackingOptOutParameters(url, instantGlobals);
        String urlWithModalOn = urlBuilder.appendQueryStringToURL(urlWithInstantGlobals, MODAL_INSTANT_GLOBAL);

        BasePageObject ads = new BasePageObject();
        ads.getUrl(urlWithModalOn);

        modal.clickAcceptButton();
        modal.verifyTrackingRequestSend(urlPatterns, ads, networkTrafficInterceptor);
    }

}
