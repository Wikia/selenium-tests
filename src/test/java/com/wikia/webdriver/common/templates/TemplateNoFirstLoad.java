package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapVideos;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

public class TemplateNoFirstLoad extends NewTestTemplate {

  @Override
  protected void loadFirstPage() {
  }

  public AdsBaseObject openPageWithVideoInLocalStorage(Page page) {
    final AdsBaseObject ads = new AdsBaseObject(driver);
    ads.getUrl(urlBuilder.getUrlForWiki("project43"));
    JavascriptActions runScript = new JavascriptActions(driver);
    runScript.execute("localStorage.setItem('" + VuapVideos.PORVATA_VAST + VuapVideos.VAST_VIDEO + ");");
    ads.getUrl(page);
    return ads;
  }
}
