package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileEditModePageObject extends MobileBasePageObject {

	public MobileEditModePageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#wpTextbox1")
	private WebElement textArea;
	@FindBy(css="#wkMainCntHdr > h1")
	private WebElement selectedPageHeader;
	@FindBy(css="#wkMobileCancel")
	private WebElement editCancelButton;
	@FindBy(css="#wkPreview")
	private WebElement editPreviewButton;

	public void verifyEditModeContent(String text) {
		Assertion.assertStringContains(textArea.getText(), text);
	}
	
	public MobileArticlePageObject clickCancel() {
		editCancelButton.click();
		return new MobileArticlePageObject(driver);
	}
	
	public String getHeader(){
		return selectedPageHeader.getText();
	}
	
	public MobileEditPreviewPageObject clickPreview() {
		editPreviewButton.click();
		return new MobileEditPreviewPageObject(driver);
	}
	
	public String getEditArticleName() {
		String header = getHeader();
		return header.substring(header.indexOf(' ') + 1);
	}
	
	public String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf(' '));
	}
	
	public void verifyEditArticleName() {
		String url = driver.getCurrentUrl();
		String urlArticleName =
			url.substring(url.indexOf(PageContent.articleNamePrefix), url.indexOf('?'));
		Assertion.assertEquals(urlArticleName, getEditArticleName());
		PageObjectLogging.log("verifyModeName", 
				"verifying the article shows '" + urlArticleName + "'", true);
	}
	
	public void verifyModeName() {
		Assertion.assertEquals("Editing", getModeName());
		PageObjectLogging.log("verifyModeName", 
				"verifying the header shows 'Editing'", true);
	}
	
	public void enterText(String text) {
		textArea.sendKeys(text);
	}
}
