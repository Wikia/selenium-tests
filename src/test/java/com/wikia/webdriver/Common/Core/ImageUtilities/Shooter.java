package com.wikia.webdriver.Common.Core.ImageUtilities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
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

	public void savePageScreenshot(String path, WebDriver driver) {
		saveImageFile(capturePage(driver), path);
	}

	public File capturePage(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
		return cropImage(start, element.getSize(), screen);
	}

	public File captureWebElementAndCrop(WebElement element, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		Point start = element.getLocation();
		return cropImage(start, size, screen);
	}

	public File capturePageAndCrop(Point start, Dimension size, WebDriver driver) {
		File screen = capturePage(driver);
		return cropImage(start, size, screen);
	}

	public BufferedImage scaleImage(
		File inputFile, double scaleX, double scaleY
	) {
		BufferedImage inputImage = null;
		try {
			inputImage = ImageIO.read(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage outputImage = new BufferedImage(
			inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB
		);
		Graphics2D graphics = outputImage.createGraphics();
		AffineTransform affineTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		graphics.drawRenderedImage(inputImage, affineTransform);
		return outputImage;
	}

	public File cropImage(Point start, Dimension size, BufferedImage image) {
		int width = size.width;
		int height = size.height;
		Rectangle rect = new Rectangle(width, height);
		File subImg;
		try {
			subImg = File.createTempFile("screenshot", ".png");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		BufferedImage dest = image.getSubimage(
				start.getX(), start.getY(), rect.width, rect.height
		);
		try {
			ImageIO.write(dest, "png", subImg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return subImg;
	}

	private BufferedImage fileToImage(File file) {
		BufferedImage img;
		try {
			img = ImageIO.read(file);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		return img;
	}

	private File cropImage(Point start, Dimension size, File image) {
		BufferedImage img = fileToImage(image);
		return cropImage(start, size, img);
	}

	private void saveImageFile(File imageFile, String path) {
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


}
