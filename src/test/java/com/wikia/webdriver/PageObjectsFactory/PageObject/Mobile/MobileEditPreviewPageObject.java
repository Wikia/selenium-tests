package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileEditPreviewPageObject extends MobileBasePageObject {

	public MobileEditPreviewPageObject(WebDriver driver) {
		super(driver);
	}

	private final String summaryTextString = "#wkSummary";
	private final String keepEditingButtonString = "#wkContinueEditing";
	private final String publishButtonString = "#wkSave";

	@FindBy(css=summaryTextString)
	private WebElement summaryTextBox;
	@FindBy(css=keepEditingButtonString)
	private WebElement keepEditingButton;
	@FindBy(css=publishButtonString)
	private WebElement publishButton;
	@FindBy(css="#wkMdlCnt #mw-content-text > p")
	private WebElement articleText;
	@FindBy(css="#wkMdlTlBar > span")
	private WebElement selectedPageHeader;

	public void verifyEditModeContent(String targetText) {
		Assertion.assertStringContains(targetText, getArticleText());
		PageObjectLogging.log("verifyEditModeContent",
				"verifying the article shows " + targetText, true);
	}

	public void verifyPreviewPageHeader(String targetText) {
		Assertion.assertStringContains(targetText, getPageHeaderText());
		PageObjectLogging.log("verifyPreviewPageHeader",
				"verifying the summary shows " + targetText, true);
	}

	public MobileEditModePageObject clickKeepEditing() {
		WebElement keepEditingButton = waitForElementByCss(keepEditingButtonString);
		waitForElementClickableByElement(keepEditingButton);
		jQueryClick(keepEditingButton);
		return new MobileEditModePageObject(driver);
	}

	public String getHeader(){
		return selectedPageHeader.getText();
	}

	public MobileArticlePageObject clickPublish() {
		WebElement publishButton = waitForElementByCss(publishButtonString);
		waitForElementClickableByElement(publishButton);
		jQueryClick(publishButton);
		return new MobileArticlePageObject(driver);
	}

	public String getArticleText() {
		return articleText.getText();
	}

	public String getPageHeaderText() {
		return selectedPageHeader.getText();
	}

	public void enterSummaryText(String text) {
		summaryTextBox.sendKeys(text);
	}

	public void verifySummaryText(String targetText) {
		waitForValueToBePresentInElementsAttributeByCss(
				summaryTextString, "value", targetText);
		PageObjectLogging.log("verifySummaryText",
				"verifying the summary shows " + targetText, true);
	}

}
