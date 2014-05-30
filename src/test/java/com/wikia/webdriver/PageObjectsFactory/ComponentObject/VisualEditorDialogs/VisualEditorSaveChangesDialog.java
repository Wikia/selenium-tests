package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorSaveChangesDialog extends WikiBasePageObject {

	@FindBy(
		css=
		".oo-ui-widget.oo-ui-flaggableElement-constructive" +
		".oo-ui-buttonWidget .oo-ui-labeledElement-label"
	)
	private WebElement publishButton;
	@FindBy(css=".oo-ui-dialog-open .oo-ui-frame")
	private WebElement saveDialogIFrame;

	public VisualEditorSaveChangesDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject savePage() {
		waitForElementVisibleByElement(saveDialogIFrame);
		driver.switchTo().frame(saveDialogIFrame);
		waitForElementByElement(publishButton);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("savePage", "The 2nd Publish Button is clicked", true);
		waitForElementNotVisibleByElement(saveDialogIFrame);
		driver.switchTo().defaultContent();
		return new ArticlePageObject(driver);
	}

}
