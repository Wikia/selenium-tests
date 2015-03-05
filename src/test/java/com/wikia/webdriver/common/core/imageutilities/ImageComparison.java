package com.wikia.webdriver.common.core.imageutilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
   * @param File file1 - file containing first image
   * @param File file2 - file containing second image
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
    if (Arrays.equals(fileInBytes1, fileInBytes2)) {
      return true;
    }
    return false;
  }

  public boolean areBase64StringsTheSame(String base1, String base2) {
    Base64 coder = new Base64();
    byte[] baseInBytes1 = coder.decode(base1);
    byte[] baseInBytes2 = coder.decode(base2);
    if (Arrays.equals(baseInBytes1, baseInBytes2)) {
      return true;
    }
    return false;
  }

  /**
   * @param accuracy in percentage between 0 and 100.
   */
  public boolean isColorImage(BufferedImage image, Color color, int accuracy) {
    int count = image.getHeight() * image.getWidth();
    ;
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

  /**
   * @param threshold in percentage between 0 and 100.
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

  public Triple getRgbVariance(BufferedImage image) {
    ArrayList<Color> pixels = new ArrayList<>();
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        pixels.add(new Color(image.getRGB(x, y)));
      }
    }
    ArrayList<Integer> red = new ArrayList<>();
    for (Color pixel : pixels) {
      red.add(pixel.getRed());
    }
    ArrayList<Integer> green = new ArrayList<>();
    for (Color pixel : pixels) {
      green.add(pixel.getGreen());
    }
    ArrayList<Integer> blue = new ArrayList<>();
    for (Color pixel : pixels) {
      blue.add(pixel.getBlue());
    }
    return Triple.of(getVariance(red), getVariance(green), getVariance(blue));
  }

  private double getVariance(java.util.List<Integer> numbers) {
    double sum = 0;
    int size = numbers.size();
    for (Integer number : numbers) {
      sum += number;
    }
    double average = sum / size;
    sum = 0;
    for (int number : numbers) {
      sum += Math.abs(number - average);
    }
    return sum / size;
  }
}
