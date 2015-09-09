package com.wikia.webdriver.common.core.imageutilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class containing methods responsible for comparing images using different algorithms.
 *
 * @author Bogna 'bognix' Knychala
 */
public class ImageComparison {

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
      throw new RuntimeException(e);
    }
    return Arrays.equals(fileInBytes1, fileInBytes2);
  }

  /**
   * Compare two files byte length in specific accuracy
   *
   * @param accuracy use value between 0.0 and 1.0 i.e 0.98 for 98% accuracy
   * @return boolean
   */
  public boolean areFilesTheSame(File file1, File file2, double accuracy) {
    double difference = (double) file1.length() / file2.length();
    return (difference >= accuracy && difference <= 1.0) || (difference >= 1.0
                                                             && difference <= 2.0 - accuracy);
  }

  public boolean areBase64StringsTheSame(String base1, String base2) {
    Base64 coder = new Base64();
    byte[] baseInBytes1 = coder.decode(base1);
    byte[] baseInBytes2 = coder.decode(base2);
    return Arrays.equals(baseInBytes1, baseInBytes2);
  }

  /**
   * @param accuracy in percentage between 0 and 100.
   */
  public boolean isColorImage(BufferedImage image, Color color, int accuracy) {
    int count = image.getHeight() * image.getWidth();
    int diffCount = 0;
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        if (image.getRGB(x, y) != color.getRGB()) {
          diffCount += 1;
        }
      }
      if (diffCount > ((100 - accuracy) * count) / 100D) {
        return false;
      }
    }
    return true;
  }

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
      throw new RuntimeException("Images have different sizes");
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
}
