package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VetAddImageComponentObject extends WikiBasePageObject {

  public static final String PAGE_NAME = "AddImageModal";
  @FindBy(css = "#ImageUploadFile")
  private WebElement imageUploadTextBox;
  @FindBy(xpath = "//*[@value='Upload']")
  private WebElement uploadButton;
  @FindBy(css = "#ImageQuery")
  private WebElement findTextBox;
  @FindBy(xpath = "//*[@value='Find']")
  private WebElement findButton;
  @FindBy(css = ".close wikia-chiclet-button")
  private WebElement closeButton;
  private By addImageModalBy = By.cssSelector("#WMU_divWrapper");

  public VetAddImageComponentObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  private void typeInPath(String path) {
    wait.forElementVisible(imageUploadTextBox);
    imageUploadTextBox.sendKeys(path);
    PageObjectLogging.log(PAGE_NAME, path + " typed into path field", true);
  }

  private void clickUploadButton() {
    wait.forElementVisible(uploadButton);
    scrollAndClick(uploadButton);
    PageObjectLogging.log(PAGE_NAME, "Upload button clicked", true, driver);
  }

  public void uploadImage(String imagePath){
    typeInPath(imagePath);
    clickUploadButton();
    wait.forElementNotVisible(uploadButton);
    wait.forElementNotVisible(addImageModalBy);
  }
}
