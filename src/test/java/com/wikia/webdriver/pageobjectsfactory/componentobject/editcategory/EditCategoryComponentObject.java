/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCategoryComponentObject extends BasePageObject {

  @FindBy(css = ".WikiaForm .categoryName > input")
  private WebElement categoryNameField;
  @FindBy(css = "#categorySelectEditModal button.primary")
  private WebElement saveButton;

  public EditCategoryComponentObject(WebDriver driver) {
    super();
  }

  public ArticlePageObject editCategoryName(String newCategoryName) {
    wait.forElementVisible(categoryNameField);
    categoryNameField.clear();
    categoryNameField.sendKeys(newCategoryName);
    saveButton.click();
    wait.forElementNotVisible(categoryNameField);
    PageObjectLogging.log("editCategoryName", "category name changed to " + newCategoryName, true);
    return new ArticlePageObject();
  }
}
