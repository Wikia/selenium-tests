package com.wikia.webdriver.common.core.imageutilities;

import org.apache.commons.lang3.tuple.Triple;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Ludwik Kazmierczak
 */
public class ImageHelper {

  private static final String START_TOKEN = "Wikia-Visualization-Main%2C";
  private static final String STOP_TOKEN = ".";

  private ImageHelper() {

  }

  /**
   * Method fetches specific string related to an image by storing index start position and finish
   * position, and then selects characters in between those indexes by using substring method.
   */
  public static String getImageId(WebElement image) {
    String imageUrl = image.getAttribute("src");

    int indexComparisonStart = imageUrl.indexOf(START_TOKEN) + START_TOKEN.length();
    int indexComparisonFinish = imageUrl.substring(indexComparisonStart).indexOf(STOP_TOKEN);

    return imageUrl.substring(indexComparisonStart, indexComparisonStart + indexComparisonFinish);
  }

  /**
   * Method calculates RGB variance of an image
   * @return R variance, G variance, B variance
   */
  public static Triple getRgbVariance(BufferedImage image) {
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

  private static double getVariance(java.util.List<Integer> numbers) {
    double sum = 0;
    int size = numbers.size();
    for (Integer number : numbers) {
      sum += number;
    }
    double average = sum / size;
    sum = 0;
    for (int number : numbers) {
      sum += Math.pow(number - average, 2);
    }
    return sum / size;
  }
}
