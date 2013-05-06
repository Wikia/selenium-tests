package com.wikia.webdriver.TestCases.CpmScrappers;

import com.wikia.webdriver.Common.DataProvider.CpmScrapperProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CpmScrapperPageObject.CpmScrapperPageObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class CpmStarTests extends TestTemplate {

    @Test(
	dataProviderClass=CpmScrapperProvider.class,
	dataProvider="getCpmStarUrl",
	groups = { "CpmStar", "CpmScrapper"}
    )
    public void cpmStarTest (String url) {
	CpmScrapperPageObject cpm = new CpmScrapperPageObject(driver, url);
	cpm.loginCpm(url, Properties.cpmUserName, Properties.cpmPassowrd);
	cpm.generateReportForYesterday();
	cpm.scrapeData();
    }
}
