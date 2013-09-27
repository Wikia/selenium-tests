package com.wikia.webdriver.Common.Core.ImageUtilities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
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
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		Rectangle rect = new Rectangle(width, height);
		BufferedImage img = null;
		File subImg = new File(path);
		try {
			img = ImageIO.read(screen);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
		try {
			ImageIO.write(dest, "png", subImg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return subImg;
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
		File screen = ((TakesScreenshot) driver) .getScreenshotAs(OutputType.FILE);
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		Rectangle rect = new Rectangle(width, height);
		BufferedImage img = null;
		File subImg = null;
		try {
			 subImg = File.createTempFile("screenshot", ".png");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			img = ImageIO.read(screen);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
		try {
			ImageIO.write(dest, "png", subImg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return subImg;
	}
}
