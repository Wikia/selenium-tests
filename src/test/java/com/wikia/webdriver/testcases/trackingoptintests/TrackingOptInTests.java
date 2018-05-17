package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTests extends NewTestTemplate {

//    @Execute(trackingOptIn = "false")
    @NetworkTrafficDump(useMITM = false)
    @Test
    public void verifyTrackingForUserOptedIn() {
        TrackingOptInModal modal = new TrackingOptInModal();
        new PostsListPage().open();

        networkTrafficInterceptor.startIntercepting();
        Assertion.assertTrue(modal.isModalDisplayed());
        modal.clickAcceptButton();
        networkTrafficInterceptor.stop();
        List<HarEntry> entries = networkTrafficInterceptor.getHar().getLog().getEntries();


    }

}
