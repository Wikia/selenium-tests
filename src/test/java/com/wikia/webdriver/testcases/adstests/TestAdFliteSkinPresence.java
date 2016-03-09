package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAdFliteSkinPresence extends TemplateNoFirstLoad {


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteSkin",
      groups = "TestAdFliteSkinPresenceOasis"
  )
  public void TestAdFliteSkinPresence(String wikiName, String article,
                                      Dimension windowResolution) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(windowResolution.width), true);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, windowResolution);

    adsBaseObject.verifySkin(AdsBaseObject.FLITE_SKIN_LEFT_1,
                             AdsBaseObject.FLITE_SKIN_RIGHT_1,
                             null,
                             null);

    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton2);

    adsBaseObject.verifySkin(AdsBaseObject.FLITE_SKIN_LEFT_2,
                             AdsBaseObject.FLITE_SKIN_RIGHT_2,
                             null,
                             null);
    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton3);

    adsBaseObject.verifySkin(AdsBaseObject.FLITE_SKIN_LEFT_3,
                             AdsBaseObject.FLITE_SKIN_RIGHT_3,
                             null,
                             null);

    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton4);

    adsBaseObject.verifySkin(AdsBaseObject.FLITE_SKIN_LEFT_4,
                             AdsBaseObject.FLITE_SKIN_RIGHT_4,
                             null,
                             null);

    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton1);

    adsBaseObject.verifySkin(AdsBaseObject.FLITE_SKIN_LEFT_1,
                             AdsBaseObject.FLITE_SKIN_RIGHT_1,
                             null,
                             null);
  }
}
