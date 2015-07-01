package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Garth Webb
 * @author Saipetch Kongkatong
 */
public class SpecialCuratedContentPageObject extends SpecialPageObject {

  @FindBy(css = "#addItem")
  private WebElement addItemButton;
  @FindBy(css = "#save")
  private WebElement saveButton;
  @FindBy(css = "#save.ok")
  private WebElement successMark;
  @FindBy(css = "li.item:last-of-type .item-input")
  private WebElement lastElementNameInput;
  @FindBy(css = "li.item:last-of-type .name")
  private WebElement lastElementLabelInput;
  @FindBy(css = "li.item:last-of-type span.remove")
  private WebElement lastElementRemoveIcon;

  public SpecialCuratedContentPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }


  public void addNewElement(String name, String label) {
    waitForElementByElement(addItemButton);
    scrollAndClick(addItemButton);
    waitForElementByElement(lastElementNameInput);
    lastElementNameInput.sendKeys(name);
    waitForElementByElement(lastElementLabelInput);
    lastElementLabelInput.sendKeys(label);
  }

  public void clickSave() {
    waitForElementByElement(saveButton);
    scrollAndClick(saveButton);
  }

  public void verifySuccesfulSave() {
    waitForElementByElement(successMark);
  }

  public void removeLastElement(String label) {
    waitForElementByElement(lastElementRemoveIcon);
    scrollAndClick(lastElementRemoveIcon);
  }

  public PhotoAddComponentObject addImage() {
    return null;
  }

  public void verifyImageInElement(String label) {
  }
}
