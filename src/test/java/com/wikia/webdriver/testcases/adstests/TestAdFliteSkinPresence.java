package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

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
    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_1.png",
                             "src/test/resources/adsResources/flite_skin_right_1.png",
                             null,
                             null);
    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton2);


    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_2.png",
                             "src/test/resources/adsResources/flite_skin_right_2.png",
                             null,
                             null);
    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton3);

    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_3.png",
                             "src/test/resources/adsResources/flite_skin_right_3.png",
                             null,
                             null);

    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton4);


    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_4.png",
                             "src/test/resources/adsResources/flite_skin_right_4.png",
                             null,
                             null);

    adsBaseObject.moveMouseToFliteButton(adsBaseObject.fliteAdButton1);


    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_1.png",
                             "src/test/resources/adsResources/flite_skin_right_1.png",
                             null,
                             null);

  }
}
