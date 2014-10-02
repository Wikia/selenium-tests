package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

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

	private By paramLabelBy = By.cssSelector(".ve-ui-mwParameterPage-label");
	private By paramInputBy = By.cssSelector(".ve-ui-mwParameterPage-field textarea");
	private By templateParamsBy = By.cssSelector(".ve-ui-mwParameterPage");

	private String templateParamsString = ".ve-ui-mwParameterPage";

	public VisualEditorEditTemplateDialog(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject clickGetInfoLink() {
		switchToIFrame();
		waitForElementByElement(getInfoLink);
		//Opens new tab to Template namespace
		getInfoLink.click();
		switchOutOfIFrame();
		return new ArticlePageObject(driver);
	}

	public void typeInParam(String paramName, String text) {
		switchToIFrame();
		if(checkIfElementOnPage(templateParamsBy)) {
			WebElement targetParam = getElementByChildText(templateParams, paramLabelBy, text);
			WebElement targetParamInput = targetParam.findElement(paramInputBy);
			targetParamInput.sendKeys(text);
			waitForValueToBePresentInElementsAttributeByElement(targetParamInput, "value", text);
		} else {
			throw new NoSuchElementException("This template has no param.");
		}
		switchOutOfIFrame();
	}

	public VisualEditorPageObject clickDone() {
		switchToIFrame();
		if(checkIfElementOnPage(templateParamsBy)) {
			waitForElementClickableByElement(doneButton);
			doneButton.click();
		} else {
			throw new NoSuchElementException("This template has no param.");
		}
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	public void switchToIFrame() {
		waitForElementVisibleByElement(templateDialog);
		super.switchToIFrame();
	}

	public VisualEditorPageObject clickCancel() {
		switchToIFrame();
		if(checkIfElementOnPage(templateParamsBy)) {
			waitForElementClickableByElement(cancelButton);
			cancelButton.click();
		} else {
			throw new NoSuchElementException("This template has no param.");
		}
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}
}
