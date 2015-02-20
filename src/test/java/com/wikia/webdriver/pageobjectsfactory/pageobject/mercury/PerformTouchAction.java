package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @authors: Tomasz Napieralski
 * @created: 9 Jan 2015
 * @updated: 9 Feb 2015
 */

public class PerformTouchAction {

  private WebDriver driver;
  private AndroidDriver mobileDriver;

  private int nativeHeight = 0;
  private int nativeWidth = 0;
  private int webviewHeight = 0;
  private int webviewWidth = 0;
  private int ratio = 0;

  private int taskbarNativeHeight = 0;
  private int taskbarNativeWidth = 0;
  private int taskbarWebviewHeight = 0;
  private int taskbarWebviewWidth = 0;

  private int addressbarNativeHeight = 0;
  private int addressbarNativeWidth = 0;
  private int addressbarWebviewHeight = 0;
  private int addressbarWebviewWidth = 0;

  private int appNativeHeight = 0;
  private int appNativeWidth = 0;
  private int appWebviewHeight = 0;
  private int appWebviewWidth = 0;

  private int loadedPageHeight = 0;
  private int loadedPageWidth = 0;

  public static final String DIRECTION_LEFT = "left";
  public static final String DIRECTION_RIGHT = "right";
  public static final String DIRECTION_UP = "up";
  public static final String DIRECTION_DOWN = "down";

  public static final String ZOOM_WAY_IN = "in";
  public static final String ZOOM_WAY_OUT = "out";

  private static final String CONTEXT_NATIVE_APP = "NATIVE_APP";
  private static final String CONTEXT_WEBVIEW_1 = "WEBVIEW_1";

