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
			".oo-ui-widget.oo-ui-flaggableElement-constructive" +
			".oo-ui-buttonWidget.oo-ui-pushButtonWidget"
	)
	private WebElement publishButton;
	@FindBy(css=".oo-ui-frame")
	private WebElement saveDialogIFrame;

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject savePage() {
		driver.switchTo().frame(saveDialogIFrame);
		publishButton.click();
		driver.switchTo().defaultContent();
		return new ArticlePageObject(driver);
	}

}
