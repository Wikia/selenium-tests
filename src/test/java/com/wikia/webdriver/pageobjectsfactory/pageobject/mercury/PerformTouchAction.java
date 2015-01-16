package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * @authors: Tomasz Napieralski
 * @created: 9 Jan 2015
 * @updated: 16 Jan 2015
 */

public class PerformTouchAction {
	
	private static WebDriver driver;
	private static AndroidDriver mobileDriver;
	
	private static int nativeHeight = 0;
	private static int nativeWidth = 0;
	private static int webviewHeight = 0;
	private static int webviewWidth = 0;
	private static int ratio = 0;
	
	private static int taskbarNativeHeight = 0;
	private static int taskbarNativeWidth = 0;
	private static int taskbarWebviewHeight = 0;
	private static int taskbarWebviewWidth = 0;
	
	private static int appNativeHeight = 0;
	private static int appNativeWidth = 0;
	private static int appWebviewHeight = 0;
	private static int appWebviewWidth = 0;
	
	private static int loadedPageHeight = 0;
	private static int loadedPageWidth = 0;
	
	public static final String DIRECTION_LEFT = "left";
	public static final String DIRECTION_RIGHT = "right";
	public static final String DIRECTION_UP = "up";
	public static final String DIRECTION_DOWN = "down";
	
	public static final String ZOOM_WAY_IN = "in";
	public static final String ZOOM_WAY_OUT = "out";
	
