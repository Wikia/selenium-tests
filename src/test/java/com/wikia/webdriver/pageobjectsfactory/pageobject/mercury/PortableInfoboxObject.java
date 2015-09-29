package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightboxComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownshership: Content West-Wing
 */
public class PortableInfoboxObject extends BasePageObject {

  @FindBy(css = "body")
  private WebElement bodyElement;
  @FindBy(css = ".portable-infobox")
  private WebElement infoboxWrapper;
  @FindBy(css= ".pi-hero .article-image")
  private WebElement mainImage;
  @FindBy(css= ".portable-infobox .pi-hero-title")
  private WebElement title;
  @FindBy(css= ".portable-infobox .pi-title")
  private WebElement titleSmallImage;
  @FindBy(css= ".portable-infobox .pi-expand-button")
  private WebElement expandButton;
  @FindBy(css=".article-content .collapsed")
  private WebElement infoboxIsCollapsed;
  @FindBy(css=".tabber .article-image")
  private WebElement imageInTabber;
  @FindBy(css=".tabber figcaption")
  private WebElement captionInTabber;
  @FindBy(css=".portable-infobox .article-video")
  private WebElement video;
  @FindBy(css=".portable-infobox .article-video figcaption")
  private WebElement videoCaption;
  @FindBy(css=".pi-title img")
  private WebElement imageInTitle;
  @FindBy(css =".portable-infobox .new")
  private List<WebElement> internalLinksToEmptyArticle;
  @FindBy(css = ".portable-infobox a[href*='/wiki/']")
  private List<WebElement> internalLinks;
  @FindBy(css =".portable-infobox .external")
  private List<WebElement> externalLinks;
  @FindBy(css= ".pi-item .pi-data-label")
  private List<WebElement> dataLabels;
  @FindBy(css =".pi-item .pi-data-value")
  private List<WebElement> dataValues;
  @FindBy(css = ".portable-infobox .reference")
  private List<WebElement> references;
  @FindBy(css = ".portable-infobox ul li")
  private List<WebElement> unorderedLists;
  @FindBy(css = ".portable-infobox ol li")
  private List<WebElement> orderedLists;
  @FindBy(css = ".pi-header")
  private List<WebElement> headers;
  @FindBy(css = ".portable-infobox .pi-image img")
  private List<WebElement> images;

  public PortableInfoboxObject(WebDriver driver) {
    super(driver);
  }

  public String getExternalLinkName(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementVisible(externalLinks.get(index));
    return externalLinks.get(index).getText();
  }

  public String getUrlFromExternalLinkaAfterPageIsLoaded() {
    wait.forElementVisible(bodyElement);
    return driver.getCurrentUrl();
  }

  public PortableInfoboxObject tapInfoboxContent() {
    Assertion.assertFalse(dataLabels.isEmpty());
    dataLabels.get(0).click();
    return this;
  }

  public PortableInfoboxObject clickExpandButton() {
    wait.forElementVisible(expandButton);
    expandButton.click();
    return this;
  }

  public PortableInfoboxObject clickExternalLink(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementVisible(externalLinks.get(index));
    externalLinks.get(index).click();
    return this;
  }

  public PortableInfoboxObject closeLightbox() {
    new LightboxComponentObject(driver).clickCloseButton();
    return this;
  }

  public PortableInfoboxObject clickMainImage() {
    wait.forElementVisible(mainImage);
    mainImage.click();
    return this;
  }

  public PortableInfoboxObject clickVideo() {
    wait.forElementVisible(video);
    video.click();
    return this;
  }

  public PortableInfoboxObject isMainImageVisible() {
    wait.forElementVisible(mainImage);
    Assertion.assertEquals(isElementOnPage(mainImage), true);
    return this;
  }

  public PortableInfoboxObject isLightboxOpened() {
    Assertion.assertTrue(new LightboxComponentObject(driver).isLightboxOpened());
    return this;
  }

  public PortableInfoboxObject isTitleOverImageVisible() {
    wait.forElementVisible(title);
    Assertion.assertEquals(isElementOnPage(title), true);
    return this;
  }

  public PortableInfoboxObject isTitleAboveImageVisible() {
    wait.forElementVisible(titleSmallImage);
    Assertion.assertEquals(isElementOnPage(titleSmallImage), true);
    return this;
  }

  public PortableInfoboxObject isImageInTitleNotVisible() {
    Assertion.assertEquals(isElementVisible(imageInTitle), false);
    return this;
  }

  public PortableInfoboxObject isInfoboxCollapsed() {
    Assertion.assertEquals(isElementOnPage(infoboxIsCollapsed), true);
    return this;
  }

  public PortableInfoboxObject isInfoboxExpanded() {
    Assertion.assertEquals(infoboxWrapper.getAttribute("style"), "height: auto;");
    return this;
  }

  public PortableInfoboxObject isImageInTabberVisible() {
    Assertion.assertEquals(isElementVisible(imageInTabber), true);
    return this;
  }

  public PortableInfoboxObject isImageCaptionInTabberVisible() {
    Assertion.assertEquals(isElementVisible(captionInTabber), true);
    return this;
  }

  public PortableInfoboxObject isVideoVisible() {
    Assertion.assertEquals(isElementVisible(video), true);
    return this;
  }

  public PortableInfoboxObject isVideoCaptionVisible() {
    Assertion.assertEquals(isElementVisible(videoCaption), true);
    return this;
  }

  public PortableInfoboxObject isHeroImageCentered() {
    Assertion.assertEquals(images.get(0).getCssValue("text-align"), "center");
    return this;
  }

  public PortableInfoboxObject areUnorderedListsVisible() {
    Assertion.assertFalse(unorderedLists.isEmpty());
    return this;
  }

  public PortableInfoboxObject areOrderedListsVisible() {
    Assertion.assertFalse(orderedLists.isEmpty());
    return this;
  }

  public PortableInfoboxObject areHeadersVisible() {
    Assertion.assertFalse(headers.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyDataItemsVisibility() {
    Assertion.assertFalse(dataLabels.isEmpty());
    Assertion.assertFalse(dataValues.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyLinksVisibility() {
    Assertion.assertFalse(externalLinks.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyReferencesVisibility() {
    Assertion.assertFalse(references.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyExternalLinkNameAndURL(String name, String url) {
    Assertion.assertStringContains(url, name);
    return this;
  }

  public PortableInfoboxObject compareListsAndDataValuesMargin() {
    Assertion.assertEquals(
        unorderedLists.get(0).getCssValue("margin"),
        dataValues.get(0).getCssValue("margin")
    );

    Assertion.assertEquals(
        orderedLists.get(0).getCssValue("margin"),
        dataValues.get(0).getCssValue("margin")
    );
    return this;
  }
}
