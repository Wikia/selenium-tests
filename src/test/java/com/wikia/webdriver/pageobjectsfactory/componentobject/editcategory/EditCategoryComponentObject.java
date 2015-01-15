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

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class EditCategoryComponentObject extends BasePageObject {

	public EditCategoryComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".WikiaForm .categoryName > input")
	private WebElement categoryNameField;
	@FindBy(css = "#categorySelectEditModal button.primary")
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
