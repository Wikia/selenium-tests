/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class AdsOnCorporatePageTests extends AdsTestTemplate {

    @GeoEdgeProxy(country="US")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_001", "Ads", "US"}
    )
    public void TestCorporatePage_US(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="GB")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_002", "Ads", "GB"}
    )
    public void TestCorporatePage_GB(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="DE")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_003", "Ads", "DE"}
    )
    public void TestCorporatePage_DE(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="CA")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_004", "Ads", "CA"}
    )
    public void TestCorporatePage_CA(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="AU")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_005", "Ads", "AU"}
    )
    public void TestCorporatePage_AU(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="PL")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_006", "Ads", "PL"}
    )
    public void TestCorporatePage_PL(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="UK")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_007", "Ads", "UK"}
    )
    public void TestCorporatePage_UK(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }
}
