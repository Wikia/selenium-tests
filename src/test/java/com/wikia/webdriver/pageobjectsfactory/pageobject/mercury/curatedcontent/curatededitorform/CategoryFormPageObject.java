package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.SectionItemListPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents category editor Form
 * That object differs from Section Form:
 * Done button may redirect you to main editor home or section items form
 * It has two text fields to fill instead of one
 * That object differs from Item Form:
 * Done button may redirect you to main editor home or section items form
 * It allows only category namespace
 *
 * @ownership: Content X-Wing
 */
public class CategoryFormPageObject extends CuratedEditorFormPageObject {

  @FindBy(css = "input#title")
  private WebElement pageNameField;
  @FindBy(css = ".sub-head--done")
  private WebElement doneButton;

  public CategoryFormPageObject(WebDriver driver) {
    super(driver);
  }

  public void typeCategoryName(String pageName) {
    waitAndSendKeys(pageNameField, pageName);
  }

  public SectionItemListPageObject clickDone() {
    waitAndClick(doneButton);
    return new SectionItemListPageObject(driver);
  }
}
