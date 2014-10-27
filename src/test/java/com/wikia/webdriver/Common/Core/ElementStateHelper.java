package com.wikia.webdriver.Common.Core;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ludwik on 2014-10-27.
 */
public class ElementStateHelper {

	public static final int TIMEOUT = 30;

	public static boolean isElementVisible(WebElement element, WebDriver webDriver) {
		webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			webDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
	}
}
