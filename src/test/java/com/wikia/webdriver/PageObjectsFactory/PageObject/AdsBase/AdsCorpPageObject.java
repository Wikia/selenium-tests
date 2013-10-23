package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsCorpPageObject extends AdsBaseObject {

	@FindBy(css="#CORP_TOP_LEADERBOARD")
	private WebElement corporateTopLeaderboard;
	@FindBy(css="#HUB_TOP_LEADERBOARD")
	private WebElement hubTopLeaderboard;

	public AdsCorpPageObject(WebDriver driver, String page) {
		super(driver, page);
	}

	public void verifyProviderAds(String providerName) {
		List<WebElement> slots = new ArrayList<WebElement>();
		if (checkIfElementOnPage(corporateTopLeaderboard)) {
			slots.add(corporateTopLeaderboard);
		}
		if (checkIfElementOnPage(hubTopLeaderboard)) {
			slots.add(hubTopLeaderboard);
		}
		if (slots.size() > 0) {
			verifyAdsFromProvider(providerName, slots);
		} else {
			PageObjectLogging.log("NoSlots", "No ad slots on page", true);
		}
	}
}
