package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;


@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTestsOasis extends NewTestTemplate {

    private static final Page GTA_WIKI_HOME_PAGE = new Page("gta", "Main_Page");

    @Execute(asUser = User.ANONYMOUS)
    @Test(groups = {"oasis-tracking-opt-in"},
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "GDPRcountries"
    )
    public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
        setGeoCookie(continent, country);
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        Assertion.assertEquals(modal.isVisible(), shouldGetModal);
    }

    @Execute(asUser = User.USER)
    @Test(groups = {"oasis-tracking-opt-in"},
            dataProviderClass = TrackingOptInDataProvider.class,
            dataProvider = "GDPRcountries"
    )
    public void testModalVisibilityForLoggedInWhoNeverOptedIn(String continent, String country, boolean shouldGetModal) {
        setGeoCookie(continent, country);
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        Assertion.assertEquals(modal.isVisible(), shouldGetModal);
    }

    @Test(groups = {"oasis-tracking-opt-in"})
    @Execute(asUser = User.USER)
    public void loggedInUserInEUShouldNotGetModalIfOptInAccepted() {
        setGeoCookie("EU", "DE");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        modal.clickAcceptButton();
        modal.refreshPage();

        Assertion.assertFalse(modal.isVisible());
    }

    @Test(groups = {"oasis-tracking-opt-in"})
    @Execute(asUser = User.USER)
    public void loggedInUserInEUShouldNotGetModalIfOptInRejected() {
        setGeoCookie("EU", "DE");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        modal.clickRejectButton();
        modal.refreshPage();

        Assertion.assertFalse(modal.isVisible());
    }

    @Test(groups = {"oasis-tracking-opt-in"})
    public void anonUserInEUShouldNotGetModalIfOptInAccepted() {
        setGeoCookie("EU", "DE");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        modal.clickAcceptButton();
        modal.refreshPage();

        Assertion.assertFalse(modal.isVisible());
    }

    @Test(groups = {"oasis-tracking-opt-in"})
    public void anonUserInEUShouldNotGetModalIfOptInRejected() {
        setGeoCookie("EU", "DE");
        TrackingOptInModal modal = new TrackingOptInModal();
        modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

        modal.clickRejectButton();
        modal.refreshPage();

        Assertion.assertFalse(modal.isVisible());
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
