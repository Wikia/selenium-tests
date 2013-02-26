package com.wikia.webdriver.TestCases.PhalanxTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPhalanxPageObject;
import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class BlockPageTitleTests extends TestTemplate {

    private String phalanxSpecialPage = URLsContent.specialPhalanx;
    private String specialCreatePage = URLsContent.specialCreatePage;

    //@Test(groups = {"BlockPageTitle_001_SpecialPage", "Phalanx"})
    public void BlockPageTitle_001_SpecialPage () {
        CommonFunctions.logOut(driver);

        CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addStandardFilterForTitle();
        phalanx.testBlock(block);

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver, Global.DOMAIN);
        special.addDefaultContentWithTitle((String) block.get("content"));

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
    }
}
