package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.*;

import java.util.ArrayList;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsEvolveObject extends AdsBaseObject {
	private static final String EVOLVE_ERROR_MESSAGE = "Verify Evolve call in ";
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

	public void verifyEvolveCall() {
		Assertion.assertTrue(evolveCalledInSlot(AdsContent.TOP_LB),
			EVOLVE_ERROR_MESSAGE + AdsContent.TOP_LB);
		Assertion.assertTrue(evolveCalledInSlot(AdsContent.MEDREC),
			EVOLVE_ERROR_MESSAGE + AdsContent.MEDREC);
		Assertion.assertTrue(evolveCalledInSlot(AdsContent.LEFT_SKYSCRAPPER_2),
			EVOLVE_ERROR_MESSAGE + AdsContent.LEFT_SKYSCRAPPER_2);
		Assertion.assertFalse(evolveCalledInSlot(AdsContent.FLOATING_MEDREC),
			EVOLVE_ERROR_MESSAGE + AdsContent.FLOATING_MEDREC);
		Assertion.assertFalse(evolveCalledInSlot(AdsContent.PREFOOTER_LEFT),
			EVOLVE_ERROR_MESSAGE + AdsContent.PREFOOTER_LEFT);
		Assertion.assertFalse(evolveCalledInSlot(AdsContent.PREFOOTER_RIGHT),
			EVOLVE_ERROR_MESSAGE + AdsContent.PREFOOTER_RIGHT);
	}
}
