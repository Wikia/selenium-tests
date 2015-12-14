package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GalleryComponentObject extends BasePageObject {

  @FindBy(css = ".article-gallery img")
  private List<WebElement> galleryImagesArray;

  public GalleryComponentObject(WebDriver driver) {
    super(driver);
  }

  public LightboxComponentObject clickGalleryImage(int index) {
    wait.forElementVisible(galleryImagesArray.get(index));
    jsActions.scrollToElement(galleryImagesArray.get(index));
    galleryImagesArray.get(index).click();
    return new LightboxComponentObject(driver);
  }
}
