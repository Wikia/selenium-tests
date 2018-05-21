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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInRejectedMobileWiki"
    )
    public void adsTrackingRejectedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "adsOptInRejectedMobileWiki"
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
            groups = "adsOptInRejectedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedOasis"
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
            groups = "adsOptInAcceptedMobileWiki"
    )
    public void adsTrackingAcceptedForGoogleAnalytics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsQualarooDataProvider",
            groups = "adsOptInAcceptedMobileWiki"
    )
    public void adsTrackingAcceptedForQualaroo(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal.setGeoCookie(driver, "EU", "PL");

        new TrackingOptInModal()
                .verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }
}
