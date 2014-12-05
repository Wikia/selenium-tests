package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
* @author Michal 'justnpT' Nowierski
*/
public class FacebookSettingsPageObject extends WikiBasePageObject{

	@FindBy(css = "#pageLogo")
	private WebElement pageLogo;
	@FindBy(css = "#pop_content .uiButtonConfirm")
	private WebElement removeButton;
	@FindBy(css = ".pop_container_advanced")
	private WebElement removeAppConfirmationModal;
	@FindBy(css = ".fbSettingsList ")
	private WebElement settingsList ;
	@FindBy(css = "#app-settings-page .fbApplicationsList li")
	private List<WebElement> applicationList;

	String removeAppSelector = "#application-li-%appID% .uiCloseButtonSmall";

	public FacebookSettingsPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLogo() {
		waitForElementByElement(pageLogo);
		PageObjectLogging.log("verifyPageLogo", "Page logo is present", true);
	}

	public void openApps() {
		appendToUrl(URLsContent.FACEBOOK_SETTINGS_APP_TAB);
		PageObjectLogging.log("openApps", "Apps tab opened", true);
	}

	/**
	 * This method removes App from facebook Apps, based on its ID.
	 */
	public void removeApp(String appID) {
		if (isAppPresent(appID)) {
			WebElement wikiAppRemoveButton = driver.findElement(
					By.cssSelector(
							removeAppSelector.replace("%appID%", appID)
							)
					);
			waitForElementByElement(wikiAppRemoveButton);
			wikiAppRemoveButton.click();
			waitForElementByElement(removeButton);
			removeButton.click();
			waitForElementNotVisibleByElement(removeAppConfirmationModal);
			waitForElementNotVisibleByElement(wikiAppRemoveButton);
			waitForElementByElement(settingsList);
			Assert.assertFalse(isAppPresent(appID));
			PageObjectLogging.log("removeApp", "App with id "+appID+" removed", true);
		} else {
			PageObjectLogging.log("removeApp", "App with id "+appID+" not found", true);
		}
	}

	/**
	 * This method verifies if App is present on facebook apps list.
	 * It searches for uniqe App id.
	 */
	private boolean isAppPresent(String appID) {
		boolean isPresent = false;
		if (!applicationList.isEmpty()) {
			for (WebElement elem : applicationList) {
				String elemId = elem.getAttribute("id");
				if (elemId.contains(appID)) {
					isPresent = true;
				}
			}
		}
		return isPresent;
	}
}
