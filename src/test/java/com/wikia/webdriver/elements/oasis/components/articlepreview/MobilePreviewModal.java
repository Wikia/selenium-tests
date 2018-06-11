package com.wikia.webdriver.elements.oasis.components.articlepreview;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;

public class MobilePreviewModal extends WikiBasePageObject {

  public MobilePreviewModal() {
    super();
  }

  public MobilePreviewModal heroImageIsPresent() {
    waitForElementToBePresent(".has-hero-image", "Hero image");

    return this;
  }

  public MobilePreviewModal infoboxIsPresent() {
    waitForElementToBePresent(".portable-infobox", "Portable infobox");

    return this;
  }

  public MobilePreviewModal articleTableIsPresent() {
    waitForElementToBePresent(".article-table", "Article table");

    return this;
  }

  public MobilePreviewModal mediaGalleryIsPresent() {
    waitForElementToBePresent(".article-media-gallery", "Media gallery");

    return this;
  }

  public MobilePreviewModal linkedMediaGalleryIsPresent() {
    waitForElementToBePresent(".article-media-linked-gallery", "Linked media gallery");

    return this;
  }

  public MobilePreviewModal singleImageIsPresent() {
    waitForElementToBePresent("#Single_Image", "Single image");

    return this;
  }

  public MobilePreviewModal singleVideoIsPresent() {
    waitForElementToBePresent(".article-video", "Single video");

    return this;
  }

  private void waitForElementToBePresent(String cssSelector, String elementName) {
    Log.info("Waiting for: \"" + elementName + "\", to be present");
    wait.forElementPresent(By.cssSelector(cssSelector));
    Log.info("\"" + elementName + "\", is present");
  }
}
