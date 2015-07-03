package com.wikia.webdriver.testcases.flagtests;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.flag.AddFlagsComponentObject;
import org.testng.annotations.Test;

/**
 * Created by Mariusz Czeszejko  2015-07-01
 * This test is adding flags with modal view and checking if added flags have the same ID
 * as in modal and check visibility added flags.
 * If some flag isn't visible test ends with fail.
 */
public class FlagTests extends NewTestTemplate {

    Credentials credentials = config.getCredentials();

    @Test(
            groups = {"FlagTests","Flag_Tests_001"})
    public void FlagTests_001_addFlag() {
        AddFlagsComponentObject flag = new AddFlagsComponentObject(driver);
        flag.logInCookie(credentials.userNameStaff, credentials.passwordStaff,
                URLsContent.EXTERNAL_RUNESCAPE);
        flag.openArticleByName(URLsContent.EXTERNAL_RUNESCAPE, URLsContent.FLAGTEST);
        flag.clickFlagButton();
        flag.selectFlagsInModal();
        flag.clickDoneButton();
        flag.checkFlagsEnabling();
        //If some flags aren't visible test is failing
        PageObjectLogging.log("Verify flags", "All flags are visible", true);
    }
}
