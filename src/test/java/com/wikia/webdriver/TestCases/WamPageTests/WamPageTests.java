package com.wikia.webdriver.TestCases.WamPageTests;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Wam.WamPageObject;
import org.testng.annotations.Test;

public class WamPageTests extends TestTemplate {
    @Test(groups = {"WamPage001", "WamPageTests"})
    public void verifyDefaultPage() {
        WamPageObject pageObject = new WamPageObject(driver);
        pageObject.openWamPage();
        pageObject.verifyFirstTabSelected();
        pageObject.verifyWamIndexIsNotEmpty();
        pageObject.verifyWamIndexHasExactRowsNo( pageObject.DEFAULT_WAM_INDEX_ROWS );
    }

    @Test(groups = {"WamPage002", "WamPageTests"})
    public void verifyFilteringByVertical() {
        WamPageObject pageObject = new WamPageObject(driver);
        pageObject.openWamPage();
        pageObject.verifyWamIndexIsNotEmpty();
        pageObject.verifyWamVerticalFilterOptions();

        for( WamPageObject.VerticalsIds verticalId : WamPageObject.VerticalsIds.values() ) {
            pageObject.selectVertical( verticalId );
            pageObject.verifyWamIndexIsNotEmpty();
            pageObject.verifyVerticalColumnValuesAreTheSame();
        }
    }
}
