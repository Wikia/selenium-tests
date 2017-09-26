package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.FeaturedVideoContainer;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FeaturedVideoAdminPageObject extends WikiBasePageObject {

  @FindBy(css = ".featured-video:first-child")
  private WebElement featuredVideoForm;
  @FindBy(css = ".vpt-form button[type=submit]")
  private WebElement saveButton;
  @FindBy(xpath = "//button[contains(@class,'media-uploader-btn')][1]")
  private WebElement addImageButton;
  @FindBys(@FindBy(css = ".featured-video"))
  private List<WebElement> videoContainerElements;

  public FeaturedVideoAdminPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public FeaturedVideoAdminPageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName())
            + URLsContent.SPECIAL_VIDEO_PAGE_ADMIN);

    return this;
  }

  public LatestVideoAdminPageObject clickSaveFeaturedVideoForm(WebDriver driver) {
    scrollAndClick(saveButton);
    PageObjectLogging.log("clickSaveFeaturedVideoForm", "Featured video form has been saved", true);
    return new LatestVideoAdminPageObject(driver);
  }

  public List<FeaturedVideoContainer> getVideoContainers() {
    wait.forElementVisible(videoContainerElements.get(0));
    List<FeaturedVideoContainer> videoContainerList = new ArrayList<>();
    for (WebElement videoTileElement : videoContainerElements) {
      FeaturedVideoContainer featuredVideoContainer = new FeaturedVideoContainer(videoTileElement);
      videoContainerList.add(featuredVideoContainer);
    }
    return videoContainerList;
  }
}

