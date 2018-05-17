package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.testng.annotations.Test;

@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTests extends NewTestTemplate {

    @Execute(trackingOptIn = "false", asUser = User.ANONYMOUS)
    @NetworkTrafficDump(useMITM = true)
    @Test
    public void verifyTrackingForUserOptedIn() {
        TrackingOptInModal modal = new TrackingOptInModal();
        new PostsListPage().open();

        networkTrafficInterceptor.startIntercepting();
        Assertion.assertTrue(modal.isModalDisplayed());
        modal.clickAcceptButton();

    }

}
