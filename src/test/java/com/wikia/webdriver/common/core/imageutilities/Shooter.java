package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class responsible for taking and saving screenshots
 */
public class Shooter {

  private ImageEditor imageEditor;
  private int dpr;

  public Shooter() {
    imageEditor = new ImageEditor();
    this.dpr = imageEditor.getDevicePixelRatio();
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

  public BufferedImage takeScreenshot(WebElement element, WebDriver driver) {

    JavascriptActions jsActions = new JavascriptActions();
    CoordsProvider coordsProvider;
    if (!"FF".equals(Configuration.getBrowser())) {
      jsActions.scrollElementIntoViewPort(element);
      coordsProvider = new SimpleViewportRelativeCoordsProvider();
    } else {
      coordsProvider = new WebDriverCoordsProvider();
      dpr = 1;
    }

    BufferedImage image = new AShot().coordsProvider(coordsProvider)
        .shootingStrategy(ShootingStrategies.scaling(dpr))
        .takeScreenshot(driver, element)
        .getImage();

    Log.log("Image size", image.getWidth() + "x" + image.getHeight() + "with dpr: " + dpr, true);
    Log.image("Shooter", createTempFileFromImage(image), true);
    return image;
  }

  private File createTempFileFromImage(BufferedImage image) {
    File file;
    try {
      file = File.createTempFile("screenshot", ".png");
    } catch (IOException e) {
      throw new WebDriverException(e);
    }

    try {
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
    return file;
  }

  public File capturePageAndCrop(Point start, Dimension size, WebDriver driver) {

    WebElement dummyElement = driver.findElement(By.tagName("body"));

    BufferedImage image = new AShot().imageCropper(new CustomImageCropper().setCoordinates(start,
                                                                                           size
    ))
        .shootingStrategy(ShootingStrategies.scaling(dpr))
        .takeScreenshot(driver, dummyElement)
        .getImage();

    return createTempFileFromImage(image);
  }
}