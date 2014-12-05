/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditCategory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class EditCategoryComponentObject extends BasePageObject{

	public EditCategoryComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".WikiaForm .categoryName > input")
	private WebElement categoryNameField;
	@FindBy(css="#categorySelectEditModal button.primary")
	private WebElement saveButton;

	public ArticlePageObject editCategoryName(String newCategoryName) {
		waitForElementByElement(categoryNameField);
		categoryNameField.clear();
		categoryNameField.sendKeys(newCategoryName);
		saveButton.click();
		PageObjectLogging.log("editCategoryName", "category name changed to " + newCategoryName, true);
		return new ArticlePageObject(driver);
	}
}
