package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership Content X-Wing Wikia
 */
public class SectionItemListPageObject extends CuratedEditorFormPageObject {

  @FindBy(css = ".curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;
  @FindBy(css = ".curated-content-editor-row")
  private List<WebElement> item;

  By itemDisplayNameLocator = By.cssSelector(".title");

  public SectionItemListPageObject(WebDriver driver) {
    super(driver);
  }

  public EditorHomePageObject clickDone() {
    waitAndClick(doneButton);
    return new EditorHomePageObject(driver);
  }

  public CategoryFormPageObject clickAddCategory() {
    waitAndClick(addCategoryButton);
    return new CategoryFormPageObject(driver);
  }

  public void verifyItem(String itemDisplayName) {
    verifyTextInListElements(item, itemDisplayNameLocator, itemDisplayName);
  }

  public void waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);
  }
}
