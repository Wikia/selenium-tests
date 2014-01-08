package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class Ads71MediaObject extends AdsBaseObject {

	public Ads71MediaObject(WebDriver driver) {
		super(driver);
		setSlots();
	}

	public Ads71MediaObject(WebDriver driver, String page) {
		super(driver, page);
		setSlots();
	}

	/*
	 * List of all possible combinations for 71M ads with their characteristic slots
	 */
	private List<HashMap<String,Object>> combinations = new ArrayList<HashMap<String, Object>>();

	private void setSlots() {
		HashMap<String,Object> billboardMap = new HashMap<String, Object>();
		HashMap<String,Object> fireplaceMap = new HashMap<String, Object>();
		HashMap<String,Object> flashtalkingMap = new HashMap<String, Object>();
		HashMap<String,Object> wp_internMap = new HashMap<String, Object>();
		HashMap<String,Object> leaderboardMap = new HashMap<String, Object>();

		List<String> billboard = new ArrayList<String>();
		List<String> fireplace = new ArrayList<String>();
		List<String> flashtalking = new ArrayList<String>();
		List<String> wp_intern = new ArrayList<String>();

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

		combinations.add(billboardMap);
		combinations.add(fireplaceMap);
		combinations.add(flashtalkingMap);
		combinations.add(wp_internMap);
	}

	public void veriy71MediaAdsPresent() {
		AdsComparison adsComparison = new AdsComparison();
		boolean combinationFound = false;
		for (HashMap<String,Object> combination: combinations) {
			List<String> combinationSlots = (List)combination.get("slots");
			if (checkIfCombinationOnPage(combinationSlots)) {
				PageObjectLogging.log(
					"Combination present",
					"Combination present: " + combination.get("name"),
					true,
					driver
				);
				for (String elementSelector: combinationSlots) {
					if (
						adsComparison.compareSlotOnOff(
							driver.findElement(By.cssSelector(elementSelector)),
							elementSelector,
							driver
						)
					) {
						throw new NoSuchElementException(
							"Ad in slot not found; CSS: " + elementSelector
						);
					} else {
						PageObjectLogging.log(
							"Ad in slot found",
							"Ad in slot found; CSS: " + elementSelector,
							true
						);
					}
				}
				combinationFound = true;
			}
		}
		if (!combinationFound) {
			throw new NoSuchElementException("No known combination from 71 media present");
		}
	}

	public void verifyNo71MediaAds() throws Exception {
		AdsComparison adsComparison = new AdsComparison();
		boolean combinationFound = false;
		PageObjectLogging.log("PageOpened", "Page opened", true, driver);
		for (HashMap<String,Object> combination: combinations) {
			List<String> combinationSlots = (List)combination.get("slots");
			if (!checkIfCombinationOnPage(combinationSlots)) {
				PageObjectLogging.log(
					"Combination not present",
					"Combination not present: " + combination.get("name"),
					true
				);
			} else {
				PageObjectLogging.log(
					"Combination present",
					"Combination present: " + combination.get("name"),
					false
				);
				combinationFound = true;
			}
		}
		if (combinationFound) {
			throw new Exception("Ads found on page");
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
}
