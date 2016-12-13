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

    private static final String FANDOM_URL = "http://www.wikia.com/fandom";
    private static final String URL = "pagead2.googlesyndication.com/pagead/gen_204?rt";
    private static final String SLOT_NAME = "slotName";
    private static final String SRC = "src";

    @NetworkTrafficDump
    @Test(
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsVUAPTopMercury",
            groups = "AdsTopAdVideoClosesWhenFinishPlaysMercury"
    )
    public void adsTopAdVideoClosesWhenFinishPlaysMercury(Page page, Map<String, String> map) {
        networkTrafficInterceptor.startIntercepting();
        AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
        ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL);


        VUAP vuap = new VUAP(driver, map.get(SRC), map.get(SLOT_NAME));
        vuap.play();

        vuap.waitForVideoPlayerVisible();
        vuap.waitForVideoPlayerHidden();
    }
}
