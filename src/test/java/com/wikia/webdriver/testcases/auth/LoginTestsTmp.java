package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;


@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
@Test
public class LoginTestsTmp extends NewTestTemplate {

    private static final String EXPECTED_ERROR_MESSAGE =
      "We don't recognize these credentials. Try again or register a new account.";

    public void anonCanLogInAsRegisteredUser() {
        ArticlePage article = new ArticlePage();

            article.open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .navigateToSignIn()
            .login(Configuration.getCredentials().userName10,
              Configuration.getCredentials().password10);

        assertTrue(article.userLoggedInMobile(Configuration.getCredentials().userName10));
    }

    public void anonCanNotLogInWithInvalidPassword() {
        ArticlePage article = new ArticlePage();

        SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .navigateToSignIn();

    signIn.login(Configuration.getCredentials().userName10, "someinvalidpassw0rd");
    assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
    }

    public void anonCanNotLogInWithBlankPassword() {
        ArticlePage article = new ArticlePage();

        SignInPage signIn = article.open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .navigateToSignIn();

        signIn.login(Configuration.getCredentials().userName10, "someinvalidpassw0rd");
        assertTrue(signIn.submitButtonNotClickable());
    }

    public void anonCanNotLogInWithInvalidUsername() {
        SignInPage signIn = new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .navigateToSignIn();

        signIn.login(String.valueOf(DateTime.now().getMillis()), Configuration.getCredentials().password10);
        assertTrue(signIn.getError().contains(EXPECTED_ERROR_MESSAGE));
    }

    public void anonCanNotLogInWithBlankUsername() {
        SignInPage signIn = new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopbar()
            .openNavigation()
            .clickOnSignInRegisterButton()
            .navigateToSignIn();
            signIn.typePassword(Configuration.getCredentials().password10);
            assertTrue(signIn.submitButtonNotClickable());
    }

}
