package com.wikia.webdriver.testcases.fandom;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.HubPage;
import org.testng.annotations.Test;

/**
 * Created by psko on 7/29/16.
 */

@Test(groups = {"Fandom", "Fandom_Hubpage"})
public class HubPageTest extends FandomTestTemplate {

    @Test
    public void hubPageDisplaysHero() {
        HubPage hubPage = new HubPage().open("Games");

        Assertion.assertTrue(hubPage.getHeroUnit().isDisplayed(), "Hero Image is not Displayed");
    }

}
