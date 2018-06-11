package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.Log;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PortableInfoboxObject extends WikiBasePageObject{

  @FindBy(css = "body")
  private WebElement bodyElement;

  @FindBy(css = ".portable-infobox")
  private WebElement infoboxWrapper;

  @FindBy(css = ".portable-infobox .pi-data .article-media-thumbnail img")
  private WebElement imageInInfobox;

  @FindBy(css = ".portable-infobox .pi-expand-button")
  private WebElement expandButton;

  @FindBy(css = ".article-content .collapsed")
  private WebElement infoboxIsCollapsed;

  @FindBy(css = ".tabber .article-media-thumbnail")
  private WebElement imageInTabber;

  @FindBy(css = ".tabber figcaption")
  private WebElement captionInTabber;

  @FindBy(css = ".portable-infobox .article-video")
  private WebElement video;

  @FindBy(css = ".portable-infobox .article-media-thumbnail figcaption")
  private WebElement videoCaption;

  @FindBy(css = ".pi-image-collection")
  private WebElement imageInCollection;

  @FindBy(css = ".portable-infobox .article-media-linked-gallery img")
  private List<WebElement> galleryImageList;

  @FindBy(css = ".portable-infobox .article-media-linked-gallery button")
  private List<WebElement> galleryButtonList;

  @FindBy(css = ".portable-infobox .external")
  private List<WebElement> externalLinks;

  @FindBy(css = ".pi-item .pi-data-label")
  private List<WebElement> dataLabels;

  @FindBy(css = ".pi-item .pi-data-value")
  private List<WebElement> dataValues;

  @FindBy(css = ".portable-infobox .reference")
  private List<WebElement> references;

  @FindBy(css = ".portable-infobox ul li")
  private List<WebElement> unorderedLists;

  @FindBy(css = ".portable-infobox ol li")
  private List<WebElement> orderedLists;

  @FindBy(css = ".pi-header")
  private List<WebElement> headers;

  private Wait wait;
  private WebDriver driver;

  public PortableInfoboxObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
  }

  public String getExternalLinkName(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementVisible(externalLinks.get(index));

    return externalLinks.get(index).getText();
  }

  public String getUrlFromExternalLinkAfterPageIsLoaded() {
    wait.forElementVisible(bodyElement);

    return driver.getCurrentUrl();
  }

  public String getHeaderName(int index) {
    Assertion.assertFalse(headers.isEmpty());
    wait.forElementVisible(headers.get(index));

    return headers.get(index).getText();
  }

  public PortableInfoboxObject tapInfoboxContent() {
    Assertion.assertFalse(dataLabels.isEmpty());
    jsActions.scrollToElement(dataLabels.get(0));
    dataLabels.get(0).click();

    return this;
  }

  public PortableInfoboxObject clickExpandButton() {
    wait.forElementClickable(expandButton).click();

    return this;
  }

  public PortableInfoboxObject clickExternalLink(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementClickable(externalLinks.get(index)).click();

    return this;
  }

  public PortableInfoboxObject closeLightbox() {
    new LightboxComponentObject().clickCloseButton();

    return this;
  }

  public PortableInfoboxObject clickOnImageInInfobox() {
    wait.forElementClickable(imageInInfobox).click();

    return this;
  }

  public PortableInfoboxObject clickVideo() {
    wait.forElementClickable(video).click();

    return this;
  }

  public PortableInfoboxObject clickGalleryImage(int index) {
    wait.forElementClickable(galleryImageList.get(index)).click();

    return this;
  }

  public PortableInfoboxObject clickGalleryButton(int index) {
    wait.forElementClickable(galleryButtonList.get(index)).click();

    return this;
  }

  public PortableInfoboxObject clickNextImageArrow(int childIndex) {
    //'By' is needed here because we have to compute position of the nextImageArrow selector for each run.
    By nextImageArrow = By.cssSelector(".pi-image:nth-child(" + childIndex + ") .action-next");
    wait.forElementClickable(nextImageArrow)
        .click();

    return this;
  }

  public PortableInfoboxObject isLightboxOpened() {
    Assertion.assertTrue(new LightboxComponentObject().isLightboxOpened());

    return this;
  }

  public PortableInfoboxObject isImageInTitleNotVisible() {
    wait.forElementNotVisible(By.cssSelector(".pi-title img"));
    Log.log("Hero image title", MercuryMessages.INVISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isTitleNotVisible() {
    wait.forElementNotVisible(By.cssSelector(".portable-infobox .pi-title"));
    Log.log("Portable infobox title", MercuryMessages.INVISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isInfoboxCollapsed() {
    wait.forElementVisible(infoboxIsCollapsed);
    Log.log("Infobox", MercuryMessages.COLLAPSED_MSG, true);

    return this;
  }

  public PortableInfoboxObject isInfoboxExpanded() {
    Assertion.assertEquals(infoboxWrapper.getAttribute("style"), "height: auto;");
    Log.log("Infobox", MercuryMessages.EXPANDED_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageInCollectionVisible() {
    wait.forElementVisible(imageInCollection);
    Log.log("Image in collection", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageInTabberVisible() {
    wait.forElementVisible(imageInTabber);
    Log.log("Image in tabber", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageCaptionInTabberVisible() {
    wait.forElementVisible(captionInTabber);
    Log.log("Image caption in tabber", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isVideoVisible() {
    wait.forElementVisible(video);
    Log.log("Video", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isVideoCaptionVisible() {
    wait.forElementVisible(videoCaption);
    Log.log("Video caption", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isExternalLinkLabelInURL(String name, String url) {
    Assertion.assertStringContains(url, name);
    Log.log("External links", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areUnorderedListsVisible() {
    Assertion.assertFalse(unorderedLists.isEmpty());
    Log.log("Unordered list", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areOrderedListsVisible() {
    Assertion.assertFalse(orderedLists.isEmpty());
    Log.log("Ordered list", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areHeadersVisible() {
    Assertion.assertFalse(headers.isEmpty());
    Log.log("Headers", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areLinksVisible() {
    Assertion.assertFalse(externalLinks.isEmpty());
    Log.log("Links", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areReferencesVisible() {
    Assertion.assertFalse(references.isEmpty());
    Log.log("References", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areDataLabelsVisible() {
    Assertion.assertFalse(dataLabels.isEmpty());
    Log.log("Data labels", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areDataValuesVisible() {
    Assertion.assertFalse(dataValues.isEmpty());
    Log.log("Data values", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject verifyDataValueMargin() {
    Assertion.assertEquals(dataValues.get(0).getCssValue("margin"), "0px");
    Log.log("Data values", "have correct margin", true);

    return this;
  }

  public PortableInfoboxObject verifyListMargin() {
    Assertion.assertEquals(orderedLists.get(0).getCssValue("margin"), "0px 0px 8px 10px");
    Log.log("Ordered list ", "have correct margin", true);

    Assertion.assertEquals(unorderedLists.get(0).getCssValue("margin"), "0px 0px 8px 10px");
    Log.log("Unordered list ", "have correct margin", true);

    return this;
  }
}
