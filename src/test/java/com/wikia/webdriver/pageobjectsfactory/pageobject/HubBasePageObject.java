package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

/**
 * @author Michal 'justnpT' Nowierski
 * @author Karol 'kkarolk' Kujawiak
 */
public class HubBasePageObject extends WikiBasePageObject {

  @FindBy(css = "ul.wikia-mosaic-thumb-region img")
  List<WebElement> mosaicSliderThumbRegionImages;
  @FindBy(css = "ul.wikia-mosaic-thumb-region img")
  List<WebElement> fromCommunityImage;
  @FindBy(css = "ul.wikiahubs-ftc-list div.wikiahubs-ftc-title a")
  List<WebElement> fromCommunityHeadlines;
  @FindBy(css = "div.wikiahubs-ftc-subtitle a")
  List<WebElement> fromCommunityWikinameAndUsernameFields;
  @FindBy(css = "ul.wikiahubs-ftc-list div.wikiahubs-ftc-creative")
  List<WebElement> fromCommunityQuatations;
  @FindBy(css = "#suggestArticleDialogModal a")
  private WebElement modalWrapperXCloseButton;
  @FindBy(css = "#suggestArticleDialogModal button.secondary")
  private WebElement modalWrapperCancelCloseButton;
  @FindBy(css = "button[id='suggestArticle']")
  private WebElement getPromotedButton;
  @FindBy(css = "#suggestArticleDialogModal")
  private WebElement suggestVideoOrArticleModal;
  @FindBy(css = "#suggestArticleDialogModal h3")
  private WebElement suggestVideoOrArticleModalTopic;
  @FindBy(css = "#suggestArticleDialogModal input")
  private WebElement suggestArticleWhatInput;
  @FindBy(css = "#suggestArticleDialogModal textarea")
  private WebElement suggestArticleWhyCooliInput;
  @FindBy(css = "#suggestArticleDialogModal button.primary")
  private WebElement submitButton;
  @FindBy(css = "#WikiHeader h1 img")
  private WebElement wordmarkImage;
  @FindBy(css = ".wikiabar-button[href$='Video_Games']")
  private WebElement videoGamesWikiaBarLink;
  @FindBy(css = ".wikiabar-button[href$='Entertainment']")
  private WebElement entertainmentWikiaBarLink;
  @FindBy(css = ".wikiabar-button[href$='Lifestyle']")
  private WebElement lifestyleWikiaBarLink;

  private String mosaicSliderLargeImageDescriptionString =
      "div.wikia-mosaic-slider-description[style*='1'] span.image-description b";

  public HubBasePageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyMosaicSliderImages() {
    for (WebElement thumbnail : mosaicSliderThumbRegionImages) {
      wait.forElementVisible(thumbnail);
    }
    LOG.logResult("verifyMosaicSliderImages",
                  "Verify that WikiaMosaicSlider has images",
                  true);
  }

  /**
   * Hover Over Image number 'n'on Mosaic Slider
   *
   * @param n number of the image n={1,2,3,4,5}
   */
  public void mosaicSliderHoverOverImage(int n) {
    if (n > 4) {
      throw new WebDriverException("please select image index between 0 and 4");
    }
    jsActions.mouseOver(mosaicSliderThumbRegionImages.get(n));
    LOG.log("mosaicSliderHoverOverImage", "hover over image number " + n, LOG.Type.SUCCESS);
  }

  /**
   * Get title of current LargeImage on Mosaic Slider
   */
  public String mosaicSliderGetCurrentLargeImageDescription() {
    return driver.findElement(By.cssSelector(mosaicSliderLargeImageDescriptionString)).getText();
  }

  /**
   * Verify that Large Image has changed (by verifying description change), and get the current
   * description
   *
   * @param n number of the image n={1,2,3,4,5}
   */
  public void mosaicSliderVerifyLargeImageDescriptionDifferent(
      String previousLargeImageDescription) {
    Assertion.assertNotEquals(mosaicSliderGetCurrentLargeImageDescription(),
                              previousLargeImageDescription);
  }

  /**
   * Click on getPromoted button
   */
  public void clickGetPromoted() {
    wait.forElementVisible(getPromotedButton);
    scrollToElement(getPromotedButton);
    wait.forElementClickable(getPromotedButton);
    scrollAndClick(getPromotedButton);
    LOG.log("clickGetPromoted", "Click on suggest an article button", LOG.Type.SUCCESS);
  }

  /**
   * Verify that suggest a video or article modal appeared
   */
  public void verifySuggestAVideoOrArticleModalAppeared() {
    wait.forElementVisible(suggestVideoOrArticleModal);
    LOG.log("verifySuggestAVideoOrArticleModalAppeared",
            "Verify that suggest a video modal appeared", LOG.Type.SUCCESS);
  }

  /**
   * Verify that suggest a video or an article modal has topic:
   */
  public void verifySuggestAVideoOrArticleModalTopic() {
    wait.forElementVisible(suggestVideoOrArticleModalTopic);
    LOG.log("verifySuggestAVideoOrArticleModalTopic",
            "Verify that suggest a video or an article", LOG.Type.SUCCESS);
  }

  /**
   * Click on [x] to close suggest a video or article modal
   */
  public void closeSuggestAVideoOrArticleByXButton() {
    closeModalWrapper();
    LOG.log("closeSuggestAVideoOrArticleByXButton",
            "Click on [x] to close suggest a video or article modal", LOG.Type.SUCCESS);
  }

