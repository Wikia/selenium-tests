package com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.curatededitorform;

import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.elements.communities.mobile.pages.curatedcontent.EditorHomePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SectionItemListPageObject extends CuratedEditorFormPageObject {

  @FindBy(css = ".curated-content-editor-add-item-btn")
  private WebElement addCategoryButton;
  @FindBy(css = ".curated-content-editor-row")
  private List<WebElement> item;

  private By itemDisplayNameLocator = By.cssSelector(".title");

  public EditorHomePageObject clickDone() {
    wait.forElementVisible(doneButton);
    doneButton.click();

    return new EditorHomePageObject();
  }

  public SectionItemListPageObject verifyItem(String itemDisplayName) {
    WebElement innerElem;

    for (WebElement element : item) {
      wait.forElementVisible(element);
      innerElem = element.findElement(itemDisplayNameLocator);

      if (innerElem.getText().equals(itemDisplayName)) {
        break;
      }
    }

    return this;
  }

  public CategoryFormPageObject clickAddCategory() {
    wait.forElementVisible(addCategoryButton);
    addCategoryButton.click();

    return new CategoryFormPageObject();
  }

  public SectionItemListPageObject waitForAddCategoryButtonToBeVisible() {
    wait.forElementVisible(addCategoryButton);

    return this;
  }
}
