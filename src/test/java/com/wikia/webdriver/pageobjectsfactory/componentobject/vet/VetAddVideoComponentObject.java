package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.logging.LOG;
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
    wait.forElementVisible(urlField);
    urlField.sendKeys(url);
    LOG.log("typeInUrl", url + " typed into url field", LOG.Type.SUCCESS);
  }

  private void clickAddButtonProvider() {
    wait.forElementVisible(addUrlButton);
    scrollAndClick(addUrlButton);
    LOG.log("clickAddButton", "add url button clicked", true, driver);
  }

  private void typeInSearchQuery(String query) {
    wait.forElementVisible(findField);
    findField.sendKeys(query);
    LOG.log("typeInSearchQuery",
            query + " query typed into search video field", LOG.Type.SUCCESS);
  }

  private void clickFindButton() {
    wait.forElementVisible(findButton);
    scrollAndClick(findButton);
    LOG.log("clickFindButton", "find button clicked", true, driver);
  }

  private void clickAddVideoLibrary(int videoListItem) {
    WebElement listElem = videoList.get(videoListItem);
    wait.forElementVisible(listElem);
    WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
    this.videoName = addVideoLink.getAttribute("title");
    scrollAndClick(addFromPreviewButton);
    LOG.log("clickAddVideoLibrary",
            "add video button clicked: " + this.videoName, true, driver);
  }

  private void checkIfLibraryIsPresent() {
    wait.forElementVisible(libraryLIs);
    LOG.log("checkIfLibraryIsPresent", "library carousel present", LOG.Type.SUCCESS);
  }

  public void verifyAddVideoModal() {
    wait.forElementVisible(urlField);
    wait.forElementVisible(addUrlButton);
    LOG.log("verifyAddVideoModal", "add video modal is displayed", LOG.Type.SUCCESS);
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
    LOG.log("clickVideoThumbnail", "video thumbnails clicked", LOG.Type.SUCCESS);
  }

  private void checkVideoPreviewAppearing() {
    wait.forElementVisible(videoPlayer);
    waitForValueToBePresentInElementsCssByCss("#VET-preview", "display", "block");
    LOG.log("checkVideoPreviewAppearing", "video preview appeared", LOG.Type.SUCCESS);
  }

  public String getVideoName() {
    return this.videoName;
  }

  public void verifySuggestionsIsDisplayed() {
    wait.forElementVisible(suggestedVideo);
    LOG.log("verifySuggestionsIsDisplayed",
            "Verified suggested module appeared", true, driver);
  }

  public WikiArticleEditMode clickCloseButton() {
    wait.forElementVisible(closeButton);
    scrollAndClick(closeButton);
    LOG.log("clickCloseButton", "close button clicked", LOG.Type.SUCCESS);
    return new WikiArticleEditMode(driver);
  }
}
