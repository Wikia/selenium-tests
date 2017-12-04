package com.webdriver.common.templates;

import com.webdriver.common.core.elemnt.JavascriptActions;
import com.webdriver.common.core.url.Page;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoAd;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapVideos;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

public class TemplateNoFirstLoad extends NewTestTemplate {

  @Override
  protected void loadFirstPage() {
  }

  protected AdsBaseObject openPageWithVideoInLocalStorage(Page page, VideoAd videoAd) {
    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForWiki("project43"));
    putVASTToLocalStorage(videoAd.getVastXML());
    ads.getUrl(page);
    return ads;
  }

  protected AdsBaseObject openPageWithVideoInLocalStorage(Page page) {
    return openPageWithVideoInLocalStorage(page, VuapVideos.DEFAULT);
  }

  private void putVASTToLocalStorage(String vast) {
    JavascriptActions runScript = new JavascriptActions(driver);
    runScript.execute(String.format("localStorage.setItem('porvata_vast', '%s');", vast));
  }
}
