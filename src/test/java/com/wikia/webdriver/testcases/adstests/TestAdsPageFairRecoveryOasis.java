package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.ImageEditor;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1050, 600);


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis(Page page, String urlParam, String imagePath) {

    String pp = urlBuilder.getUrlForWiki("project43");
    String url = urlBuilder.getUrlForPage(page);
    url = urlBuilder.appendQueryStringToURL(url, urlParam);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, pp, DESKTOP_SIZE);
    adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
//    try {
//      Thread.sleep(5000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    //adsRecoveryObject.verifyNoAdsOnPage();
    String firstSpanClass = driver.findElement(By.cssSelector("body>span")).getAttribute("class");
    List<WebElement> pageFairSpans= driver.findElements(By.cssSelector("body>span." + firstSpanClass));
    int spanCount = pageFairSpans.size();
    // spanCount with that class should be 101
    Assertion.assertEquals(101, spanCount);

    //List<WebElement> recoveredAds = driver.findElements(By.cssSelector("body>span." + firstSpanClass + ":visible"));
    List<WebElement> spans = driver.findElements(By.cssSelector("body>span." + firstSpanClass));


    List<WebElement> recoveredAds = spans
        .stream()
        .filter(WebElement::isDisplayed)
        .collect(Collectors.toList());
    ImageEditor imageEditor = new ImageEditor();

    File patternFile = new File("src/test/resources/adsResources/test3.png");
    BufferedImage patternImage = imageEditor.fileToImage(patternFile);
//    if (shouldCutPattern(patternImage.getWidth(), isLeft)) {
//      patternFile = getCutPattern(patternImage, isLeft);
//      patternImage = imageEditor.fileToImage(patternFile);
//    }

    Shooter shooter = new Shooter();
    ImageComparison imageComparison = new ImageComparison();
    int IMAGES_THRESHOLD_PERCENT = 12;

    for (WebElement ad : recoveredAds) {
      Dimension size = new Dimension(patternImage.getWidth(), patternImage.getHeight());
      PageObjectLogging.logImage("EXPECTED", patternFile, true);
      File actualFile = shooter.capturePageAndCrop(ad.getLocation(), size, driver);
      PageObjectLogging.logImage("ACTUAL", actualFile, true);
      BufferedImage actualImg = imageEditor.fileToImage(actualFile);
      Assertion.assertFalse(imageComparison.areImagesDifferent(patternImage, actualImg, IMAGES_THRESHOLD_PERCENT));
    }
  }
}

