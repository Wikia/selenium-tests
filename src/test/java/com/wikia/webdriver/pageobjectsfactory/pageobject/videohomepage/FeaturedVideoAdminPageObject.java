package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.FeaturedVideoContainer;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
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

  @Deprecated
  public VetAddVideoComponentObject clickAddVideo() {
    wait.forElementVisible(featuredVideoForm);
    WebElement addVideoButton = featuredVideoForm.findElement(By.cssSelector(".add-video-button"));
    addVideoButton.click();
    PageObjectLogging.log("VetAddVideoComponentObject", "Add video button clicked", true);
    return new VetAddVideoComponentObject(driver);
  }

  @Deprecated
  public void verifyVideoAdded(String name) {
    verifyVideoTitleUpdated(name);
    verifyVideoDisplayTitleUpdated(name);
    PageObjectLogging.log("verifyVideoAdded", "Video" + name + " was successfully added.", true);
  }

  @Deprecated
  public void verifyVideoTitleUpdated(String name) {
    wait.forElementVisible(featuredVideoForm);
    WebElement videoTitle = featuredVideoForm.findElement(By.cssSelector(".video-title"));
    String title = videoTitle.getText();
    Assertion.assertEquals(title, name);
    PageObjectLogging.log("verifyVideoTitleUpdated", "Video title was updated", true);
  }

  @Deprecated
  public void verifyVideoDisplayTitleUpdated(String name) {
    wait.forElementVisible(featuredVideoForm);
    WebElement displayTitle = featuredVideoForm.findElement(By.cssSelector(".display-title"));
    String title = displayTitle.getAttribute("value");
    Assertion.assertEquals(title, name);
    PageObjectLogging.log("verifyVideoDisplayTitleUpdated",
        "Video display title input was populated", true);
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
      FeaturedVideoContainer featuredVideoContainer = new FeaturedVideoContainer(driver, videoTileElement);
      videoContainerList.add(featuredVideoContainer);
    }
    return videoContainerList;
  }
}

