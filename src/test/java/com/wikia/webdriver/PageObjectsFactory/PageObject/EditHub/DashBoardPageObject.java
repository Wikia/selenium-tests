package com.wikia.webdriver.PageObjectsFactory.PageObject.EditHub;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPageObject extends BasePageObject {
	@FindBy(css = "#date-picker .ui-datepicker")
	protected WebElement edithub_dashboardCalendar;

	public DashBoardPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void openDashboard(String hubUrl) {
		getUrl(hubUrl + URLsContent.specialEditHub);
		PageObjectLogging.log("openDashboard", "dashboard opened", true);
	}

	public void verifyCalendarAppears() {
		waitForElementByElement(edithub_dashboardCalendar);
		PageObjectLogging.log("verifyCalendarAppears", "Curators calendar visible", true);
	}
}