package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.Map;

@Test(
        groups = "AdsVuapMercury"
)
public class TestAdsVuapMercury extends MobileTestTemplate {

    private static final String TOP_VIDEO_URL = "https://pubads.g.doubleclick.net/gampad/ads?output=vast&env=vp&gdfp_req=1&unviewed_position_start=1&iu=%2F5441%2Fwka.life%2F_project43%2F%2Farticle%2Fmobile%2FMOBILE_TOP_LEADERBOARD";


    @NetworkTrafficDump(useMITM = true)
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVUAPTopMercury",
            groups = "AdsTopAdVideoClosesWhenFinishPlaysMercury"
    )
    public void adsTopAdVideoClosesWhenFinishPlaysMercury(Page page, String slotName, String iframeId) {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, TOP_VIDEO_URL);

        VUAP vuap = new VUAP(driver, iframeId);
        vuap.play();

        vuap.waitForVideoPlayerVisible();
        vuap.waitForVideoPlayerHidden();
    }
}
