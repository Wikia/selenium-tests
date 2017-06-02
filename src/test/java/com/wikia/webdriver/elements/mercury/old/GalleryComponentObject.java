package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GalleryComponentObject {

  @FindBy(css = ".article-media-gallery img")
  private List<WebElement> galleryImagesArray;

  private WebDriver driver;
  private Wait wait;
  private JavascriptActions jsActions;

  public GalleryComponentObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public LightboxComponentObject clickGalleryImage(int index) {
    wait.forElementVisible(galleryImagesArray.get(index));
    jsActions.scrollToElement(galleryImagesArray.get(index));
    galleryImagesArray.get(index).click();
    return new LightboxComponentObject();
  }
}
