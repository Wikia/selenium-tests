package com.wikia.webdriver.common.core.imageutilities;

import org.openqa.selenium.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
		JavascriptExecutor js = (JavascriptExecutor) driver;

		ArrayList<String> list = (ArrayList<String>) js.executeScript(
			"var rect =  arguments[0].getBoundingClientRect();" +
				"return [ '' + parseInt(rect.left), '' + parseInt(rect.top), '' + parseInt(rect.width), '' + parseInt(rect.height) ]",
			element
		);

		Point start = new Point(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));
		Dimension size = new Dimension(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));

		return imageEditor.cropImage(start, size, screen);
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
