package com.wikia.webdriver.Common.Core.ImageUtilities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
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

	public Shooter Shooter() {
		return this;
	}

	public void saveImageFile(File imageFile, String path) {
		Pattern pattern = Pattern.compile("/*.jpg|/*.png|/*.jpeg");
		Matcher matcher = pattern.matcher(path);
		if (!matcher.matches()) {
			path += ".png";
		}
		try {
			FileUtils.copyFile(imageFile, new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void savePageScreenshot(String path, WebDriver driver) {
		saveImageFile(capturePage(driver), path);
	}

	public File capturePage(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	/**
	 * Create a screenshot of passed element
	 * and save screenshot as image file in given path
	 *
	 * @param String path - path to save an image
	 * @param element     - WebElement you want to capture
	 * @param driver      - instace of WebDriver
	 * @return File path  - file's handler which was saved in given path
	 */
	public File captureWebElement(String path, WebElement element, WebDriver driver) {
		File screen = capturePage(driver);
		return cropImage(element.getLocation(), element.getSize(), screen);
	}

	/**
	 * Create a screenshot of passed element
	 * and save screenshot as image file in temp dir
	 *
	 * @param element     - WebElement you want to capture
	 * @param driver      - instace of WebDriver
	 * @return File path  - file's handler which was saved in given path
	 */
	public File captureWebElement(WebElement element, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		int width = element.getSize().getWidth();
		return cropImage(start, element.getSize(), screen);
	}

	public File captureWebElementWithSize(WebElement element, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		return cropImage(start, size, screen);
	}

	public File capturePartOfPage(Point start, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		return cropImage(start, size, screen);
	}

	private File cropImage(Point start, Dimension size, File image) {
		int width = size.width;
		int height = size.height;
		Rectangle rect = new Rectangle(width, height);
		BufferedImage img = null;
		File subImg = null;
		try {
			 subImg = File.createTempFile("screenshot", ".png");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			img = ImageIO.read(image);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		BufferedImage dest = img.getSubimage(
			start.getX(), start.getY(), rect.width, rect.height
		);
		try {
			ImageIO.write(dest, "png", subImg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return subImg;
	}
}
