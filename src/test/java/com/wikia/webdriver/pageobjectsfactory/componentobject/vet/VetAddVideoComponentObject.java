package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VetAddVideoComponentObject extends WikiBasePageObject {

  @FindBy(css = "#VideoEmbedUrl")
  private WebElement urlField;
  @FindBy(css = "#VideoEmbedUrlSubmit")
  private WebElement addUrlButton;
  @FindBy(css = "#VET-suggestions .carousel li")
  private WebElement libraryLIs;
  @FindBy(css = "#VET-search-field")
  private WebElement findField;
  @FindBy(css = "#VET-search-submit")
  private WebElement findButton;
  @FindBys(@FindBy(css = "#VET-suggestions .carousel li"))
  private List<WebElement> videoList;
  @FindBys(@FindBy(css = "#VET-suggestions .video-thumbnail"))
  private List<WebElement> videoThumbnailsList;
  @FindBy(css = "#VET-video-wrapper .Wikia-video-enabledEmbedCode")
  private WebElement videoPlayer;
  @FindBy(css = ".video-thumbnail")
  private WebElement suggestedVideo;
  @FindBy(css = "a.bottom-close-button")
  private WebElement closeButton;
  @FindBy(css = "#VET-add-from-preview")
  private WebElement addFromPreviewButton;

  private By addVideoLibraryLink = By.cssSelector("figure + a");

  private String videoName;

  public VetAddVideoComponentObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  private void typeInUrl(String url) {
    waitForElementByElement(urlField);
    urlField.sendKeys(url);
    PageObjectLogging.log("typeInUrl", url + " typed into url field", true);
  }

  private void clickAddButtonProvider() {
    waitForElementByElement(addUrlButton);
    scrollAndClick(addUrlButton);
    PageObjectLogging.log("clickAddButton", "add url button clicked", true, driver);
  }

  private void typeInSearchQuery(String query) {
    waitForElementByElement(findField);
    findField.sendKeys(query);
    PageObjectLogging.log("typeInSearchQuery",
                          query + " query typed into search video field", true);
  }

  private void clickFindButton() {
    waitForElementByElement(findButton);
    scrollAndClick(findButton);
    PageObjectLogging.log("clickFindButton", "find button clicked", true, driver);
  }

  private void clickAddVideoLibrary(int videoListItem) {
    WebElement listElem = videoList.get(videoListItem);
    waitForElementByElement(listElem);
    WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
    this.videoName = addVideoLink.getAttribute("title");
    scrollAndClick(addFromPreviewButton);
    PageObjectLogging.log("clickAddVideoLibrary",
                          "add video button clicked: " + this.videoName, true, driver);
  }

  private void checkIfLibraryIsPresent() {
    waitForElementByElement(libraryLIs);
    PageObjectLogging.log("checkIfLibraryIsPresent", "library carousel present", true);
  }

  public void verifyAddVideoModal() {
    waitForElementByElement(urlField);
    waitForElementByElement(addUrlButton);
    PageObjectLogging.log("verifyAddVideoModal", "add video modal is displayed", true);
  }

  public VetOptionsComponentObject addVideoByUrl(String url) {
    typeInUrl(url);
    clickAddButtonProvider();
    return new VetOptionsComponentObject(driver);
  }

  public VetOptionsComponentObject addVideoByQuery(String query, int i) {
    typeInSearchQuery(query);
    clickFindButton();
    checkIfLibraryIsPresent();
    clickVideoThumbnail(i);
    checkVideoPreviewAppearing();
    clickAddVideoLibrary(i);
    return new VetOptionsComponentObject(driver);
  }

  private void clickVideoThumbnail(int i) {
    scrollAndClick(videoThumbnailsList.get(i));
    PageObjectLogging.log("clickVideoThumbnail", "video thumbnails clicked", true);
  }

  private void checkVideoPreviewAppearing() {
    waitForElementByElement(videoPlayer);
    waitForValueToBePresentInElementsCssByCss("#VET-preview", "display", "block");
    PageObjectLogging.log("checkVideoPreviewAppearing", "video preview appeared", true);
  }

  public String getVideoName() {
    return this.videoName;
  }

  public void verifySuggestionsIsDisplayed() {
    waitForElementByElement(suggestedVideo);
    PageObjectLogging.log("verifySuggestionsIsDisplayed",
                          "Verified suggested module appeared", true, driver);
  }

  public WikiArticleEditMode clickCloseButton() {
    waitForElementByElement(closeButton);
    scrollAndClick(closeButton);
    PageObjectLogging.log("clickCloseButton", "close button clicked", true);
    return new WikiArticleEditMode(driver);
  }
}
