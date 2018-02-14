package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.ImageEditor;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class represents helper functionality for working with Ad Skin (such as checking whether ad skin
 * present on the page or not)
 */
public class AdsSkinHelper {

  private static final String WIKIA_MESSAGE_BUBLE = "#WikiaNotifications div[id*='msg']";
  private static final String WIKIA_MESSAGE_BUBLE_CLOSE_BUTTON = ".sprite close-notification";
  private static final int IMAGES_THRESHOLD_PERCENT = 12;
  private final WebDriver driver;
  private String pathToLeftPart;
  private String pathToRightPart;
  private AdsComparison adsComparison;
  private ImageEditor imageEditor;
  private ImageComparison imageComparison;
  private Shooter shooter;
  private int viewPortWidth;
  private int articleRightSideX;
  private int articleLeftSideX;
  private int startSkinY;
  private int articleBottomY;

  public AdsSkinHelper(String pathToLeftPart, String pathToRightPart, WebDriver driver) {
    this.pathToLeftPart = pathToLeftPart;
    this.pathToRightPart = pathToRightPart;
    this.driver = driver;
    init();
  }

  private void init() {
    this.adsComparison = new AdsComparison();
    this.imageEditor = new ImageEditor();
    this.shooter = new Shooter();
    this.imageComparison = new ImageComparison();
    WebElement wikiaArticle = driver.findElement(By.id("WikiaPageBackground"));
    WebElement globalNavigation = driver.findElement(By.id("globalNavigation"));
    this.viewPortWidth = globalNavigation.getSize().getWidth();
    this.startSkinY = globalNavigation.getSize().getHeight();
    this.articleLeftSideX = wikiaArticle.getLocation().x;
    this.articleBottomY = wikiaArticle.getLocation().y + wikiaArticle.getSize().getHeight();
    this.articleRightSideX = wikiaArticle.getLocation().x + wikiaArticle.getSize().getWidth();
  }

  public boolean skinPresent() {
    return skinPartPresent(true) & skinPartPresent(false);
  }

  private boolean skinPartPresent(boolean isLeft) {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    hideCoveredSkinElements();
    String pathToFile = isLeft ? pathToLeftPart : pathToRightPart;
    File patternFile = new File(pathToFile);
    BufferedImage patternImage = imageEditor.fileToImage(patternFile);
    if (shouldCutPattern(patternImage.getWidth(), isLeft)) {
      patternFile = getCutPattern(patternImage, isLeft);
      patternImage = imageEditor.fileToImage(patternFile);
    }
    Dimension size = new Dimension(patternImage.getWidth(), patternImage.getHeight());
    PageObjectLogging.logImage("EXPECTED", patternFile, true);
    Point startPoint = getStartPoint(patternImage, isLeft);
    File actualFile = shooter.capturePageAndCrop(startPoint, size, driver);
    PageObjectLogging.logImage("ACTUAL", actualFile, true);
    BufferedImage actualImg = imageEditor.fileToImage(actualFile);
    return !imageComparison.areImagesDifferent(patternImage, actualImg, IMAGES_THRESHOLD_PERCENT);
  }

  private Point getStartPoint(BufferedImage patternImage, boolean isLeft) {
    if (isLeft) {
      return new Point(articleLeftSideX - patternImage.getWidth(), startSkinY);
    } else {
      return new Point(articleRightSideX, startSkinY);
    }
  }

  private File getCutPattern(BufferedImage patternImage, boolean isLeft) {
    Point startPoint;
    Dimension size;
    if (isLeft) {
      size = new Dimension(articleLeftSideX, patternImage.getHeight());
      startPoint = new Point(patternImage.getWidth() - articleLeftSideX, 0);
    } else {
      size = new Dimension(viewPortWidth - articleRightSideX, patternImage.getHeight());
      startPoint = new Point(0, 0);
    }
    return imageEditor.cropImage(startPoint, size, patternImage);
  }

  private boolean shouldCutPattern(int patternWidth, boolean isLeft) {
    if (isLeft) {
      return patternWidth > articleLeftSideX;
    } else {
      return patternWidth > viewPortWidth - articleRightSideX;
    }
  }

  private void hideCoveredSkinElements() {
    closeWikiaNotifications();
    adsComparison.hideSlot(AdsContent.getSlotSelector(AdsContent.WIKIA_BAR), driver);
  }

  private void closeWikiaNotifications() {
    if (!driver.findElements(By.cssSelector(WIKIA_MESSAGE_BUBLE)).isEmpty()){
      driver.findElement(By.cssSelector(WIKIA_MESSAGE_BUBLE_CLOSE_BUTTON)).click();
    }
  }

  public String getMiddleColor() {
    Point startPoint = new Point((articleLeftSideX + articleRightSideX) / 2, startSkinY + 3);
    File pixel = shooter.capturePageAndCrop(startPoint, new Dimension(1, 1), driver);
    return toHex(new Color(imageEditor.fileToImage(pixel).getRGB(0, 0)));
  }

  public String getBackgroundColor() {
    Point startPoint = new Point(1, articleBottomY);
    File pixel = shooter.capturePageAndCrop(startPoint, new Dimension(1, 1), driver);
    return toHex(new Color(imageEditor.fileToImage(pixel).getRGB(0, 0)));
  }

  private String toHex(Color color) {
    return String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
  }
}
