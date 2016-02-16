package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialCuratedContentPageObject extends SpecialPageObject {

  @FindBy(css = "#addItem")
  private WebElement addItemButton;
  @FindBy(css = "#save")
  private WebElement saveButton;
  @FindBy(css = "#save.ok")
  private WebElement successfulSaveMark;
  @FindBy(css = "li.item:last-of-type .item-input")
  private WebElement lastElementNameInput;
  @FindBy(css = "li.item:last-of-type .name")
  private WebElement lastElementLabelInput;
  @FindBy(css = "li.item:last-of-type span.remove")
  private WebElement lastElementRemoveIcon;
  @FindBy(css = "li.item:last-of-type .image")
  private WebElement lastElementImage;
  @FindBy(css = "li.item:last-of-type .image[style]")
  private WebElement lastElementImageFilled;
  @FindBy(css = "li.item:last-of-type .image.error")
  private WebElement lastElementImageWithError;

  public SpecialCuratedContentPageObject(WebDriver driver) {
    super();
  }


  public void addNewElement(String name, String label) {
    wait.forElementVisible(addItemButton);
    scrollAndClick(addItemButton);
    wait.forElementVisible(lastElementNameInput);
    lastElementNameInput.sendKeys(name);
    wait.forElementVisible(lastElementLabelInput);
    lastElementLabelInput.sendKeys(label);
  }

  public void clickSave() {
    wait.forElementVisible(saveButton);
    scrollAndClick(saveButton);
  }

  public void verifySuccesfulSave() {
    wait.forElementVisible(successfulSaveMark);
    PageObjectLogging.log("verifySuccesfulSave", "Curated Content saved with success", true);
  }

  public void removeLastElement() {
    wait.forElementVisible(lastElementRemoveIcon);
    scrollAndClick(lastElementRemoveIcon);
  }

  public PhotoAddComponentObject clickImageOnLastElement() {
    wait.forElementVisible(lastElementImage);
    scrollAndClick(lastElementImage);
    return new PhotoAddComponentObject(driver);
  }

  public void verifyImageInLastElement() {
    wait.forElementVisible(lastElementImageFilled);
    PageObjectLogging.log("verifyImageInLastElement", "Image is present in the last element", true);
  }

  public void verifyImageErrorInLastElement() {
    wait.forElementVisible(lastElementImageWithError);
    PageObjectLogging.log("verifyImageErrorInLastElement", "Image shows error", true);
  }

  public void verifySaveNotClickable() {
    waitForElementNotClickableByElement(saveButton);
    PageObjectLogging.log("verifySaveNotClickable", "Save button is not clickable", true);
  }
}