	public PerformTouchAction (WebDriver webDriver) {
		mobileDriver = NewDriverProvider.getMobileDriver();
		driver = webDriver;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		nativeHeight = mobileDriver.manage().window().getSize().height;
		nativeWidth = mobileDriver.manage().window().getSize().width;
		int startX = nativeWidth / 2;
		int startY = nativeHeight - 1;
		int endX = startX;
		int endY = nativeHeight / 3;
		int duration = startY - endY;
		try {
			mobileDriver.swipe(startX, startY, endX, endY, duration);
		} catch (Exception e) {}
		try {
			Thread.sleep(duration * 3);
		} catch(Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
		js.executeScript("window.scrollTo(0, 0)");
		appWebviewHeight = Integer.parseInt(js.executeScript("return $(window).height()").toString());
		appWebviewWidth = Integer.parseInt(js.executeScript("return $(window).width()").toString());
		loadedPageHeight = Integer.parseInt(js.executeScript("return $(document).height()").toString());
		loadedPageWidth = Integer.parseInt(js.executeScript("return $(document).width()").toString());
		ratio = nativeWidth / appWebviewWidth;
		webviewHeight = nativeHeight / ratio;
		webviewWidth = appWebviewWidth;
		taskbarWebviewHeight = webviewHeight - appWebviewHeight;
		taskbarWebviewWidth = webviewWidth;
		taskbarNativeHeight = taskbarWebviewHeight * ratio;
		taskbarNativeWidth = nativeWidth;
		appNativeWidth = nativeWidth;
		appNativeHeight = nativeHeight - taskbarNativeHeight;
		VarStatus();
	}
	
	/**
	 * That method return value of all resolution variables.
	 */
	public void VarStatus () {
		System.out.println("=======================================");
		System.out.println("nativeHeight: " + nativeHeight); //1920
		System.out.println("nativeWidth: " + nativeWidth); //1080
		System.out.println("webviewHeight: " + webviewHeight); //640
		System.out.println("webviewWidth: " + webviewWidth); //360
		System.out.println("ratio (nativeWidth / appWebviewWidth): " + ratio); //3
		System.out.println("=======================================");
		System.out.println("taskbarNativeHeight: " + taskbarNativeHeight); //243 should be 75
		System.out.println("taskbarNativeWidth: " + taskbarNativeWidth); //1080
		System.out.println("taskbarWebviewHeight: " + taskbarWebviewHeight); //81 should be 25
		System.out.println("taskbarWebviewWidth: " + taskbarWebviewWidth); //360
		System.out.println("=======================================");
		System.out.println("appNativeWidth: " + appNativeWidth); //1080
		System.out.println("appNativeHeight: " + appNativeHeight); //1677 should be 1845
		System.out.println("appWebviewHeight: " + appWebviewHeight); //559 should be 615
		System.out.println("appWebviewWidth: " + appWebviewWidth); //360
		System.out.println("=======================================");
		System.out.println("loadedPageHeight: " + loadedPageHeight); //2484
		System.out.println("loadedPageWidth: " + loadedPageWidth); //360
		System.out.println("=======================================");
	}
	
	/**
	 * 
	 * @param direction Use public const DIRECTION from that class
	 * @param pixelPath	From 0 to half of screen X or Y, if path will be too high it will be set to edge of app
	 * @param duration In milliseconds
	 * @param waitAfter In milliseconds, Reccomend 2*duration
	 */
	public void SwipeFromCenterToDirection (String direction, int pixelPath, int duration, int waitAfter){
		int centerX = appNativeWidth / 2;
		int centerY = appNativeHeight / 2;
		int path = 0;
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		switch (direction) {
			case "left": 
				if (pixelPath < centerX) {
					path = pixelPath;
				} else {
					path = centerX - 2;
				}
				try {
					mobileDriver.swipe(centerX, centerY, centerX - path, centerY, duration);
				} catch (Exception e) {}
				break;
			case "right": 
				if (pixelPath < centerX) {
					path = pixelPath;
				} else {
					path = centerX - 2;
				}
				try {
					mobileDriver.swipe(centerX, centerY, centerX + path, centerY, duration);	
				} catch (Exception e) {}
				break;
			case "up": 
				if (pixelPath < centerY) {
					path = pixelPath;
				} else {
					path = centerY - 2;
				}
				try {
					mobileDriver.swipe(centerX, centerY, centerX, centerY - path, duration);				
				} catch (Exception e) {}
				break;
			case "down": 
				if (pixelPath < centerY) {
					path = pixelPath;
				} else {
					path = centerY - 2;
				}
				try {						
					mobileDriver.swipe(centerX, centerY, centerX, centerY + path, duration);	
				} catch (Exception e) {}
				break;
			default: break;
		}
		try {
			Thread.sleep(waitAfter);
		} catch (Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
	/**
	 * 
	 * @param startX Use value 0-100, it is percent of app width, Reccomend 10-90
	 * @param startY Use value 0-100, it is percent of app height, Reccomend 10-90
	 * @param endX Use value 0-100, it is percent of app width, Reccomend 10-90
	 * @param endY Use value 0-100, it is percent of app height, Reccomend 10-90
	 * @param duration In milliseconds
	 * @param waitAfter In milliseconds, Reccomend 2*duration
	 */
	public void SwipeFromPointToPoint (int startX, int startY, int endX, int endY, int duration, int waitAfter) {		
		startX = (int)((startX / 100f) * appNativeWidth);
		startY = (int)((startY / 100f) * appNativeHeight);
		endX = (int)((endX / 100f) * appNativeWidth);
		endY = (int)((endY / 100f) * appNativeHeight);
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		try {
			mobileDriver.swipe(startX, startY, endX, endY, duration);
		} catch (Exception e) {}
		try {
			Thread.sleep(waitAfter);
		} catch (Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
	/**
	 * It uses two fingers to zoom in.
	 * @param startX Use value 0-100, it is percent of app width, Reccomend 50
	 * @param startY Use value 0-100, it is percent of app height, Reccomend 10-90
	 * @param fingersSpace Space between two fingers, In pixel, It must be less than pixelPath, Reccomend 20-100
	 * @param pixelPath From 0 to half of screen X, if path will be too high it will be set to edge of app
	 * @param zoomWay Use public const ZOOM_WAY from that class
	 * @param waitAfter In milliseconds
	 */
	public void ZoomInOutPointXY (int pointX, int pointY, int fingersSpace, int pixelPath, String zoomWay, int waitAfter) {
		pointX = (int)((pointX / 100f) * appNativeWidth);
		pointY = (int)((pointY / 100f) * appNativeHeight);
		TouchAction touchOne = new TouchAction(mobileDriver);
		TouchAction touchTwo = new TouchAction(mobileDriver);
		MultiTouchAction multiTouch = new MultiTouchAction(mobileDriver);
		int halfPath = 0;
		int endXOne = 0;
		int endXTwo = 0;
		int offSet = 0;
		int startXOne = 0;
		int startXTwo = 0;
		if (fingersSpace >= pixelPath) {
			fingersSpace = pixelPath - 2;
		}
		fingersSpace /= 2;
		if (pixelPath < appNativeWidth) {
			halfPath = pixelPath / 2;
		} else {
			halfPath = (appNativeWidth - 2) / 2;
		}
		endXOne = pointX - halfPath;
		if (endXOne < 0) {
			offSet = -endXOne;
			endXOne = 0;
		}
		endXTwo = pointX + halfPath;
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
		startXOne = pointX - fingersSpace;
		if (startXOne <= 0) {
			startXOne = 1;
		}
		startXTwo = pointX + fingersSpace;
		if (startXTwo >= (appNativeWidth - 2)) {
			startXTwo = appNativeWidth - 3;
		}
		if (zoomWay == "out") {
			int temp;
			temp = startXOne;
			startXOne = endXOne;
			endXOne = temp;
			temp = startXTwo;
			startXTwo = endXTwo;
			endXTwo = temp;
		}
		System.out.println("startXOne: "+startXOne+", endXOne: "+endXOne+", startXTwo: "+startXTwo+", endXTwo: "+endXTwo+" ");
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		touchOne.press(startXOne, pointY).moveTo(endXOne, pointY).release();
		touchTwo.press(startXTwo, pointY).moveTo(endXTwo, pointY).release();
		multiTouch.add(touchOne);
		multiTouch.add(touchTwo);
		try {
			multiTouch.perform();
		} catch (Exception e) {}
		try {
			Thread.sleep(waitAfter);
		} catch (Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
	/**
	 * 
	 * @param pointX Use value 0-100, it is percent of app width, Reccomend 10-90
	 * @param pointY Use value 0-100, it is percent of app height, Reccomend 10-90
	 * @param duration In milliseconds, Reccomend 500
	 * @param waitAfter In milliseconds
	 */
	public void TapOnPointXY (int pointX, int pointY, int duration, int waitAfter) {
		pointX = (int)((pointX / 100f) * appNativeWidth);
		pointY = (int)((pointY / 100f) * appNativeHeight);
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		try {
			mobileDriver.tap(1, pointX, pointY, duration);
		} catch (Exception e) {}
		try {
			Thread.sleep(waitAfter);
		} catch (Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
	/**
	 * It scroll to element then tap on his center.
	 * It scroll only vertically so if element need to be scrolled horizontally it won't work
	 * @param locator Use By selector, Example By.cssSelector('img.loaded')
	 * @param index If locator find more than one element use index to access element you want
	 * @param duration In milliseconds, Reccomend 500
	 * @param waitAfter In milliseconds
	 */
	public void TapOnWebElement (By locator, int index, int duration, int waitAfter) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element;
		if (index > 0) {
			List<WebElement> elements = driver.findElements(locator);
			element = elements.get(index);
		} else {
			element = driver.findElement(locator);
		}
		int elementStartPointX = element.getLocation().getX();
		int elementStartPointY = element.getLocation().getY();
		int elementHeight = element.getSize().getHeight();
		int elementWidth = element.getSize().getHeight();
		int offSetY = loadedPageHeight - element.getLocation().getY() - appWebviewHeight;
		if (offSetY < 0) {
			offSetY = -offSetY;
		} else {
			offSetY = 0;
		}
		js.executeScript("window.scrollTo(0, "+element.getLocation().getY()+")");
		int pointX = (elementStartPointX + (elementWidth / 2)) * ratio;
		int pointY = (taskbarWebviewHeight + (elementHeight / 2) + offSetY) * ratio; 
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		try {
			mobileDriver.tap(1, pointX, pointY, duration);
		} catch (Exception e) {}
		try {
			Thread.sleep(waitAfter);
		} catch (Exception e) {}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
}
