package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorEditTemplateDialog extends VisualEditorDialog {

	//outside of iframe
	@FindBy(css = ".ve-ui-wikiaTemplateGetInfoWidget-templateInfoButton a")
	private WebElement getInfoLink;
	@FindBy(css = ".ve-ui-mwParameterPage")
	private List<WebElement> templateParams;
	@FindBy(css=".oo-ui-flaggableElement-primary a")
	private WebElement doneButton;
	@FindBy(css=".ve-ui-wikiaTransclusionDialog-cancelButton a")
	private WebElement cancelButton;
	@FindBy(css = ".ve-ui-mwTemplateDialog-ready")
	private WebElement templateDialog;

	private static final By PARAM_LABEL_BY = By.cssSelector(".ve-ui-mwParameterPage-label");
	private static final By PARAM_INPUT_BY = By.cssSelector(".ve-ui-mwParameterPage-field textarea");
	private static final By TEMPLATE_PARAMS_BY = By.cssSelector(".ve-ui-mwParameterPage");

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
		if(checkIfElementOnPage(TEMPLATE_PARAMS_BY)) {
			WebElement targetParam = getElementByChildText(templateParams, PARAM_LABEL_BY, paramName);
			WebElement targetParamInput = targetParam.findElement(PARAM_INPUT_BY);
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
			if(checkIfElementOnPage(TEMPLATE_PARAMS_BY)) {
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
			if(checkIfElementOnPage(TEMPLATE_PARAMS_BY)) {
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
