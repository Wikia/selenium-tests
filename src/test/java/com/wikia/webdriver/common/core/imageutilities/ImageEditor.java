package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Emulator;

import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class ImageEditor {

  private int dpr;

  public ImageEditor() {
    this.dpr = getDevicePixelRatio();
  }

  /**
   * Gets device pixel ratio from emulator, 1 if not present. If no emulator is used dpr is taken
   * from config.yaml and should reflect your monitor dpr e.g. 2 for Macbook Pro
   *
   * @return dpr
   */
  public static int getDevicePixelRatio() {
    if (Configuration.getEmulator() == Emulator.DEFAULT) {
      if (Configuration.getDpr() == null) {
        throw new ConfigurationRuntimeException("No dpr variable found in config.yaml");
      }
      return Integer.parseInt(Configuration.getDpr());
    } else {
      Map<String, Object> metrics = Configuration.getEmulator().getDeviceMetrics();
      return (metrics != null && metrics.containsKey("pixelRatio")) ? ((Double) metrics.get(
          "pixelRatio")).intValue() : 1;
    }
  }

  public void saveImageFile(File imageFile, String path) {
    Pattern pattern = Pattern.compile("/*.jpg|/*.png|/*.jpeg");
    Matcher matcher = pattern.matcher(path);
    String newPath = null;
    if (!matcher.matches()) {
      newPath = path + ".png";
    }
    try {
      FileUtils.copyFile(imageFile, new File(newPath));
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
  }

  public File cropImage(
      org.openqa.selenium.Point start, org.openqa.selenium.Dimension size, BufferedImage image
  ) {
    CustomImageCropper cropper = new CustomImageCropper();
    BufferedImage croppedImage = cropper.customCropScreenshot(image, start, size).getImage();
    File subImg;
    try {
      subImg = File.createTempFile("screenshot", ".png");
    } catch (IOException e) {
      throw new WebDriverException(e);
    }

    try {
      ImageIO.write(croppedImage, "png", subImg);
    } catch (IOException e) {
      throw new WebDriverException(e);
    }

    return subImg;
  }

  public BufferedImage fileToImage(File file) {
    BufferedImage img;
    try {
      img = ImageIO.read(file);
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
    return img;
  }
}