  public PerformTouchAction(WebDriver webDriver) {
    String methodName = "PerformTouchAction";
    mobileDriver = NewDriverProvider.getMobileDriver();
    driver = webDriver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    addressbarWebviewHeight =
        Integer.parseInt(js.executeScript("return $(window).height()").toString());
    addressbarWebviewWidth =
        Integer.parseInt(js.executeScript("return $(window).width()").toString());
    waitForFinish(5000, methodName);
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    nativeHeight = mobileDriver.manage().window().getSize().height;
    nativeWidth = mobileDriver.manage().window().getSize().width;
    int startX = nativeWidth / 2;
    int startY = nativeHeight - 1;
    int endX = startX;
    int endY = nativeHeight / 3;
    int duration = startY - endY;
    mobileDriver.swipe(startX, startY, endX, endY, duration);
    waitForFinish(duration * 3, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
    js.executeScript("window.scrollTo(0, 0)");
    appWebviewHeight = Integer.parseInt(js.executeScript("return $(window).height()").toString());
    appWebviewWidth = Integer.parseInt(js.executeScript("return $(window).width()").toString());
    loadedPageHeight = Integer.parseInt(js.executeScript("return $(document).height()").toString());
    loadedPageWidth = Integer.parseInt(js.executeScript("return $(document).width()").toString());
    addressbarWebviewHeight = appWebviewHeight - addressbarWebviewHeight;
    ratio = nativeWidth / appWebviewWidth;
    webviewHeight = nativeHeight / ratio;
    webviewWidth = appWebviewWidth;
    taskbarWebviewHeight = webviewHeight - appWebviewHeight;
    taskbarWebviewWidth = webviewWidth;
    taskbarNativeHeight = taskbarWebviewHeight * ratio;
    taskbarNativeWidth = nativeWidth;
    appNativeWidth = nativeWidth;
    appNativeHeight = nativeHeight - taskbarNativeHeight;
    addressbarNativeHeight = addressbarWebviewHeight * ratio;
    addressbarNativeWidth = nativeWidth;
  }

  /**
   * That method return value of all resolution variables.
   */
  public void varStatus() {
    System.out.println("=======================================");
    System.out.println("nativeHeight: " + nativeHeight);
    System.out.println("nativeWidth: " + nativeWidth);
    System.out.println("webviewHeight: " + webviewHeight);
    System.out.println("webviewWidth: " + webviewWidth);
    System.out.println("ratio (nativeWidth / appWebviewWidth): " + ratio);
    System.out.println("=======================================");
    System.out.println("taskbarNativeHeight: " + taskbarNativeHeight);
    System.out.println("taskbarNativeWidth: " + taskbarNativeWidth);
    System.out.println("taskbarWebviewHeight: " + taskbarWebviewHeight);
    System.out.println("taskbarWebviewWidth: " + taskbarWebviewWidth);
    System.out.println("=======================================");
    System.out.println("appNativeWidth: " + appNativeWidth);
    System.out.println("appNativeHeight: " + appNativeHeight);
    System.out.println("appWebviewHeight: " + appWebviewHeight);
    System.out.println("appWebviewWidth: " + appWebviewWidth);
    System.out.println("=======================================");
    System.out.println("addressbarNativeHeight: " + addressbarNativeHeight);
    System.out.println("addressbarNativeWidth: " + addressbarNativeWidth);
    System.out.println("addressbarWebviewHeight: " + addressbarWebviewHeight);
    System.out.println("addressbarWebviewWidth: " + addressbarWebviewWidth);
    System.out.println("=======================================");
    System.out.println("loadedPageHeight: " + loadedPageHeight);
    System.out.println("loadedPageWidth: " + loadedPageWidth);
    System.out.println("=======================================");
  }

  /**
   * @param time       In milliseconds
   * @param methodName Used in log if fail
   */
  private void waitForFinish(int time, String methodName) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  /**
   * Use that method if you want to switch between using app and using touch
   *
   * @param contextName Use NATIVE_APP for touch or WEBVIEW_1 for app
   * @param methodName  Used in log if fail
   */
  private void switchToContext(String contextName, String methodName) {
    try {
      mobileDriver.context(contextName);
    } catch (NoSuchContextException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  /**
   * @param direction Use public const DIRECTION from that class
   * @param pixelPath From 0 to half of screen X or Y, if path will be too high it will be set to
   *                  edge of app
   * @param duration  In milliseconds
   * @param waitAfter In milliseconds, Recommend 2*duration
   */
  public void swipeFromCenterToDirection(String direction, int pixelPath, int duration,
                                         int waitAfter) {
    String methodName = "swipeFromCenterToDirection";
    int centerX = appNativeWidth / 2;
    int centerY = (appNativeHeight / 2) + taskbarNativeHeight;
    int path = 0;
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    switch (direction) {
      case "left":
        if (pixelPath < centerX) {
          path = pixelPath;
        } else {
          path = centerX - 2;
        }
        mobileDriver.swipe(centerX, centerY, centerX - path, centerY, duration);
        break;
      case "right":
        if (pixelPath < centerX) {
          path = pixelPath;
        } else {
          path = centerX - 2;
        }
        mobileDriver.swipe(centerX, centerY, centerX + path, centerY, duration);
        break;
      case "up":
        if (pixelPath < centerY) {
          path = pixelPath;
        } else {
          path = centerY - 2;
        }
        mobileDriver.swipe(centerX, centerY, centerX, centerY - path, duration);
        break;
      case "down":
        if (pixelPath < centerY) {
          path = pixelPath;
        } else {
          path = centerY - 2;
        }
        mobileDriver.swipe(centerX, centerY, centerX, centerY + path, duration);
        break;
      default:
        break;
    }
    waitForFinish(waitAfter, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
  }

  /**
   * @param startX    Use value 0-100, it is percent of app width, Recommend 10-90
   * @param startY    Use value 0-100, it is percent of app height, Recommend 10-90
   * @param endX      Use value 0-100, it is percent of app width, Recommend 10-90
   * @param endY      Use value 0-100, it is percent of app height, Recommend 10-90
   * @param duration  In milliseconds
   * @param waitAfter In milliseconds, Recommend 2*duration
   */
  public void swipeFromPointToPoint(int startX, int startY, int endX, int endY, int duration,
                                    int waitAfter) {
    String methodName = "swipeFromPointToPoint";
    int appStartX = (int) ((startX / 100f) * appNativeWidth);
    int appStartY = (int) (((startY / 100f) * appNativeHeight) + taskbarNativeHeight);
    int appEndX = (int) ((endX / 100f) * appNativeWidth);
    int appEndY = (int) (((endY / 100f) * appNativeHeight) + taskbarNativeHeight);
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    mobileDriver.swipe(appStartX, appStartY, appEndX, appEndY, duration);
    waitForFinish(waitAfter, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
  }

  /**
   * It uses two fingers to zoom in or out.
   *
   * @param pointX       Use value 0-100, it is percent of app width, Recommend 50
   * @param pointY       Use value 0-100, it is percent of app height, Recommend 10-90
   * @param fingersSpace Space between two fingers, In pixel, It must be less than pixelPath,
   *                     Recommend 20-100
   * @param pixelPath    From 0 to half of screen X, if path will be too high it will be set to edge
   *                     of app
   * @param zoomWay      Use public const ZOOM_WAY from that class
   * @param waitAfter    In milliseconds
   */
  public void zoomInOutPointXY(int pointX, int pointY, int fingersSpace, int pixelPath,
                               String zoomWay, int waitAfter) {
    String methodName = "zoomInOutPointXY";
    int appPointX = (int) ((pointX / 100f) * appNativeWidth);
    int appPointY = (int) (((pointY / 100f) * appNativeHeight) + taskbarNativeHeight);
    TouchAction touchOne = new TouchAction(mobileDriver);
    TouchAction touchTwo = new TouchAction(mobileDriver);
    MultiTouchAction multiTouch = new MultiTouchAction(mobileDriver);
    int halfPath = 0;
    int endXOne = 0;
    int endXTwo = 0;
    int offSet = 0;
    int startXOne = 0;
    int startXTwo = 0;
    int appFingersSpace = 0;
    if (fingersSpace >= pixelPath) {
      appFingersSpace = pixelPath - 2;
    }
    appFingersSpace = fingersSpace / 2;
    if (pixelPath < appNativeWidth) {
      halfPath = pixelPath / 2;
    } else {
      halfPath = (appNativeWidth - 2) / 2;
    }
    endXOne = appPointX - halfPath;
    if (endXOne < 0) {
      offSet = -endXOne;
      endXOne = 0;
    }
    endXTwo = appPointX + halfPath;
    if (endXTwo > (appNativeWidth - 2)) {
      offSet = (appNativeWidth - 2) - endXTwo;
      endXTwo = (appNativeWidth - 2);
    }
    if (offSet > 0) {
      endXTwo += offSet;
    }
    if (offSet < 0) {
      endXOne += offSet;
    }
    startXOne = appPointX - appFingersSpace;
    if (startXOne <= 0) {
      startXOne = 1;
    }
    startXTwo = appPointX + appFingersSpace;
    if (startXTwo >= (appNativeWidth - 2)) {
      startXTwo = appNativeWidth - 3;
    }
    if ("out".equals(zoomWay)) {
      int temp;
      temp = startXOne;
      startXOne = endXOne;
      endXOne = temp;
      temp = startXTwo;
      startXTwo = endXTwo;
      endXTwo = temp;
    }
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    touchOne.press(startXOne, appPointY).moveTo(endXOne - startXOne, 0).release();
    touchTwo.press(startXTwo, appPointY).moveTo(endXTwo - startXTwo, 0).release();
    multiTouch.add(touchOne);
    multiTouch.add(touchTwo);
    multiTouch.perform();
    waitForFinish(waitAfter, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
  }

  /**
   * @param pointX    Use value 0-100, it is percent of app width, Recommend 10-90
   * @param pointY    Use value 0-100, it is percent of app height, Recommend 10-90
   * @param duration  In milliseconds, Recommend 500
   * @param waitAfter In milliseconds
   */
  public void tapOnPointXY(int pointX, int pointY, int duration, int waitAfter) {
    String methodName = "tapOnPointXY";
    int appPointX = (int) ((pointX / 100f) * appNativeWidth);
    int appPointY = (int) (((pointY / 100f) * appNativeHeight) + taskbarNativeHeight);
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    mobileDriver.tap(1, appPointX, appPointY, duration);
    waitForFinish(waitAfter, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
  }

  /**
   * It scroll to element then tap on his center. It scroll only vertically so if element need to be
   * scrolled horizontally it won't work
   *
   * @param locator   Use By selector, Example By.cssSelector('img.loaded')
   * @param index     If locator find more than one element use index to access element you want
   * @param duration  In milliseconds, Recommend 500
   * @param waitAfter In milliseconds
   */
  public void tapOnWebElement(By locator, int index, int duration, int waitAfter) {
    String methodName = "tapOnWebElement";
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement element;
    if (index > 0) {
      List<WebElement> elements = driver.findElements(locator);
      element = elements.get(index);
    } else {
      element = driver.findElement(locator);
    }
    int elementStartPointX = element.getLocation().getX();
    int elementHeight = element.getSize().getHeight();
    int elementWidth = element.getSize().getHeight();
    int offSetY = loadedPageHeight - element.getLocation().getY() - appWebviewHeight;
    if (offSetY < 0) {
      offSetY = -offSetY;
    } else {
      offSetY = 0;
    }
    js.executeScript("window.scrollTo(0, " + element.getLocation().getY() + ")");
    int pointX = (elementStartPointX + (elementWidth / 2)) * ratio;
    int pointY = (taskbarWebviewHeight + (elementHeight / 2) + offSetY) * ratio;
    switchToContext(CONTEXT_NATIVE_APP, methodName);
    mobileDriver.tap(1, pointX, pointY, duration);
    waitForFinish(waitAfter, methodName);
    switchToContext(CONTEXT_WEBVIEW_1, methodName);
  }

  /**
   * Use that method to verify presence of addressbar
   *
   * @return Boolean
   */
  public boolean isAddressbarPresent() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    if ((appWebviewHeight - Integer
        .parseInt(js.executeScript("return $(window).height()").toString()))
        == addressbarWebviewHeight) {
      return true;
    }
    return false;
  }
}
