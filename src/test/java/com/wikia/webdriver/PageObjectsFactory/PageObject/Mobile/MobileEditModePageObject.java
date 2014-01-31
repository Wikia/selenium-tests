package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.MobilePageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileEditModePageObject extends MobileBasePageObject {

	public MobileEditModePageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#wkPreview")
	private WebElement editPreviewButton;
	@FindBy(css="#wpTextbox1")
	private WebElement textArea;
	@FindBy(css="#wkMobileCancel")
	private WebElement editCancelButton;

	private final String editPreviewButtonString = "#wkPreview";

	public MobileArticlePageObject clickCancel() {
		editCancelButton.click();
		return new MobileArticlePageObject(driver);
	}

	private String getHeader(){
		return selectedPageHeader.getText();
	}

	public MobileEditPreviewPageObject clickPreview() {
		WebElement editPreviewButton = waitForElementByCss(editPreviewButtonString);
		waitForElementClickableByElement(editPreviewButton);
		jQueryClick(editPreviewButton);
		return new MobileEditPreviewPageObject(driver);
	}

	private String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf(' '));
	}

	public void verifyModeName() {
		Assertion.assertEquals(MobilePageContent.editModeHeader, getModeName());
		PageObjectLogging.log(
				"verifyModeName",
				"verifying the header shows '" + MobilePageContent.editModeHeader + "'",
				true
		);
	}

	public void enterEditText(String text) {
		textArea.sendKeys(text);
	}

	private String getEditText() {
		return textArea.getAttribute("value");
	}

	public void verifyEditText(String targetText) {
		waitForValueToBePresentInElementsAttributeByElement(
				textArea, "value", targetText);
		Assertion.assertEquals(targetText, getEditText());
		PageObjectLogging.log("verifyEditText",
				"verifying the summary shows " + targetText, true);
	}
}
