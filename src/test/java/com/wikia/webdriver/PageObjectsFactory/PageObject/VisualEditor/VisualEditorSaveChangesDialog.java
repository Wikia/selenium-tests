package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorSaveChangesDialog extends WikiBasePageObject {

	@FindBy(
			css=
			".ve-init-mw-viewPageTarget-saveDialog-slide-save" +
			" .ve-ui-flaggableElement-constructive[role='button']"
	)
	private WebElement saveButton;

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject savePage() {
		saveButton.click();
		return new ArticlePageObject(driver);
	}

}
