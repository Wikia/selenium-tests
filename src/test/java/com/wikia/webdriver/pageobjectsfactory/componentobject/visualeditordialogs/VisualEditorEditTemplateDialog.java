package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

public class VisualEditorEditTemplateDialog extends VisualEditorDialog {

	//outside of iframe
	@FindBy(css = ".oo-ui-dialog-drag-handle.oo-ui-icon-grabber")
	private WebElement draggableHandle;
	@FindBy(css = ".ve-ui-mwTemplateDialog-filter input")
	private WebElement filterInput;
	@FindBy(css = ".ve-ui-wikiaTemplateGetInfoWidget-templateInfoButton a")
	private WebElement getInfoLink;
	@FindBy(css = ".ve-ui-mwParameterPage")
	private List<WebElement> templateParams;
	@FindBy(css = ".ve-ui-mwParameterPage .ve-ui-mwParameterPage-label")
	private List<WebElement> templateLabels;
	@FindBy(css=".oo-ui-flaggableElement-primary a")
	private WebElement doneButton;
	@FindBy(css=".ve-ui-wikiaTransclusionDialog-cancelButton a")
	private WebElement cancelButton;
	@FindBy(css=
		".oo-ui-flaggableElement-secondary:not(.ve-ui-mwTransclusionDialog-modeButton)" +
		":not(.ve-ui-wikiaTransclusionDialog-cancelButton) a")
	private WebElement updatePreviewButon;
	@FindBy(css = ".ve-ui-mwTemplateDialog-ready")
	private WebElement templateDialog;

	private static final By paramLabelBy = By.cssSelector(".ve-ui-mwParameterPage-label");
	private static final By paramInputBy = By.cssSelector(".ve-ui-mwParameterPage-field textarea");
	private static final By templateParamsBy = By.cssSelector(".ve-ui-mwParameterPage");

	public VisualEditorEditTemplateDialog(WebDriver driver) {
		super(driver);
	}

	@Override
	public void switchToIFrame() {
		waitForElementVisibleByElement(templateDialog);
		super.switchToIFrame();
	}

	@Override
	public void switchOutOfIFrame() {
		waitForElementNotVisibleByElement(templateDialog);
		super.switchOutOfIFrame();
	}

	public ArticlePageObject clickGetInfoLink() {
		switchToIFrame();
		try {
			waitForElementByElement(getInfoLink);
			//Opens new tab to Template namespace
			getInfoLink.click();
			return new ArticlePageObject(driver);
		} finally {
			switchOutOfIFrame();
		}
	}

	public void typeInParam(String paramName, String text) {
		switchToIFrame();
		if(checkIfElementOnPage(templateParamsBy)) {
			WebElement targetParam = getElementByChildText(templateParams, paramLabelBy, paramName);
			WebElement targetParamInput = targetParam.findElement(paramInputBy);
			targetParamInput.sendKeys(text);
			waitForValueToBePresentInElementsAttributeByElement(targetParamInput, "value", text);
		} else {
			throw new NoSuchElementException("This template has no param.");
		}
		PageObjectLogging.log("typeInParam", "Type " + text + " in the " + paramName + " field.", true, driver);
		switchOutOfIFrame();
	}

	public VisualEditorPageObject clickDone() {
		switchToIFrame();
		try {
			if(checkIfElementOnPage(templateParamsBy)) {
				waitForElementClickableByElement(doneButton);
				doneButton.click();
			} else {
				throw new NoSuchElementException("This template has no param.");
			}
			return new VisualEditorPageObject(driver);
		} finally {
			switchOutOfIFrame();
		}
	}

	public VisualEditorPageObject clickCancel() {
		switchToIFrame();
		try {
			if(checkIfElementOnPage(templateParamsBy)) {
				waitForElementClickableByElement(cancelButton);
				cancelButton.click();
			} else {
				throw new NoSuchElementException("This template has no param.");
			}
			return new VisualEditorPageObject(driver);
		} finally {
			switchOutOfIFrame();
		}
	}
}
