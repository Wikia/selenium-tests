package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialEditHubPageObject extends BasePageObject {
	@FindBy(css = "#date-picker .ui-datepicker")
	private WebElement edithubDashboardCalendar;

	public SpecialEditHubPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyCalendarAppears() {
		waitForElementByElement(edithubDashboardCalendar);
		PageObjectLogging.log("verifyCalendarAppears", "Curators calendar visible", true);
	}
}
