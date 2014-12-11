package com.wikia.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * @author Bogna 'bognix' Knychala
 */
public class BaseMonoBookPageObject extends WikiBasePageObject {

	@FindBy(css = ".skin-monobook")
	protected WebElement monobookSkinClass;
	@FindBy(css = "body.oasis-oasis")
	protected WebElement oasisSkinClass;

	public BaseMonoBookPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void openWikiPageWithMonobook() {
		getUrl(Global.DOMAIN + URLsContent.NOEXTERNALS);
		PageObjectLogging.log(
			"OpenWikiPage",
			"Wiki Page opened with success", true
		);
		changeToMonoBook();
		PageObjectLogging.log(
			"logOut",
			"skin is changing for more then 30 seconds",
			true, driver
		);
	}

	public void changeToMonoBook() {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("?")) {
			getUrl(currentUrl + "&useskin=monobook");
		} else {
			getUrl(currentUrl + "?useskin=monobook");
		}
		verifySkinChanged();
	}

	public void verifySkinChanged() {
		waitForElementByElement(monobookSkinClass);
		PageObjectLogging.log(
			"skinChangedToMonoBook", "skin is changed to monobook", true
		);
	}
}
