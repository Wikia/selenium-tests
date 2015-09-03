package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.curatededitorform;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.EditorHomePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents item editor Form
 * That object differs from Section Form:
 * Done button redirects you to main editor home
 * It has two text fields to fill instead of one
 * That object differs from Category Form:
 * It allows more namespaces than category
 *
 * @ownership: Content X-Wing
 */
public class ItemFormPageObject extends CuratedEditorFormPageObject {

  @FindBy(css = "input#title")
  private WebElement pageNameField;
  @FindBy(css = ".sub-head--done")
  private WebElement doneButton;

  public ItemFormPageObject(WebDriver driver) {
    super(driver);
  }

  public void typePageName(String pageName) {
    waitAndSendKeys(pageNameField, pageName);
  }

  public EditorHomePageObject clickDone() {
    waitAndClick(doneButton);
    return new EditorHomePageObject(driver);
  }
}
