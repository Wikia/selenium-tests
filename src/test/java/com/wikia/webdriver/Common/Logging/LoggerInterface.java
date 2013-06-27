package com.wikia.webdriver.Common.Logging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public interface LoggerInterface extends WebDriverEventListener {

	public void log(String command, WebElement element, WebDriver driver);
	public void saveLogs();
}
