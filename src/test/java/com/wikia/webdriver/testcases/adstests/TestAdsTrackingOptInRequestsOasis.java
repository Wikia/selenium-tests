package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.util.List;

public class TestAdsTrackingOptInRequestsOasis extends NewTestTemplate {

    private static final Page ADS_HOME_PAGE = new Page("project43", "Project43_Wikia");

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKikimoraDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKruxDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForKrux(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNetzAthletenDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForNetzAthleten(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsMoatDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForMoat(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNordicsDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForNordics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "DE");

        modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsPrebidDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsA9DataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForA9(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsComscoreQuantcastDataProvider",
            groups = "adsOptInRejectedOasis"
    )
    public void adsTrackingRejectedForComscoreAndQuantcast(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);

    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKikimoraDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void verifyAdsTrackingAcceptedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsKruxDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForKrux(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNetzAthletenDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForNetzAthleten(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsMoatDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForMoat(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsNordicsDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForNordics(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "DE");

        modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsPrebidDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsA9DataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForA9(String[] instantGlobals, List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(instantGlobals, urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "adsComscoreQuantcastDataProvider",
            groups = "adsOptInAcceptedOasis"
    )
    public void adsTrackingAcceptedForComscoreAndQuantcast(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal modal = new TrackingOptInModal();
        setGeoCookie("EU", "PL");

        modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor, ADS_HOME_PAGE);
    }

    private void setGeoCookie(String continent, String country) {
        Cookie geoCookie = driver.manage().getCookieNamed("Geo");
        driver.manage().deleteCookie(geoCookie);
        driver.manage().addCookie(new Cookie(
                "Geo",
                "{%22region%22:%22WP%22%2C%22country%22:%22" + country + "%22%2C%22continent%22:%22" + continent + "%22}",
                String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
                null,
                null
        ));
    }
}
