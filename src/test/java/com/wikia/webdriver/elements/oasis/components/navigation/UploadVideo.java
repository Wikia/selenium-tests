package com.wikia.webdriver.elements.oasis.components.navigation;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UploadVideo extends BasePageObject {

  @FindBy(xpath = "//span[text()='Explore']/../../..")
  private WebElement exploreButton;
  @FindBy(css = ".wds-button.addVideo")
  private WebElement addVideo;
  @FindBy(css = "#VideoEmbedUrl")
  private WebElement videoTextBox;
  @FindBy(css = "#VideoEmbedUrlSubmit")
  private WebElement submitVideo;

  public UploadVideo exploreButton() {
    new Actions(driver).moveToElement(exploreButton).perform();

    return this;
  }

  public UploadVideo clickAddVideo() {
    new Actions(driver).click(addVideo).perform();

    return this;
  }

  public UploadVideo addVideo(String videoUrl) {
    videoTextBox.sendKeys(videoUrl);
    submitVideo.click();

    return this;
  }
}
