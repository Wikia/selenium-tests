package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.google.common.base.Joiner;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsKruxObject extends AdsBaseObject {
	private static final String KRUX_CONTROL_TAG_URL_PREFIX = "http://cdn.krxd.net/controltag?confid=";
	@FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
	private WebElement kruxControlTag;

	public AdsKruxObject(WebDriver driver, String page) {
		super(driver, page);
	}

	/**
	 * Test whether the Krux control tag is called with the proper site ID
	 *
	 * @param kruxSiteId the expected Krux site ID
	 */
	public void verifyKruxControlTag(String kruxSiteId) {
		String expectedUrl = KRUX_CONTROL_TAG_URL_PREFIX + kruxSiteId;
		Assertion.assertEquals(expectedUrl, kruxControlTag.getAttribute("src"));
	}

	/**
	 * Test whether the Krux user id is not empty and added to GPT calls
	 */
	public void verifyKruxUserParam() {
		waitForKrux();
		String kruxUser = (String) ((JavascriptExecutor) driver).executeScript("return Krux.user;");
		Assertion.assertStringNotEmpty(kruxUser);
		Assertion.assertTrue(isGptParamPresent("u", kruxUser));
	}

	public void verifyKruxSegment(String seg_id) {
		waitForKrux();
		List segments = (ArrayList) ((JavascriptExecutor) driver).executeScript("return Krux.segments;");
		String current = Joiner.on("\t").join(segments);
		Assertion.assertStringContains(seg_id, current);
	}

	private void waitForKrux() {
		wait.until(CommonExpectedConditions.scriptReturnsTrue("return !!window.Krux"));
	}
}
