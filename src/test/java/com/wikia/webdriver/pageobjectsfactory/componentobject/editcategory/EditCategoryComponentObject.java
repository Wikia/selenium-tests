/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class EditCategoryComponentObject extends BasePageObject {

  @FindBy(css = ".WikiaForm .categoryName > input")
  private WebElement categoryNameField;
  @FindBy(css = "#categorySelectEditModal button.primary")
  private WebElement saveButton;

  public EditCategoryComponentObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject editCategoryName(String newCategoryName) {
    wait.forElementVisible(categoryNameField);
    categoryNameField.clear();
    categoryNameField.sendKeys(newCategoryName);
    saveButton.click();
    LOG.success("editCategoryName", "category name changed to " + newCategoryName);
    return new ArticlePageObject(driver);
  }
}
