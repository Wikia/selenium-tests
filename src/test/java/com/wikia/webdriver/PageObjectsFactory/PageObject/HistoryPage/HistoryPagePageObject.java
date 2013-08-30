package com.wikia.webdriver.PageObjectsFactory.PageObject.HistoryPage;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DiffPage.DiffPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryPagePageObject extends BasePageObject {

	public HistoryPagePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css=".historysubmit")
	private List<WebElement> historySubmit;

	@FindBy(css=".WikiaPageHeaderDiffHistory h1 strong")
	private WebElement diffHeader;

	public void openFileHistoryPage(String articlePage) {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver);
		wikiObject.getUrl(urlBuilder.appendQueryStringToURL(articlePage, URLsContent.historyAction));
		waitForTextToBePresentInElementByElement(diffHeader, "History");
		PageObjectLogging.log("Open history page", "history page opened", true);
	}

	public DiffPagePageObject goToDiffPageFromHistoryPage() {
		historySubmit.get(0).click();
		waitForTextToBePresentInElementByElement(diffHeader, "Changes");
		return new DiffPagePageObject(driver);
	}
}
