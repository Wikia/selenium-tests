package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Content
 */
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
    super(driver);
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
    LOG.success("verifySuccesfulSave", "Curated Content saved with success");
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
    LOG.success("verifyImageInLastElement", "Image is present in the last element");
  }

  public void verifyImageErrorInLastElement() {
    wait.forElementVisible(lastElementImageWithError);
    LOG.success("verifyImageErrorInLastElement", "Image shows error");
  }

  public void verifySaveNotClickable() {
    waitForElementNotClickableByElement(saveButton);
    LOG.success("verifySaveNotClickable", "Save button is not clickable");
  }
}
