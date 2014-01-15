package com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
* @author Michal 'justnpT' Nowierski
*/
public class FacebookSettingsPageObject extends WikiBasePageObject{

	@FindBy(css = "#pageLogo")
	private WebElement pageLogo;
	@FindBy(css = "#application-li-112328095453510 .uiCloseButtonSmall")
	private WebElement wikiAppRemoveButton;
	@FindBy(css = "#pop_content .uiButtonConfirm")
	private WebElement removeButton;
	@FindBy(css = ".pop_container_advanced")
	private WebElement removeAppConfirmationModal;
	@FindBy(css = ".fbSettingsList ")
	private WebElement settingsList ;
	@FindBy(css = "#app-settings-page .fbApplicationsList li")
	private List<WebElement> applicationList;

	public FacebookSettingsPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLogo() {
		waitForElementByElement(pageLogo);
		PageObjectLogging.log("verifyPageLogo", "Page logo is present", true);
	}

	public void openApps() {
		appendToUrl(URLsContent.facebookSettingsAppTab);
		PageObjectLogging.log("openApps", "Apps tab opened", true);
	}

	/**
	 * This method removes Wikia App from facebook Apps.
	 */
	public void removeWikiaApp() {
		if (isWikiaAppPresent()) {
			waitForElementByElement(wikiAppRemoveButton);
			wikiAppRemoveButton.click();
			waitForElementByElement(removeButton);
			removeButton.click();
			waitForElementNotVisibleByElement(removeAppConfirmationModal);
			waitForElementNotVisibleByElement(wikiAppRemoveButton);
			waitForElementByElement(settingsList);
			Assert.assertFalse(isWikiaAppPresent());
			PageObjectLogging.log("removeWikiaApp", "Wikia app removed", true);
		}
		else {
			PageObjectLogging.log("removeWikiaApp", "Wikia app not found", true);
		}
	}

	/**
	 * This method verifies if Wikia App is present on facebook apps list.
	 * It searches for uniqe Wikia App id, defined in facebookWikiaAppID field.
	 */
	private boolean isWikiaAppPresent() {
		boolean isPresent = false;
		if (!applicationList.isEmpty()) {
			for (WebElement elem : applicationList) {
				String elemId = elem.getAttribute("id");
				if (elemId.contains(URLsContent.facebookWikiaAppID)) {
					isPresent = true;
				}
			}
		}
		return isPresent;
	}
}
