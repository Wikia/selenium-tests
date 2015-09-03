package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.CategoryFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform.ItemFormPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class SectionItemListPageObject extends BasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement doneButton;
  @FindBy(css = ".curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;
  @FindBys(@FindBy(css = ".curated-content-editor-row"))
  private List<WebElement> item;

  By itemDisplayNameLocator = By.cssSelector(".title");

  public SectionItemListPageObject(WebDriver driver) {
    super(driver);
  }

  public EditorHomePageObject clickDone() {
    waitAndClick(doneButton);
    return new EditorHomePageObject(driver);
  }

  public ItemFormPageObject clickItem(int itemIndex) {
    waitAndClick(item.get(itemIndex));
    return new ItemFormPageObject(driver);
  }

  public CategoryFormPageObject clickAddCategory() {
    waitAndClick(addCategoryButton);
    return new CategoryFormPageObject(driver);
  }

  public void verifyItem(String itemDisplayName) {
    verifyTextInListElements(item, itemDisplayNameLocator, itemDisplayName);
  }
}
