package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.Common.Templates.GeoEdgeProxy;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class BasicLayoutTests extends AdsTestTemplate {

    @GeoEdgeProxy(country="US")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="popularSites",
        groups={"Ads_Basic_Layout", "Ads_Basic_Layout_001"}
    )
    public void TestBasicLayout_US(String page) {
        AdsBaseObject mainPage = new AdsBaseObject(driver, page);
        mainPage.openPage();
        mainPage.verifyTopLeaderBoardPresent();
        mainPage.verifyMadrecPresent();;
    }

    @GeoEdgeProxy(country="DE")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="popularSites",
        groups={"Ads_Basic_Layout", "Basic_Layout_002"}
    )
    public void TestBasicLayout_DE(String page) {
        AdsBaseObject mainPage = new AdsBaseObject(driver, page);
        mainPage.openPage();
        mainPage.verifyTopLeaderBoardPresent();
        mainPage.verifyMadrecPresent();;
    }

    @GeoEdgeProxy(country="UK")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="popularSites",
        groups={"Ads_Basic_Layout", "Basic_Layout_003"}
    )
    public void TestBasicLayout_UK(String page) {
        AdsBaseObject mainPage = new AdsBaseObject(driver, page);
        mainPage.openPage();
        mainPage.verifyTopLeaderBoardPresent();
        mainPage.verifyMadrecPresent();;
    }

    @GeoEdgeProxy(country="CA")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="popularSites",
        groups={"Ads_Basic_Layout", "Basic_Layout_004"}
    )
    public void TestBasicLayout_CA(String page) {
        AdsBaseObject mainPage = new AdsBaseObject(driver, page);
        mainPage.openPage();
        mainPage.verifyTopLeaderBoardPresent();
        mainPage.verifyMadrecPresent();;
    }
}
