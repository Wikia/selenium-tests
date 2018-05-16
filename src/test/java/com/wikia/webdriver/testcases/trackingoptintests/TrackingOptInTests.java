package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;
import org.testng.annotations.Test;

@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTests extends NewTestTemplate {

    @NetworkTrafficDump(useMITM = true)
    @Test
    public void verifyTrackingForUserOptedIn() {
        networkTrafficInterceptor.startIntercepting();
        new TrackingOptInModal().clickAcceptButton();

    }

}
