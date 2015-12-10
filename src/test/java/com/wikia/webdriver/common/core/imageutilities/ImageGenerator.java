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

public class ImageGenerator {

  private String imagePath;
  private String imageFolder = PageContent.IMAGE_UPLOAD_RESOURCES_PATH;
  private BufferedImage imageBuffer;
  private int imageWidth = 200;
  private int imageHeight = 200;

  public ImageGenerator() {
    imageBuffer =
        new BufferedImage(imageWidth, imageHeight,
                          BufferedImage.TYPE_INT_ARGB);
  }

  /**
   * Generates unique 200x200(px) .png image, with random text in its center
   */
  public void generateImageWithRandomText() {

    int fontStyle = Font.BOLD;
    int fontSize = 20;
    int textLength = 16;
    String fontName = "TimesRoman";
    String imageExtension = "png";
    String imageText = getRandomText(textLength);
    Color fontColor = Color.BLUE;

    this.imagePath = imageFolder + "random_image." + imageExtension;

    String actionName = "generate random image";
    String actionDescription = "generated image with random text: " + imageText;

    Graphics2D g2 = imageBuffer.createGraphics();
    g2.setPaint(fontColor);
    Font font = new Font(fontName, fontStyle, fontSize);
    g2.setFont(font);
    FontMetrics fontMetrics = g2.getFontMetrics();
    int stringWidth = fontMetrics.stringWidth(imageText);

    // Draw the text in the middle of the image
    g2.drawString(imageText, (imageWidth - stringWidth) / 2, imageHeight / 2);

    try {
      if (ImageIO.write(imageBuffer, imageExtension, new File(imagePath))) {
        PageObjectLogging.logOnLowLevel(actionName, actionDescription, true);
      }
    } catch (IOException e) {
      throw new WebDriverException(ExceptionUtils.getStackTrace(e));
    }
  }

  public String getImageAbsolutePath() {
    return CommonUtils.getAbsolutePathForFile(imagePath);
  }

  private String getRandomText(int textLength) {
    return new BigInteger(5 * textLength, new SecureRandom()).toString(32);
  }
}
