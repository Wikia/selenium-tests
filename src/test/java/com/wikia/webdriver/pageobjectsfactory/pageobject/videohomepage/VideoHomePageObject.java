package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by Liz Lee on 6/4/14.
 */


public class VideoHomePageObject extends WikiBasePageObject {

  @FindBy(css = ".featured-video-slider .bx-controls")
  private WebElement featuredModuleControls;
  @FindBys(@FindBy(css = "#featured-video-bxslider li"))
  private List<WebElement> featuredSlides;
  @FindBys(@FindBy(css = ".latest-videos-wrapper .carousel-wrapper"))
  private List<WebElement> latestVideoRows;

  public VideoHomePageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public VideoHomePageObject open(){
    getUrl(new UrlBuilder().getUrlForWiki(Configuration.getWikiName()));

    return this;
  }

  public void verifyFeaturedSliderInitialized() {
    wait.forElementVisible(featuredModuleControls);
    LOG
        .logResult("verifyFeaturedSliderInitialized", "Featured video slider has initialized", true);
  }

  public void verifyFeaturedSliderSlides(int count) {
    wait.forElementVisible(featuredSlides.get(0));
    Assertion.assertTrue(featuredSlides.size() >= count);
    LOG.log("verifyFeaturedSliderSlides",
            "At least " + count + "latest Videos modules have rendered", LOG.Type.SUCCESS);
  }

  public void verifyLatestVideosRows(int count) {
    wait.forElementVisible(latestVideoRows.get(0));
    Assertion.assertTrue(latestVideoRows.size() >= count);
    LOG
        .logResult("verifyLatestVideosRows",
                   "At least " + count + "latest Videos modules have rendered",
                   true);
  }
}
