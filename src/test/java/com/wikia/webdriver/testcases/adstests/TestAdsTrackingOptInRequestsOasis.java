package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;

public class TestAdsTrackingOptInRequestsOasis extends NewTestTemplate {

    private static final Page ADS_HOME_PAGE = new Page("project43", "Project43_Wikia");
    private static final Page ADS_HIVI_PAGE = new Page("project43", "SyntheticTests/UAP/HiVi");


    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKikimoraDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKruxDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForKrux(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNetzAthletenDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForNetzAthleten(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
               networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsMoatDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForMoat(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNordicsDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForNordics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "DE");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsPrebidDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsA9DataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForA9(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsComscoreQuantcastDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForComscoreAndQuantcast(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);

    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsGoogleAnalyticsDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HOME_PAGE));
        modal.clickRejectButton();

        modal.isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForQualaroo(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNpaHiViDataProviderOasis",
            groups = "AdsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForHiViAdSlots(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");
        AdsBaseObject ads = new AdsBaseObject(driver);
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HIVI_PAGE));

        modal.clickRejectButton();
        ads.scrollToFooter();

        modal.isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKikimoraDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void verifyAdsTrackingAcceptedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKruxDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForKrux(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNetzAthletenDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForNetzAthleten(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsMoatDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForMoat(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNordicsDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForNordics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "DE");

        new TrackingOptInModal().verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsPrebidDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsA9DataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForA9(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(instantGlobals, urlPatterns,
                networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsComscoreQuantcastDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForComscoreAndQuantcast(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");

        new TrackingOptInModal().verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNpaHiViDataProviderOasis",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForHiViAdSlots(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver,"EU", "PL");
        AdsBaseObject ads = new AdsBaseObject(driver);
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HIVI_PAGE));

        modal.clickAcceptButton();
        ads.scrollToFooter();

        modal.isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsGoogleAnalyticsDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(ADS_HOME_PAGE));
        modal.clickAcceptButton();

        modal.isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "AdsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForQualaroo(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }
}
