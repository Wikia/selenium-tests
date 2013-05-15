package com.wikia.webdriver.PageObjectsFactory.PageObject.Wall;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WallPageObject extends BasePageObject {

	@FindBy(css="textarea#WallMessageTitle")
	private WebElement wallMessageTitle;

	public WallPageObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * Opens the message wall for a user.
	 *
	 * @param userName
	 */
	public void openWallPage(String userName) {
		getUrl(Global.DOMAIN+"wiki/Message_Wall:"+userName);
		waitForElementByElement(wallMessageTitle);
		PageObjectLogging.log("openWallPage", "message wall page opened", true);
	}

}
