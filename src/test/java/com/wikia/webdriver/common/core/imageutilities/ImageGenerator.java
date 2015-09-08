package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriverException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

/**
 * @ownership: Quality Team
 */
public class ImageGenerator {

  private String imagePath;
  private String imageFolder = PageContent.IMAGE_UPLOAD_RESOURCES_PATH;
  BufferedImage imageBuffer;
  private int imageWidth;
  private int imageHeight;

  public ImageGenerator() {
    imageWidth = 200;
    imageHeight = 200;
    imageBuffer =
        new BufferedImage(imageWidth, imageHeight,
                          BufferedImage.TYPE_INT_ARGB);
  }

  /**
   * draw image in the middle
   */
  public void generateImageWithRandomText() {

    String imageExtension = "png";
    imagePath = imageFolder + "random_image." + imageExtension;
    String fontName = "TimesRoman";
    int fontStyle = Font.BOLD;
    int fontSize = 20;
    Color fontColor = Color.BLUE;
    int textLength = 16;
    String imageText = getRandomText(textLength);

    String actionName = "generate random image";
    String actionDescription = "generated image with random text: " + imageText;

    Graphics2D g2 = imageBuffer.createGraphics();
    g2.setPaint(fontColor);
    Font font = new Font(fontName, fontStyle, fontSize);
    g2.setFont(font);
    FontMetrics fontMetrics = g2.getFontMetrics();
    int stringWidth = fontMetrics.stringWidth(imageText);
    int stringHeight = fontMetrics.getAscent();

    // Draw the text in the middle of the image
    g2.drawString(imageText, (imageWidth - stringWidth) / 2, imageHeight / 2);

    try {
      if (ImageIO.write(imageBuffer, imageExtension, new File(imagePath))) {
        PageObjectLogging.logOnLowLevel(actionName, actionDescription, true);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getImageAbsolutePath() {
    return CommonUtils.getAbsolutePathForFile(imagePath);
  }

  public String getImageAbsolutePathForMobile() {
    String pathOnLocalMachine = this.getImageAbsolutePath();
    String pathOnMobile = "/data/local/";
    Runtime rt = Runtime.getRuntime();
    try {
      Process pr = rt.exec("adb push" + pathOnLocalMachine + " " + pathOnMobile);
    } catch (IOException e) {
      throw  new WebDriverException(ExceptionUtils.getStackTrace(e));
    }
    return pathOnMobile + "random_image.png";
  }

  private String getRandomText(int textLength) {
    SecureRandom random = new SecureRandom();
    return new BigInteger(5 * textLength, random).toString(32);
  }
}
