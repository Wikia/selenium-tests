package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import org.openqa.selenium.*;

import java.util.ArrayList;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsEvolveObject extends AdsBaseObject {
	private static final String EVOLVE_SELECTOR = " script[src*=\"4403ad\"]";

	public AdsEvolveObject(WebDriver driver, String page) {
		super(driver, page);
	}

	public boolean evolveCalledInSlot(String slotName) {
		String slotSelector = AdsContent.getSlotSelector(slotName);
		ArrayList evolveScripts = (ArrayList) ((JavascriptExecutor) driver).executeScript(
			"return $(arguments[0] + arguments[1])",
			slotSelector, EVOLVE_SELECTOR
		);
		return !evolveScripts.isEmpty();
	}
}
