package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialAdminDashboardPageObject extends SpecialPageObject {

	   	@FindBy(css = ".specialcsstool")
	    private WebElement cssToolButton;

	    public SpecialAdminDashboardPageObject(WebDriver driver) {
	        super(driver);
	    }

	    public SpecialCssPageObject clickCssTool() {
	        waitForElementByElement(cssToolButton);
	        clickAndWait(cssToolButton);
	        PageObjectLogging.log("clickCssTool", "click on special CSS tool", true);
					return new SpecialCssPageObject(driver);
	}
}
