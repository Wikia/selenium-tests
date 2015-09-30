package com.wikia.webdriver.common.core.imageutilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;

/**
 * Bogna 'bognix' Knychala
 */
public class ImageEditor {

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

  public File cropImage(org.openqa.selenium.Point start, org.openqa.selenium.Dimension size,
      BufferedImage image) {
    int width = size.width;
    int height = size.height;
    File subImg;
    try {
      subImg = File.createTempFile("screenshot", ".png");
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
    if (width < 1) {
      width = 1;
    }
    if (height < 1) {
      height = 1;
    }
    BufferedImage dest = image.getSubimage(start.getX(), start.getY(), width, height);
    try {
      ImageIO.write(dest, "png", subImg);
    } catch (IOException e) {
      throw new WebDriverException(e);
    }
    return subImg;
  }

  public File cropImage(Point start, org.openqa.selenium.Dimension size, File image) {
    BufferedImage img = fileToImage(image);
    return cropImage(start, size, img);
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
