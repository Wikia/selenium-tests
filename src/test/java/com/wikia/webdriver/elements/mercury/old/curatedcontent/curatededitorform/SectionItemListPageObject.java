package com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform;

import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.EditorHomePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
    wait.forElementVisible(doneButton);
    doneButton.click();

    return new EditorHomePageObject(driver);
  }

  public CategoryFormPageObject clickAddCategory() {
    wait.forElementVisible(addCategoryButton);
    addCategoryButton.click();

    return new CategoryFormPageObject(driver);
  }

  public void verifyItem(String itemDisplayName) {
    WebElement innerElem;

    for (WebElement element : item) {
      wait.forElementVisible(element);
      innerElem = element.findElement(itemDisplayNameLocator);

      if (innerElem.getText().equals(itemDisplayName)) {
        return;
      }
    }
  }

  public void waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);
  }
}
