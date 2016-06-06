package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.JoinPageObject;
import com.wikia.webdriver.elements.mercury.old.LoginPageObject;
import com.wikia.webdriver.elements.mercury.old.SignupPageObject;

import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class LoginTests extends NewTestTemplate {

    private static final String ERROR_MESSAGE =
        "We don't recognize these credentials. Try again or register a new account.";

    @Test(groups = "MercuryLoginTest_001")
    @RelatedIssue(issueID = "SOC-2567")
    public void anonCanLogInAsRegularUser() {
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getNavigation()
            .open()
            .clickOnSignInRegisterButton()
            .logUserIn(
                Configuration.getCredentials().userName10,
                Configuration.getCredentials().password10)
            .verifySomeExpectedBehaviour;


        new Navigate().toPage(MercurySubpages.MAP);
        String url = driver.getCurrentUrl();
        new Navigation(driver).clickOnSignInRegisterButton();
        new LoginPageObject(driver).clickOnSignInButton().logUserIn(
            Configuration.getCredentials().userName10,
            Configuration.getCredentials().password10);

        new ArticlePageObject(driver).waitForFooterToBeVisible();
        boolean result = url.equals(driver.getCurrentUrl());
        PageObjectLogging.log("url", "was redirected correctly", result);

//    Assertion.assertTrue(nav.isUserLoggedIn(Configuration.getCredentials().userName10));
    }