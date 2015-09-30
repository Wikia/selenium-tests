package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

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
    LOG.success("typeInUrl", url + " typed into url field");
  }

  private void clickAddButtonProvider() {
    wait.forElementVisible(addUrlButton);
    scrollAndClick(addUrlButton);
    LOG.success("clickAddButton", "add url button clicked", true);
  }

  private void typeInSearchQuery(String query) {
    wait.forElementVisible(findField);
    findField.sendKeys(query);
    LOG.success("typeInSearchQuery", query + " query typed into search video field");
  }

  private void clickFindButton() {
    wait.forElementVisible(findButton);
    scrollAndClick(findButton);
    LOG.success("clickFindButton", "find button clicked", true);
  }

  private void clickAddVideoLibrary(int videoListItem) {
    WebElement listElem = videoList.get(videoListItem);
    wait.forElementVisible(listElem);
    WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
    this.videoName = addVideoLink.getAttribute("title");
    scrollAndClick(addFromPreviewButton);
    LOG.success("clickAddVideoLibrary", "add video button clicked: " + this.videoName, true);
  }

  private void checkIfLibraryIsPresent() {
    wait.forElementVisible(libraryLIs);
    LOG.success("checkIfLibraryIsPresent", "library carousel present");
  }

  public void verifyAddVideoModal() {
    wait.forElementVisible(urlField);
    wait.forElementVisible(addUrlButton);
    LOG.success("verifyAddVideoModal", "add video modal is displayed");
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
    LOG.success("clickVideoThumbnail", "video thumbnails clicked");
  }

  private void checkVideoPreviewAppearing() {
    wait.forElementVisible(videoPlayer);
    waitForValueToBePresentInElementsCssByCss("#VET-preview", "display", "block");
    LOG.success("checkVideoPreviewAppearing", "video preview appeared");
  }

  public String getVideoName() {
    return this.videoName;
  }

  public void verifySuggestionsIsDisplayed() {
    wait.forElementVisible(suggestedVideo);
    LOG.success("verifySuggestionsIsDisplayed", "Verified suggested module appeared", true);
  }

  public WikiArticleEditMode clickCloseButton() {
    wait.forElementVisible(closeButton);
    scrollAndClick(closeButton);
    LOG.success("clickCloseButton", "close button clicked");
    return new WikiArticleEditMode(driver);
  }
}
