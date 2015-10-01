package com.wikia.webdriver.common.core.imageutilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;

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
   * Create a screenshot of passed element and save screenshot as image file in temp dir.
   * <p>
   * Notes: Method works properly in Google Chrome only if devicePixelRatio equals 1.
   * </p>
   *
   * @param element - WebElement you want to capture
   * @param driver - instance of WebDriver
   * @return File path - file's handler which was saved in given path
   */
  public File captureWebElement(WebElement element, WebDriver driver) {
    Point start = element.getLocation();
    Dimension size = element.getSize();
    if (!"FF".equals(Configuration.getBrowser())) {
      Object[] rect = getBoundingClientRect(element, driver);
      start = (Point) rect[0];
      size = (Dimension) rect[1];
    }

    File image = imageEditor.cropImage(start, size, capturePage(driver));
    LOG.result("Shooter", image, true);
    return image;
  }

  private Object[] getBoundingClientRect(WebElement element, WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ArrayList<String> list =
        (ArrayList<String>) js
            .executeScript(
                "var rect =  arguments[0].getBoundingClientRect();"
                + "return [ '' + parseInt(rect.left), '' + parseInt(rect.top), '' + parseInt(rect.width), '' + parseInt(rect.height) ]",
                element);
    Point start = new Point(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));
    Dimension size = new Dimension(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
    return new Object[] {start, size};
  }

  public BufferedImage takeScreenshot(WebElement element, WebDriver driver) {
    return imageEditor.fileToImage(captureWebElement(element, driver));
  }

  public File capturePageAndCrop(Point start, Dimension size, WebDriver driver) {
    File screen = capturePage(driver);
    return imageEditor.cropImage(start, size, screen);
  }
}
