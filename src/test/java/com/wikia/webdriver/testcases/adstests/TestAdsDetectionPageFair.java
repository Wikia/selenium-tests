package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPageFairObject;
import org.testng.annotations.Test;

public class TestAdsDetectionPageFair extends TemplateNoFirstLoad {

    @NetworkTrafficDump
    @Test(
        groups = "AdsDetectAdBlock",
        dataProviderClass = AdsDataProvider.class,
        dataProvider = "adsDetectionPageFair"
    )
    public void adsDetectAdBlock(Page page) {
        String url = AdsPageFairObject.addPageFairDetectionParam(page);
        networkTrafficInterceptor.startIntercepting();

        AdsPageFairObject adsBaseObject = new AdsPageFairObject(driver, url);
        adsBaseObject.assertPageFair(true, networkTrafficInterceptor);
    }

    @NetworkTrafficDump
    @Test(
            groups = "AdsDetectNoAdBlock",
            dataProviderClass = AdsDataProvider.class,
            dataProvider = "adsDetectionPageFair"
    )
    public void adsNotDetectAdBlock(Page page) {
        String url = AdsPageFairObject.addPageFairDetectionParam(page);
        networkTrafficInterceptor.startIntercepting();

        AdsPageFairObject adsBaseObject = new AdsPageFairObject(driver, url);
        adsBaseObject.assertPageFair(false, networkTrafficInterceptor);
    }
}
