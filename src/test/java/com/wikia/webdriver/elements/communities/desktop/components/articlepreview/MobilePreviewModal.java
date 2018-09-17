package com.wikia.webdriver.elements.communities.desktop.components.articlepreview;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
  Mobile preview modal. Entry point for that preview could be find in the article's editor on Desktop.
  User is able to see how his/her page would look on mobile view.
 */

public class MobilePreviewModal extends WikiBasePageObject {

  public MobilePreviewModal() {
    super();
  }

  @FindBy(css = ".has-hero-image")
  private WebElement heroImage;

  @FindBy(css = ".portable-infobox")
  private WebElement portableInfobox;

  @FindBy(css = ".article-table")
  private WebElement articleTable;

  @FindBy(css = ".article-media-gallery")
  private WebElement articleGallery;

  @FindBy(css = "#Single_Image")
  private WebElement singleImage;

  @FindBy(css = ".article-video")
  private WebElement articleVideo;

  public boolean heroImageIsPresent() {
    return isVisible(heroImage);
  }

  public boolean infoboxIsPresent() {
    return isVisible(portableInfobox);
  }

  public boolean articleTableIsPresent() {
    return isVisible(articleTable);
  }

  public boolean mediaGalleryIsPresent() {
    return isVisible(articleGallery);
  }

  public boolean singleImageIsPresent() {
    return isVisible(singleImage);
  }

  public boolean singleVideoIsPresent() {
    return isVisible(articleVideo);
  }
}
