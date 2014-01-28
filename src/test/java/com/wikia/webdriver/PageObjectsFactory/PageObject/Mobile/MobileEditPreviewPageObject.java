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

	@FindBy(css="#wkContinueEditing")
	private WebElement keepEditingButton;
	@FindBy(css="#wkSave")
	private WebElement publishButton;
	@FindBy(css="#wkSummary")
	private WebElement summaryTextBox;
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
		WebElement keepEditingButton = waitForElementByCss("#wkContinueEditing");
//		scrollAndClick(keepEditingButton);
		waitForElementClickableByElement(keepEditingButton);
		jQueryClick(keepEditingButton);
//		keepEditingButton.click();
		return new MobileEditModePageObject(driver);
	}

	public String getHeader(){
		return selectedPageHeader.getText();
	}

	public MobileArticlePageObject clickPublish() {
		publishButton.click();
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

	public String getSummaryText() {
//		waitForElementByBy(By.cssSelector("#wkSummary"));
//		waitForValueToBePresentInElementsAttributeByCss("#wkSummary", "value", PageContent.summaryText);
		WebElement summaryTextBox = waitForElementByCss("#wkSummary");
		return summaryTextBox.getAttribute("value");
	}

	public void verifySummaryText(String targetText) {
//		WebElement summaryTextBox = waitForElementByCss("#wkSummary");
//		waitForTextToBePresentInElementByElement(summaryTextBox, targetText);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertion.assertEquals(targetText, getSummaryText());
		PageObjectLogging.log("verifySummaryText",
				"verifying the summary shows " + targetText, true);
	}

}
