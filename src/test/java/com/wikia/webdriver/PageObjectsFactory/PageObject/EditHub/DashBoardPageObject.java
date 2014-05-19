package com.wikia.webdriver.PageObjectsFactory.PageObject.EditHub;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA.
 * User: kvas
 * Date: 15.03.13
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class DashBoardPageObject extends BasePageObject {
	public DashBoardPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void openDashboard(String hubUrl) {
		getUrl(hubUrl + URLsContent.specialEditHub);
		PageObjectLogging.log("openDashboard", "dashboard opened", true);
	}

	public void verifyCalendarAppears() {
		waitForElementByCss("#date-picker .ui-datepicker");
		PageObjectLogging.log("verifyCalendarAppears", "Curators calendar visible", true);
	}
}