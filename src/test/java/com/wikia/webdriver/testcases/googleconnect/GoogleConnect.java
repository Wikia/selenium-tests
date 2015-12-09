package com.wikia.webdriver.testcases.googleconnect;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.GoogleConnectPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

public class GoogleConnect extends NewTestTemplate {

  @Test
  public void connectedUserCanLogInToWikiaByGoogle() {
    GoogleConnectPage googleConnectPage = new GoogleConnectPage(driver).open();

    googleConnectPage.signInWithGoogleAccount(Configuration.getCredentials().emailQaart4,
        Configuration.getCredentials().emailPasswordQaart4);

    Assertion.assertTrue(googleConnectPage.isUserLoggedIn());

    new ArticlePageObject(driver).open().verifyUserLoggedIn(User.GOOGLE_CONNECTED);
  }
}
