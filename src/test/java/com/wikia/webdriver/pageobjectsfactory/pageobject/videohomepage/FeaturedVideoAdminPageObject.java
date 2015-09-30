package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by Liz Lee on 6/18/14.
 */
public class FeaturedVideoAdminPageObject extends WikiBasePageObject {

  @FindBy(css = ".featured-video:first-child")
  private WebElement featuredVideoForm;
  @FindBy(css = ".vpt-form button[type=submit]")
  private WebElement saveButton;

  public FeaturedVideoAdminPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public FeaturedVideoAdminPageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName())
        + URLsContent.SPECIAL_VIDEO_PAGE_ADMIN);

    return this;
  }

  public VetAddVideoComponentObject clickAddVideo() {
    wait.forElementVisible(featuredVideoForm);
    WebElement addVideoButton = featuredVideoForm.findElement(By.cssSelector(".add-video-button"));
    addVideoButton.click();
    LOG.success("VetAddVideoComponentObject", "Add video button clicked");
    return new VetAddVideoComponentObject(driver);
  }

  public void verifyVideoAdded(String name) {
    verifyVideoTitleUpdated(name);
    verifyVideoDisplayTitleUpdated(name);
    LOG.success("verifyVideoAdded", "Video" + name + " was successfully added.");
  }

  public void verifyVideoTitleUpdated(String name) {
    wait.forElementVisible(featuredVideoForm);
    WebElement videoTitle = featuredVideoForm.findElement(By.cssSelector(".video-title"));
    String title = videoTitle.getText();
    Assertion.assertEquals(name, title);
    LOG.success("verifyVideoTitleUpdated", "Video title was updated");
  }

  public void verifyVideoDisplayTitleUpdated(String name) {
    wait.forElementVisible(featuredVideoForm);
    WebElement displayTitle = featuredVideoForm.findElement(By.cssSelector(".display-title"));
    String title = displayTitle.getAttribute("value");
    Assertion.assertEquals(name, title);
    LOG.success("verifyVideoDisplayTitleUpdated", "Video display title input was populated");
  }

  public LatestVideoAdminPageObject clickSaveFeaturedVideoForm(WebDriver driver) {
    scrollAndClick(saveButton);
    LOG.success("clickSaveFeaturedVideoForm", "Featured video form has been saved");
    return new LatestVideoAdminPageObject(driver);
  }
}
