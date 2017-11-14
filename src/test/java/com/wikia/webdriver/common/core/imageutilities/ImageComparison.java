package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class containing methods responsible for comparing images using different algorithms.
 */
public class ImageComparison {

  private static final int ACCEPTABLE_COLOR_DISTANCE = 10;

  /**
   * Compare two images after converting them into byte arrays
   *
   * @param file1 - file containing first image
   * @param file2 - file containing second image
   * @return boolean   - if images are the same
   */
  public boolean areFilesTheSame(File file1, File file2) {
    byte[] fileInBytes1;
    byte[] fileInBytes2;
    try {
      fileInBytes1 = FileUtils.readFileToByteArray(file1);
      fileInBytes2 = FileUtils.readFileToByteArray(file2);
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
    return Arrays.equals(fileInBytes1, fileInBytes2);
  }

  public Color getMostFrequentColor(BufferedImage image) {
    final HashMap<Integer, Integer> colorFreq = new HashMap<>();

    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        int pixelColor = image.getRGB(x,y);
        if (colorFreq.get(pixelColor) == null) {
          colorFreq.put(pixelColor, 0);
        }
        colorFreq.put(pixelColor, colorFreq.get(pixelColor) + 1);
      }
    }

    HashMap.Entry<Integer, Integer> mostFrequentEntry = null;
    for (HashMap.Entry<Integer, Integer> entry : colorFreq.entrySet()) {
      if (mostFrequentEntry == null || entry.getValue().compareTo(mostFrequentEntry.getValue()) > 0) {
        mostFrequentEntry = entry;
      }
    }

    final Color color = new Color(mostFrequentEntry.getKey());

    PageObjectLogging.log("Most frequent color", "Image most frequent color is " + color, true);

    return color;
  }

  /**
   * @param accuracy in percentage between 0 and 100.
   */
  public boolean isColorImage(BufferedImage image, Color color, int accuracy) {
    int count = image.getHeight() * image.getWidth();
    int diffCount = 0;
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        Color pixelColor = new Color(image.getRGB(x,y));
        if (!areColorsSimilar(pixelColor, color)) {
          diffCount += 1;
        }
      }
      if (diffCount > ((100 - accuracy) * count) / 100D) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return true if image has one color
   */
  public boolean isMonocolorImage(BufferedImage image) {
    for (int x = 1; x < image.getWidth(); x++) {
      for (int y = 1; y < image.getHeight(); y++) {
        if (image.getRGB(x - 1, y - 1) != image.getRGB(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @param threshold in percentage between 0 and 100. If images have less than threshold percent of
   *                  different pixels return false
   */
  public boolean areImagesDifferent(BufferedImage image1, BufferedImage image2, int threshold) {
    int sameCount = 0;
    if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
      PageObjectLogging.logWarning("areImagesDifferent", "Images have different sizes");
      return true;
    }
    int count = image1.getHeight() * image1.getWidth();
    for (int x = 0; x < image1.getWidth(); x++) {
      for (int y = 0; y < image1.getHeight(); y++) {
        if (image1.getRGB(x, y) == image2.getRGB(x, y)) {
          sameCount += 1;
        }
      }
      if (sameCount > ((100 - threshold) * count) / 100D) {
        return false;
      }
    }
    return true;
  }

  public boolean areImagesTheSame(BufferedImage image1, BufferedImage image2) {
    if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
      PageObjectLogging.logWarning("areImagesTheSame", "Images have different sizes");
      return false;
    }
    for (int x = 0; x < image1.getWidth(); x++) {
      for (int y = 0; y < image1.getHeight(); y++) {
        if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean areColorsSimilar(Color c1, Color c2) {
    return areColorsSimilar(c1, c2, ACCEPTABLE_COLOR_DISTANCE);
  }

  public boolean areColorsSimilar(Color c1, Color c2, int acceptableColorDistance) {
    double distance = Math.pow(c1.getRed() - c2.getRed(), 2) +
                      Math.pow(c1.getGreen() - c2.getGreen(), 2) +
                      Math.pow(c1.getBlue() - c2.getBlue(), 2);
    return Math.sqrt(distance) < acceptableColorDistance;
  }
}