  /**
   * Click on Cancel to close suggest a video or article modal
   */
  public void closeSuggestAVideoOrArticleCancelButton() {
    scrollAndClick(modalWrapperCancelCloseButton);
    LOG.log("closeSuggestAVideoOrArticleCancelButton",
            "Click on Cancel to close suggest a video or article modal", LOG.Type.SUCCESS);
  }

  /**
   * Close modal wrapper. Modal wrapper can be e.g 'video player' or 'suggest a video modal'.
   */
  private void closeModalWrapper() {
    scrollAndClick(modalWrapperXCloseButton);
  }

  /**
   * Verify that Suggest Video or Article submit button is disabled
   */
  public void verifySuggestVideoOrArticleButtonNotClickable() {
    wait.forElementVisible(submitButton);
    waitForElementNotClickableByElement(submitButton);
    LOG.logResult("verifySuggestVideoOrArticleButtonNotClickable",
                  "Verify that 'Suggest Video' or 'Article' submit button button is disabled",
                  true);
  }

  /**
   * Verify that Suggest Video or Article submit button is enabled
   */
  public void verifySuggestVideoOrArticleButtonClickable() {
    wait.forElementClickable(submitButton);
    LOG.log("verifySuggestVideoOrArticleButtonClickable",
            "Verify that Suggest Video or Article submit button is enabled", LOG.Type.SUCCESS);
  }

  /**
   * Type text into 'What Video' field on 'Suggest Video Modal'
   */
  public void suggestArticleTypeIntoWhatVideoField(String text) {
    suggestArticleWhatInput.sendKeys(text);
    LOG.logResult("suggestArticleTypeIntoWhatVideoField", "Type '" + text
                                                          + "' into 'What Video' field on 'Suggest Article Modal'",
                  true);
  }

  /**
   * Type text into 'Why cool' field on 'Suggest Video Modal'
   */
  public void suggestArticleTypeIntoWhyCoolField(String text) {
    suggestArticleWhyCooliInput.sendKeys(text);
    LOG.logResult("suggestArticleTypeIntoWhyCoolField", "Type '" + text
                                                        + "' into 'Why cool' field on 'Suggest Video Modal'",
                  true);
  }

  /**
   * Verify that from the community module has images
   */
  public void verifyFromModuleHasImages() {
    for (WebElement element : fromCommunityImage) {
      scrollToElement(element);
      wait.forElementVisible(element);
    }
    LOG.log("verifyFromModuleHasImages",
            "Verify that from the community module has images", LOG.Type.SUCCESS);
  }

  /**
   * Verify that from the communitz module has headline
   */
  public void verifyFromModuleHasHeadline() {
    for (WebElement element : fromCommunityHeadlines) {
      scrollToElement(element);
      wait.forElementVisible(element);
    }
    LOG.log("verifyFromModuleHasHeadline",
            "Verify that from the community module has headline", LOG.Type.SUCCESS);
  }

  /**
   * Verify that from the community module has username field
   */
  public void verifyFromModuleHasUserAndWikiField() {
    for (WebElement element : fromCommunityWikinameAndUsernameFields) {
      scrollToElement(element);
      wait.forElementVisible(element);
    }
    LOG.log("verifyFromModuleHasUserAndWikiField",
            "Verify that from the community module has username field", LOG.Type.SUCCESS);
  }

  /**
   * Verify that from the community module has a quatation
   */
  public void verifyFromModuleHasQuatation() {
    for (WebElement element : fromCommunityQuatations) {
      scrollToElement(element);
      wait.forElementVisible(element);
    }
    LOG.log("verifyFromModuleHasQuatation",
            "Verify that from the community module has a quatation", LOG.Type.SUCCESS);
  }

  public void verifySuggestAVideoOrArticleModalDisappeared() {
    waitForElementNotVisibleByElement(suggestVideoOrArticleModal);
    LOG.log("verifySuggestAVideoOrArticleModalDisappeared",
            "Verify that video 'suggest video or article' modal disppeared", LOG.Type.SUCCESS);
  }

  public void clickWikiaBarLink(HubName hubName) {
    WebElement element;
    switch (hubName) {
      case VIDEO_GAMES:
        element = videoGamesWikiaBarLink;
        break;
      case ENTERTAINMENT:
        element = entertainmentWikiaBarLink;
        break;
      case LIFESTYLE:
      default:
        element = lifestyleWikiaBarLink;
        break;
    }
    wait.forElementClickable(element);
    element.click();

    LOG.log("clickWikiaBarLink", "Click hub link in WikiaBar", LOG.Type.SUCCESS);
  }

  public void verifyHubTitle(HubName hubName) {
    wait.forElementVisible(wordmarkImage);
    String header;
    switch (hubName) {
      case VIDEO_GAMES:
        header = "Games Wiki";
        break;
      case ENTERTAINMENT:
        header = "Movies Hub Wiki";
        break;
      case LIFESTYLE:
      default:
        header = "LifestyleHub Wiki";
        break;
    }
    Assert.assertEquals(header, wordmarkImage.getAttribute("alt"));

    LOG.log("verifyHubTitle", "Verify title", LOG.Type.SUCCESS);
  }
}
