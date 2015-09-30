package com.wikia.webdriver.pageobjectsfactory.componentobject.rightrail;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class LatestPhotosComponentObject extends WikiBasePageObject {

  @FindBy(css = ".carousel li.thumbs")
  private List<WebElement> latestPhotosList;

  public LatestPhotosComponentObject(WebDriver driver) {
    super(driver);
  }

  public LightboxComponentObject openLightboxForImage(int imageNumber) {
    scrollAndClick(latestPhotosList.get(imageNumber));
    LOG.log("openLightboxForImage", "lightbox for image opened", LOG.Type.SUCCESS);
    return new LightboxComponentObject(driver);
  }
}
