package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileHistoryPageObject extends MobileBasePageObject {

	public MobileHistoryPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#pagehistory > li.mw-tag-mobileedit")
	private List<WebElement> editHistories;
	@FindBy(css="#pagehistory > li.mw-tag-mobileedit > span.mw-tag-markers")
	private List<WebElement> editHistoriesDevice;
	@FindBy(css="#pagehistory > li.mw-tag-mobileedit > span.comment")
	private List<WebElement> editHistoriesSummary;

	private String getHeader(){
		return selectedPageHeader.getText();
	}

	private String getArticleName() {
		String header = getHeader();
		return header.substring(header.indexOf('"') + 1, header.length() - 1);
	}

	private String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf('"') - 1);
	}

	public void verifyArticleName(String targetText) {
		Assertion.assertStringContains(targetText, getArticleName());
		PageObjectLogging.log(
				"verifyArticleName",
				"verifying the article shows " + targetText,
				true
		);
	}

	public void verifyHistoryPageHeader(String targetText) {
		Assertion.assertStringContains(targetText, getModeName());
		PageObjectLogging.log(
				"verifyHistoryPageHeader",
				"verifying the summary shows " + targetText,
				true
		);
	}

	private String getLastEditHistoryDevice() {
		return editHistoriesDevice.get(0).getText();
	}

	public void verifyLastEditHistoryDevice(String targetText) {
		Assertion.assertStringContains(targetText, getLastEditHistoryDevice());
		PageObjectLogging.log(
				"verifyLastEditHistoryDevice",
				"verifying the last edit shows " + targetText,
				true
		);
	}

	public void verifyLastEditHistorySummary(String targetText) {
		Assertion.assertStringContains(targetText, getLastHistorySummary());
		PageObjectLogging.log(
				"verifyLastEditHistorySummary",
				"verifying the last edit summary shows " + targetText,
				true
		);
	}

	private String getLastHistorySummary() {
		return editHistoriesSummary.get(0).getText();
	}
}
