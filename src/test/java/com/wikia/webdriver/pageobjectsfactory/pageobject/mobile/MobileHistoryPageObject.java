package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class MobileHistoryPageObject extends MobileBasePageObject {

	public MobileHistoryPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#pagehistory > li.mw-tag-mobileedit > span.mw-tag-markers")
	private List<WebElement> editHistoriesDevice;
	@FindBy(css = "#pagehistory > li.mw-tag-mobileedit > span.comment")
	private List<WebElement> editHistoriesSummary;

	private String getHeader() {
		return selectedPageHeader.getText();
	}

	private String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf('"') - 1);
	}

	public void verifyHistoryPageHeader(String targetText) {
		Assertion.assertStringContains(getModeName(), targetText);
		PageObjectLogging.log(
			"verifyHistoryPageHeader",
			"The summary shows " + targetText,
			true
		);
	}

	private String getLastEditHistoryDevice() {
		return editHistoriesDevice.get(0).getText();
	}

	public void verifyLastEditHistoryDevice(String targetText) {
		Assertion.assertStringContains(getLastEditHistoryDevice(), targetText);
		PageObjectLogging.log(
			"verifyLastEditHistoryDevice",
			"The last edit shows " + targetText,
			true
		);
	}

	public void verifyLastEditHistorySummary(String targetText) {
		Assertion.assertStringContains(getLastHistorySummary(), targetText);
		PageObjectLogging.log(
			"verifyLastEditHistorySummary",
			"The last edit summary shows " + targetText,
			true
		);
	}

	private String getLastHistorySummary() {
		return editHistoriesSummary.get(0).getText();
	}
}
