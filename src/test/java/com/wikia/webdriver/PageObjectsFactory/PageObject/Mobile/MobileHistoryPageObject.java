package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileHistoryPageObject extends MobileBasePageObject {

	public MobileHistoryPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="textarea")
	private WebElement textArea;
	@FindBy(css="#wkMainCntHdr > h1")
	private WebElement selectedPageHeader;
	@FindBy(css="#mw-content-text")
	private WebElement mainContentText;

	public void verifyEditModeContent(String text) {
		Assertion.assertStringContains(textArea.getText(), text);
	}
	
//	public boolean isNewPage() {
//		List<WebElement> mainContents = mainContentText.findElements(By.xpath("//*"));
//		return (mainContents.size() > 1) ? true : false;		
//	}
	
	public MobileEditModePageObject goToNewPageWithEdit(String URL) {
//		if (isNewPage()) {
			driver.get(URL);
			PageObjectLogging.log("goToNewPageWithEdit", 
					"going to edit mobile edit mode", true);
			return new MobileEditModePageObject(driver);
			
//		} else {
//			return null;
//		}
	}

}
