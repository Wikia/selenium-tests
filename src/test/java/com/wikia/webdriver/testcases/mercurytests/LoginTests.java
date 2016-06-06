package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LoginTests extends NewTestTemplate {

    @Test(groups = "MercuryLoginTest_001")
    public void anonCanLogInAsRegularUser() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .getJoinButtons()
            .clickSignInButton()
            .getJoinButtons()
            .typeUsername(Configuration.getCredentials().userName10)
            .typePassword(Configuration.getCredentials().password10)
            .clickSignInButton()
            .getTopbar()
            .openNavigation()
            .isUserAvatarVisible()
        ;
    }

}