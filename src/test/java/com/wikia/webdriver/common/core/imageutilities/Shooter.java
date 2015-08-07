package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
   * Create a screenshot of passed element and save screenshot as image file in temp dir. <p> Notes:
   * Method works properly in Google Chrome only if devicePixelRatio equals 1. </p>
   *
   * @param element - WebElement you want to capture
   * @param driver  - instance of WebDriver
   * @return File path  - file's handler which was saved in given path
   */
  public File captureWebElement(WebElement element, WebDriver driver) {
    File image = imageEditor.cropImage(element.getLocation(), element.getSize(), capturePage(driver));
    PageObjectLogging.logImage("Shooter", image, true);
    return image;
  }

  public BufferedImage takeScreenshot(WebElement element, WebDriver driver) {
    return imageEditor.fileToImage(captureWebElement(element, driver));
  }

  public File capturePageAndCrop(Point start, Dimension size, WebDriver driver) {
    File screen = capturePage(driver);
    return imageEditor.cropImage(start, size, screen);
  }
}
