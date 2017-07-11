package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

  private By addFromPreviewButtonBy = By.cssSelector("#VET-add-from-preview");
  private By addVideoLibraryLink = By.cssSelector("figure + a");

  private String videoName;

  public VetAddVideoComponentObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  private void typeInUrl(String url) {
    wait.forElementVisible(urlField);
    urlField.sendKeys(url);
    PageObjectLogging.log("typeInUrl", url + " typed into url field", true);
  }

  private void clickAddButtonProvider() {
    wait.forElementVisible(addUrlButton);
    scrollAndClick(addUrlButton);
    PageObjectLogging.log("clickAddButton", "add url button clicked", true, driver);
  }

  private void typeInSearchQuery(String query) {
    wait.forElementVisible(findField);
    findField.sendKeys(query);
    PageObjectLogging.log("typeInSearchQuery",
                          query + " query typed into search video field", true);
  }

  private void clickFindButton() {
    wait.forElementVisible(findButton);
    scrollAndClick(findButton);
    PageObjectLogging.log("clickFindButton", "find button clicked", true, driver);
  }

  private void clickAddVideoLibrary(int videoListItem) {
    WebElement listElem = videoList.get(videoListItem);
    wait.forElementVisible(listElem);
    WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
    this.videoName = addVideoLink.getAttribute("title");
    wait.forElementPresent(addFromPreviewButtonBy);
    scrollAndClick(addFromPreviewButton);
    PageObjectLogging.log("clickAddVideoLibrary",
                          "add video button clicked: " + this.videoName, true, driver);
  }

  private void checkIfLibraryIsPresent() {
    wait.forElementVisible(libraryLIs);
    PageObjectLogging.log("checkIfLibraryIsPresent", "library carousel present", true);
  }

  public VetOptionsComponentObject addVideoByUrl(String url) {
    typeInUrl(url);
    clickAddButtonProvider();
    wait.forElementNotVisible(addUrlButton);

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
    wait.forElementVisible(videoPlayer);
    waitForValueToBePresentInElementsCssByCss("#VET-preview", "display", "block");
    PageObjectLogging.log("checkVideoPreviewAppearing", "video preview appeared", true);
  }

  public String getVideoName() {
    return this.videoName;
  }

  public boolean areSuggestionsDisplayed() {
    try {
      wait.forElementVisible(suggestedVideo);

      return true;
    } catch(TimeoutException e) {
      PageObjectLogging.logInfo("Suggestion are not displayed", e);

      return false;
    }
  }

  public WikiArticleEditMode clickCloseButton() {
    wait.forElementVisible(closeButton);
    scrollAndClick(closeButton);
    PageObjectLogging.log("clickCloseButton", "close button clicked", true);

    return new WikiArticleEditMode();
  }
}
