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
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_001"}
    )
    public void TestCorporatePage_US(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="UK")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_002"}
    )
    public void TestCorporatePage_UK(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="DE")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_003"}
    )
    public void TestCorporatePage_DE(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }

    @GeoEdgeProxy(country="CA")
    @Test (
        dataProviderClass=AdsDataProvider.class,
        dataProvider="corporatePages",
        groups={"Ads_Corporate_Page", "Ads_Corporate_Page_004"}
    )
    public void TestCorporatePage_CA(String page) throws Exception {
        AdsBaseObject wikiPage = new AdsBaseObject(driver, page);
	wikiPage.verifyNoAds();
    }
}
