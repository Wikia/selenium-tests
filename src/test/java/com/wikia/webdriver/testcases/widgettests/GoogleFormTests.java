package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class GoogleFormTests extends NewTestTemplate {

    @Test
    @Execute(onWikia = "mercuryautomationtesting")
    public void GoogleFormWidgetTest_001_isLoaded() {
        GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

        googleFormWidget.createAndNavigate(wikiURL);
        Assertion.assertTrue(googleFormWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
    }

    @Test(groups = "PollsnackWidgetTest_003")
    @Execute(onWikia = "mercuryautomationtesting")
    public void GoogleFormWidgetTest_003_isErrorPresent() {
        GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

        googleFormWidget.createIncorrectAndNavigate(wikiURL);
        Assertion.assertTrue(googleFormWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
    }
}
