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
	private WebElement editArea;
	@FindBy(css="#wkMobileCancel")
	private WebElement editCancelButton;

	public MobileArticlePageObject clickCancel() {
		editCancelButton.click();
		return new MobileArticlePageObject(driver);
	}

	private String getHeader(){
		return selectedPageHeader.getText();
	}

	public MobileEditPreviewPageObject clickPreview() {
		waitForElementByElement(editPreviewButton);
		waitForElementClickableByElement(editPreviewButton);
		jQueryClick(editPreviewButton);
		return new MobileEditPreviewPageObject(driver);
	}

	private String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf(' '));
	}

	public void verifyModeName() {
		Assertion.assertEquals(MobilePageContent.EDITMODE_HEADER, getModeName());
		PageObjectLogging.log(
			"verifyModeName",
			"The header shows '" + MobilePageContent.EDITMODE_HEADER + "'",
			true
		);
	}

	public void typIntoEditArea(String text) {
		editArea.sendKeys(text);
	}

	private String getEditText() {
		return editArea.getAttribute("value");
	}

	public void verifyEditText(String targetText) {
		waitForValueToBePresentInElementsAttributeByElement(
			editArea, "value", targetText);
		Assertion.assertEquals(targetText, getEditText());
		PageObjectLogging.log(
			"verifyEditText",
			"The summary shows " + targetText,
			true);
	}
}
