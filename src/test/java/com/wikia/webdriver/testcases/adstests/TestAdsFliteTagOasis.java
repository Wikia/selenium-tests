package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @ownership: AdEngineering
 */
public class TestAdsFliteTagOasis extends TemplateNoFirstLoad {

  private static final String
      FLITE_TAG_BROKEN_WIDTH =
      "Invalid width of the flite unit was passed. Make sure you provide width parameter with numeric value.";
  private static final String
      FLITE_TAG_BROKEN_HEIGHT =
      "Invalid height of the flite unit was passed. Make sure you provide height parameter with numeric value.";
  private static final String
      FLITE_TAG_BROKEN_TAG =
      "Invalid guid parameter was passed. Provide valid guid or remove this tag from article's content.";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTag",
      groups = {"AdsFliteTagOasisCorrect", "AdsFliteTagOasis"}
  )
  public void adsFliteTagOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.checkFliteTag();
  }


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenWidth",
      groups = {"AdsFliteTagBroken", "AdsFliteTagOasis"}
  )
  public void adsFliteTagBrokenWidthOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.checkFliteTagBroken(FLITE_TAG_BROKEN_WIDTH);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenHeight",
      groups = {"AdsFliteTagBroken", "AdsFliteTagOasis"}
  )
  public void adsFliteTagBrokenHeightOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.checkFliteTagBroken(FLITE_TAG_BROKEN_HEIGHT);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteTagBrokenTag",
      groups = {"AdsFliteTagBroken", "AdsFliteTagOasis"}
  )
  public void adsFliteTagBrokenTagOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.checkFliteTagBroken(FLITE_TAG_BROKEN_TAG);
  }

}
