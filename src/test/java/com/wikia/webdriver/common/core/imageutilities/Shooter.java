package com.wikia.webdriver.common.core.imageutilities;

import java.awt.image.BufferedImage;
import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class responsible for taking and saving screenshots
 *
 * @author Bogna 'bognix' Knychala
 */
public class Shooter {

	private ImageEditor imageEditor;

	public Shooter() {
		imageEditor = new ImageEditor();
	}

	public void savePageScreenshot(String path, WebDriver driver) {
		imageEditor.saveImageFile(capturePage(driver), path);
	}

	public File capturePage(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	/**
	 * Create a screenshot of passed element
	 * and save screenshot as image file in temp dir
	 *
	 * @param element - WebElement you want to capture
	 * @param driver  - instace of WebDriver
	 * @return File path  - file's handler which was saved in given path
	 */
	public File captureWebElement(WebElement element, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		return imageEditor.cropImage(start, element.getSize(), screen);
	}

	public BufferedImage takeScreenshot(WebElement element, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		Dimension size = element.getSize();
		BufferedImage image = imageEditor.fileToImage(screen);
		return image.getSubimage(start.getX(), start.getY(), size.width, size.height);
	}

	public File captureWebElementAndCrop(WebElement element, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		return imageEditor.cropImage(start, size, screen);
	}

	public File capturePageAndCrop(Point start, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		return imageEditor.cropImage(start, size, screen);
	}
}
