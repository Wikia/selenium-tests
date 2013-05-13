package com.wikia.webdriver.PageObjectsFactory.PageObject.HistoryPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DiffPage.DiffPagePageObject;

public class HistoryPagePageObject extends BasePageObject {

	public HistoryPagePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".historysubmit")
	private WebElement historySubmit;
	
	@FindBy(css=".WikiaPageHeaderDiffHistory h1 strong")
	private WebElement diffHeader;

	public void openFileHistoryPage(String articlePage) {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver, Global.DOMAIN);
		wikiObject.getUrl(URLsContent.buildUrl(articlePage, URLsContent.historyAction));
		waitForTextToBePresentInElementByElement(diffHeader, "History");
		PageObjectLogging.log("Open history page", "history page opened", true);
	}
	
	public DiffPagePageObject goToDiffPageFromHistoryPage() {
		historySubmit.click();
		waitForTextToBePresentInElementByElement(diffHeader, "Changes");
		return new DiffPagePageObject(driver);
	}
}