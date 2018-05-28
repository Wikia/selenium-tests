package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"discussions-tracking-opt-in"})
public class TrackingOptIn extends NewTestTemplate {

    /*
        DESKTOP TESTS
    */

    @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
    @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries",
            groups = {"discussions-tracking-opt-in-desktop"})
    public void testModalVisibilityForAnonOnDesktop(String continent, String country, boolean shouldGetModal) {
        TrackingOptInModal trackingModal = setGeoCookieAndNavigate(continent, country);
        Assertion.assertEquals(trackingModal.isVisible(), shouldGetModal);
    }

    @Execute(asUser = User.USER, trackingOptIn = false)
    @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries",
            groups = {"discussions-tracking-opt-in-desktop"})
    public void testModalVisibilityForLoggedInWhoNeverOptedInOnDesktop(String continent, String country,
                                                              boolean shouldGetModal) {
        TrackingOptInModal trackingModal = setGeoCookieAndNavigate(continent, country);
        Assertion.assertEquals(trackingModal.isVisible(), shouldGetModal);
    }

    @Test(groups = {"discussions-tracking-opt-in-desktop"})
    @Execute(asUser = User.USER)
    public void loggedInEUShouldNotGetModalIfOptedInOnDesktop() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @Test(groups = {"discussions-tracking-opt-in-desktop"})
    @Execute(asUser = User.ANONYMOUS)
    public void AnonInEUShouldNotGetModalIfOptedInOnDesktop() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @Test(groups = {"discussions-tracking-opt-in-desktop"})
    @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
    public void loggedInEUShouldNotGetModalIfOptedOutOnDesktop() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @Test(groups = {"discussions-tracking-opt-in-desktop"})
    @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
    public void AnonInEUShouldNotGetModalIfOptedOutOnDesktop() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @NetworkTrafficDump(useMITM = true)
    @Test(groups = {"discussions-tracking-opt-in-desktop"},
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "googleAnalyticAnonymizedUser")
    @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
    public void anonInEUOnRejectShouldGetAnonymizedGoogleTracking(List<String> urlPatterns) {
        networkTrafficInterceptor.startIntercepting();
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertTrue(trackingModal.isVisible());
        trackingModal.clickRejectButton();

        Assertion.assertTrue(trackingModal.areResponsesByUrlPatternSuccessful(urlPatterns, networkTrafficInterceptor));
    }

    /*
        MOBILE TESTS
    */

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
    @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries",
            groups = {"discussions-tracking-opt-in-mobile"})
    public void testModalVisibilityForAnonOnMobile(String continent, String country, boolean shouldGetModal) {
        TrackingOptInModal trackingModal = setGeoCookieAndNavigate(continent, country);
        Assertion.assertEquals(trackingModal.isVisible(), shouldGetModal);
    }

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER, trackingOptIn = false)
    @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries",
            groups = {"discussions-tracking-opt-in-mobile"})
    public void testModalVisibilityForLoggedInWhoNeverOptedInOnMobile(String continent, String country,
                                                              boolean shouldGetModal) {
        TrackingOptInModal trackingModal = setGeoCookieAndNavigate(continent, country);
        Assertion.assertEquals(trackingModal.isVisible(), shouldGetModal);
    }

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Test(groups = {"discussions-tracking-opt-in-mobile"})
    @Execute(asUser = User.USER)
    public void loggedInEUShouldNotGetModalIfOptedInOnMobile() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Test(groups = {"discussions-tracking-opt-in-mobile"})
    @Execute(asUser = User.ANONYMOUS)
    public void AnonInEUShouldNotGetModalIfOptedInOnMobile() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Test(groups = {"discussions-tracking-opt-in-mobile"})
    @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
    public void loggedInEUShouldNotGetModalIfOptedOutOnMobile() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Test(groups = {"discussions-tracking-opt-in-mobile"})
    @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
    public void AnonInEUShouldNotGetModalIfOptedOutOnMobile() {
        TrackingOptInModal trackingModal = setGeoCookieToGermanyAndNavigate();
        Assertion.assertFalse(trackingModal.isVisible());
    }

    /*
        HELPERS
    */

    private TrackingOptInModal setGeoCookieToGermanyAndNavigate() {
        TrackingOptInModal trackingModal = new TrackingOptInModal();
        trackingModal.setGeoCookie(driver, "EU", "DE");
        new PostsListPage().open();
        trackingModal.logTrackingCookieValue();

        return trackingModal;
    }

    private TrackingOptInModal setGeoCookieAndNavigate(String continent, String country) {
        TrackingOptInModal trackingModal = new TrackingOptInModal();
        trackingModal.setGeoCookie(driver, continent, country);
        new PostsListPage().open();
        trackingModal.logTrackingCookieValue();

        return trackingModal;
    }

}
