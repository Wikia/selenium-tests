package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class FeaturedVideoContainer extends WikiBasePageObject {


  private By videoTitleTextBoxBy = By.cssSelector(".video-title");
  private By imageTitleTextBoxBy = By.cssSelector(".alt-thumb-name");
  private By addImageButtonBy = By.cssSelector(".media-uploader-btn");
  private By addVideoButtonBy = By.cssSelector(".add-video-button");
  private By addImageModalBy = By.cssSelector("#WMU_divWrapper");
  private By addVideoModalBy = By.cssSelector("#VideoEmbedBackWrapper");
  private By displayedTitleTextBoxBy = By.cssSelector(".display-title");
  private By descriptionTextBoxBy = By.cssSelector(".description");


  @Getter
  public WebElement parentElement;

  private WebElement videoTitleTextBox;
  private WebElement imageTitleTextBox;
  private WebElement addVideoButton;
  private WebElement addImageButton;
  private WebElement displayedTitleTextBox;
  private WebElement descriptionTextBox;


  public FeaturedVideoContainer(WebDriver driver, WebElement parentElement) {
    super();
    this.parentElement = parentElement;
//    PageFactory.initElements(driver, this);
    videoTitleTextBox = parentElement.findElement(videoTitleTextBoxBy);
    imageTitleTextBox = parentElement.findElement(imageTitleTextBoxBy);
    addVideoButton = parentElement.findElement(addVideoButtonBy);
    addImageButton = parentElement.findElement(addImageButtonBy);
    displayedTitleTextBox = parentElement.findElement(displayedTitleTextBoxBy);
    descriptionTextBox = parentElement.findElement(descriptionTextBoxBy);
  }

  public void scrollToContainer(){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    jsActions.scrollToElement(parentElement);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public String getTitle(){
    return videoTitleTextBox.getText();
  }

  public String getDescription(){
    return descriptionTextBox.getText();
  }

  public String getImageName(){
    return imageTitleTextBox.getText();
  }

  public String getVideoDisplayedName(){
    return displayedTitleTextBox.getText();
  }

  public VetAddVideoComponentObject clickAddVideo() {
    addVideoButton.click();
    PageObjectLogging.log("VetAddVideoComponentObject", "Add video button clicked", true);
    return new VetAddVideoComponentObject(driver);
  }

  public void verifyVideoAdded(String name) {
    verifyVideoTitleUpdated(name);
    verifyVideoDisplayTitleUpdated(name);
    PageObjectLogging.log("verifyVideoAdded", "Video" + name + " was successfully added.", true);
  }

  public void verifyVideoTitleUpdated(String name) {
    Assertion.assertEquals(getTitle(), name);
    PageObjectLogging.log("verifyVideoTitleUpdated", "Video title was updated", true);
  }

  public void verifyVideoDisplayTitleUpdated(String name) {
    Assertion.assertEquals(displayedTitleTextBox.getAttribute("value"), name);
    PageObjectLogging.log("verifyVideoDisplayTitleUpdated",
            "Video display title input was populated", true);
  }

  public VetAddImageComponentObject openAddImageModal() {
    new Actions(driver).moveToElement(addImageButton).perform();
    addImageButton.click();
    wait.forElementVisible(addImageModalBy);
    PageObjectLogging.log("VetAddVideoComponentObject", "Add image button clicked", true);
    return new VetAddImageComponentObject(driver);
  }

  public void verifyImageAdded(String imagePath) {
    File f = new File(imagePath);
    String expectedImageName = StringUtils.capitalize(f.getName().replace("_", " "));
    Assertion.assertEquals(expectedImageName, imageTitleTextBox.getText());
  }
}
