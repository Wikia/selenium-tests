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
        groups={"Ads", "Basic_Layout_001"}
    )
    public void TestUS(String page) {
        AdsBaseObject mainPage = new AdsBaseObject(driver, page);
        mainPage.openPage();
        mainPage.verifyTopLeaderBoardPresent();
    }
}
