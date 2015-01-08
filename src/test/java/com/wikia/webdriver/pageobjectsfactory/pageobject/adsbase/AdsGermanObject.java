package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsGermanObject extends AdsBaseObject {

	private static final String IVW2_SCRIPT = "script.ioam.de";
	private static final String JS_SKIN_CALL = "top.loadCustomAd({type:\"skin\",destUrl:\"";

	public AdsGermanObject(WebDriver driver, String page) {
		super(driver);
		getUrl(page);
		setSlots();
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}


	public AdsGermanObject(
		WebDriver driver,
		String page,
		NetworkTrafficInterceptor networkTrafficInterceptor
	) {
		super(driver, page, networkTrafficInterceptor);
	}

	/*
	 * List of all possible combinations for 71M ads with their characteristic slots
	 */
	private List<Map<String, Object>> combinations = new ArrayList<>();

	private void setSlots() {
		Map<String,Object> billboardMap = new HashMap<String, Object>();
		Map<String,Object> fireplaceMap = new HashMap<String, Object>();
		Map<String,Object> flashtalkingMap = new HashMap<String, Object>();
		Map<String,Object> wp_internMap = new HashMap<String, Object>();
		Map<String,Object> leaderboardMap = new HashMap<String, Object>();
		Map<String,Object> medrecMap = new HashMap<String, Object>();
		Map<String,Object> prefooterMap = new HashMap<String, Object>();

		List<String> billboard = new ArrayList<String>();
		List<String> fireplace = new ArrayList<String>();
		List<String> flashtalking = new ArrayList<String>();
		List<String> wp_intern = new ArrayList<String>();
		List<String> leaderboard = new ArrayList<String>();
		List<String> medrec = new ArrayList<String>();
		List<String> prefooter = new ArrayList<String>();

		billboard.add("#ad-skyscraper1-outer");
		billboardMap.put("name", "billboard");
		billboardMap.put("slots", billboard);

		fireplace.add("#soi_wp_skyscraper2_outer");
		fireplace.add("#soi_wp_skyscraper1_outer");
		fireplaceMap.put("name", "fireplace");
		fireplaceMap.put("slots", fireplace);

		flashtalking.add("#ftwallpaper");
		flashtalkingMap.put("name", "flashtalking");
		flashtalkingMap.put("slots", flashtalking);

		wp_intern.add("#soi_wp_skyscraper1_outer");
		wp_internMap.put("name", "wp_intern");
		wp_internMap.put("slots", wp_intern);

		leaderboard.add("#ad-fullbanner2-outer");
		leaderboardMap.put("name", "leaderboard");
		leaderboardMap.put("slots", leaderboard);

		medrec.add("#ad-rectangle1");
		medrecMap.put("name", "medrec");
		medrecMap.put("slots", medrec);

		prefooter.add("#ad-promo1");
		prefooterMap.put("name", "prefooter");
		prefooterMap.put("slots", prefooter);

		combinations.add(billboardMap);
		combinations.add(fireplaceMap);
		combinations.add(flashtalkingMap);
		combinations.add(wp_internMap);
		combinations.add(leaderboardMap);
		combinations.add(medrecMap);
		combinations.add(prefooterMap);

	}

	public void verify71MediaAdsPresent() {
		AdsComparison adsComparison = new AdsComparison();

		for (Map<String,Object> combination: combinations) {
			List<String> combinationSlots = (List)combination.get("slots");
			if (checkIfCombinationOnPage(combinationSlots)) {
				PageObjectLogging.log(
					"Combination present",
					"Combination present: " + combination.get("name"),
					true,
					driver
				);
				for (String slotSelector : combinationSlots) {
					WebElement slot = driver.findElement(By.cssSelector(slotSelector));
					if (hasSkin(slot, slotSelector) || adsComparison.isAdVisible(slot, slotSelector, driver)) {
						PageObjectLogging.log(
								"Ad in slot found",
								"Ad in slot found; CSS: " + slotSelector,
								true
						);
					} else {
						throw new NoSuchElementException("Ad in slot not found; CSS: " + slotSelector);
					}
				}
				return;
			}
		}

		throw new NoSuchElementException("No known combination from 71 media present");
	}

	public void verifyNo71MediaAds() {
		PageObjectLogging.log("PageOpened", "Page opened", true, driver);
		for (Map<String,Object> combination: combinations) {
			List<String> combinationSlots = (List)combination.get("slots");
			if (!checkIfCombinationOnPage(combinationSlots)) {
				PageObjectLogging.log(
					"Combination not present",
					"Combination not present: " + combination.get("name"),
					true
				);
			} else {
				for (String elementSelector: combinationSlots) {
					WebElement combinationElement = driver.findElement(By.cssSelector(elementSelector));
					if (combinationElement.isDisplayed()) {
						PageObjectLogging.log(
							"Combination present",
							"Combination present: " + combination.get("name"),
							false
						);
						throw new WebDriverException("Ads found on page");
					}
				}
			}
		}
	}

	private boolean checkIfCombinationOnPage(List<String> combination) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return $(arguments[0]).find('iframe, object, img').filter(':visible').length;";

		for (String elementSelector: combination) {
			if (checkIfElementOnPage(elementSelector)) {
				if ((Long)js.executeScript(script, elementSelector) < 1) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public void verifyCallToIVW2Issued() {
		if (networkTrafficInterceptor.searchRequestUrlInHar(IVW2_SCRIPT)) {
			PageObjectLogging.log("RequestToIVW2Issued", "Request to IVW2 issued", true);
		} else {
			throw new NoSuchElementException("Request to IVW2 not issued");
		}
	}

	public void verifyParamFromIVW2Present(String ivw2Param) {
		if (driver.getPageSource().indexOf(ivw2Param) >= 0) {
			PageObjectLogging.log("ParameterFromIVW2IsPresent", "Parameter " + ivw2Param + " from IVW2 is present", true);
		} else {
			throw new NoSuchElementException("Parameter from IVW2 is not present");
		}
	}

	private boolean hasSkin(WebElement element, String elementSelector) {
		if (isScriptPresentInElement(element, JS_SKIN_CALL)) {
			PageObjectLogging.log("Found skin call", "skin call found in " + elementSelector, true);
			return true;
		}
		return false;
	}
}
