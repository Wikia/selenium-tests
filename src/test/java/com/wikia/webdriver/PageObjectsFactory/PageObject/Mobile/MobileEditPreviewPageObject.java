package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;

public class MobileEditPreviewPageObject extends MobileBasePageObject {

	public MobileEditPreviewPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="textarea")
	private WebElement textArea;
	@FindBy(css="#wkMainCntHdr > h1")
	private WebElement selectedPageHeader;
	@FindBy(css="#wkContinueEditing")
	private WebElement keepEditingButton;
	@FindBy(css="#wkSave")
	private WebElement publishButton;
	@FindBy(css="#wkSummary")
	private WebElement summaryTextBox;

	public void verifyEditModeContent(String text) {
		Assertion.assertStringContains(textArea.getText(), text);
	}
	
	public MobileEditModePageObject clickKeepEditing() {
		keepEditingButton.click();
		return new MobileEditModePageObject(driver);
	}
	
	public String getHeader(){
		return selectedPageHeader.getText();
	}
	
	public MobileArticlePageObject clickPublish() {
		publishButton.click();
		return new MobileArticlePageObject(driver);
	}
}
