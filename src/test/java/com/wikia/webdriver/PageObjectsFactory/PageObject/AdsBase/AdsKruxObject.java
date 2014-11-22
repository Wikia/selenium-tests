package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.google.common.base.Joiner;
import com.wikia.webdriver.Common.Core.Assertion;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsKruxObject extends AdsBaseObject {
	private static final String KRUX_CONTROL_TAG_URL_PREFIX = "http://cdn.krxd.net/controltag?confid=";
	private static final String KRUX_SET_SEGMEN_URL_PREFIX = "http://apiservices.krxd.net/audience_segments/" +
		"add_user?pubid=44c1a380-770f-11df-93f2-0800200c9a66&seg_id=";
	private static final String KRUX_USER_DATA_URL = "http://apiservices.krxd.net/user_data/" +
		"segments?pubid=44c1a380-770f-11df-93f2-0800200c9a66";

	@FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
	private WebElement kruxControlTag;
	@FindBy(css = "pre")
	private WebElement kruxJSON;


	public AdsKruxObject(WebDriver driver, String page) {
		super(driver, page);
	}

	public AdsKruxObject(WebDriver driver) {
		super(driver);
	}

	public void setKruxSegment(String seg_id) {
		getUrl(KRUX_SET_SEGMEN_URL_PREFIX + seg_id, true);
		waitForElementVisibleByElement(kruxJSON);
	}

	private ArrayList getKruxSegmentsByKrux() {
		getUrl(KRUX_USER_DATA_URL, true);
		waitForElementVisibleByElement(kruxJSON);
		return (ArrayList) ((JavascriptExecutor) driver).executeScript("return JSON.parse(arguments[0]).body.segments",
			kruxJSON.getText());
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
		String kruxUser = (String) ((JavascriptExecutor) driver).executeScript("return Krux.user;");
		Assertion.assertStringNotEmpty(kruxUser);
		Assertion.assertTrue(isGptParamPresent("u", kruxUser));
	}

	public void verifyKruxSegmentByKrux(String seg_id) {
		String current = Joiner.on("\t").join(getKruxSegmentsByKrux());
		Assertion.assertStringContains(seg_id, current);
	}

	public void verifyKruxSegment(String seg_id) {
		ArrayList segments = (ArrayList) ((JavascriptExecutor) driver).executeScript("return Krux.segments;");
		String current = Joiner.on("\t").join(segments);
		Assertion.assertStringContains(seg_id, current);
	}
}
