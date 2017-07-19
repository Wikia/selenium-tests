package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriverException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageGenerator {

  private String imagePath;
  private String imageFolder = PageContent.IMAGE_UPLOAD_RESOURCES_PATH;
  private BufferedImage imageBuffer;
  private int defaultWidth = 200;
  private int defaultHeight = 200;

  public ImageGenerator() {
    imageBuffer =
        new BufferedImage(defaultWidth, defaultHeight,
                          BufferedImage.TYPE_INT_ARGB);
  }

  public ImageGenerator(int width, int height) {
    imageBuffer =
            new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
  }

  /**
   * Generates unique .png image, with random text in its center
   */
  public void generateImageWithRandomText() {

    int fontStyle = Font.BOLD;
    int fontSize = 20;
    int textLength = 16;
    String fontName = "TimesRoman";
    String imageExtension = "png";
    String imageText = getRandomText(textLength);
    Color fontColor = Color.BLUE;
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    this.imagePath = imageFolder + "random_image_" + timestamp + "." + imageExtension;

    String actionName = "generate random image";
    String actionDescription = "generated image with random text: " + imageText;

    Graphics2D g2 = imageBuffer.createGraphics();
    g2.setPaint(fontColor);
    Font font = new Font(fontName, fontStyle, fontSize);
    g2.setFont(font);
    FontMetrics fontMetrics = g2.getFontMetrics();
    int stringWidth = fontMetrics.stringWidth(imageText);

    // Draw the text in the middle of the image
    g2.drawString(imageText, (imageBuffer.getWidth() - stringWidth) / 2, imageBuffer.getHeight() / 2);

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
