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
 * @updated: 14 Jan 2015
 */

public class PerformTouchAction {
	
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
	
	private static int elementStartPointX = 0;
	private static int elementStartPointY = 0;
	private static int elementHeight = 0;
	private static int elementWidth = 0;
	
	public static final String DIRECTION_LEFT = "left";
	public static final String DIRECTION_RIGHT = "right";
	public static final String DIRECTION_UP = "up";
	public static final String DIRECTION_DOWN = "down";
	
	public PerformTouchAction (WebDriver driver, AndroidDriver mobileDriver, Boolean showSmartBanner) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (!showSmartBanner) {
			js.executeScript("document.cookie='sb-closed=1'");
		}
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
		System.out.println("elementStartPointX: " + elementStartPointX);
		System.out.println("elementStartPointY: " + elementStartPointY);
		System.out.println("elementHeight: " + elementHeight);
		System.out.println("elementWidth: " + elementWidth);
		System.out.println("=======================================");
	}
	
	/**
	 * 
	 * @param mobileDriver
	 * @param direction Use public var DIRECTION from that class
	 * @param pixelPath	From 0 to half of screen X or Y, if path will be too high it will be set to edge of app
	 * @param duration In milliseconds
	 * @param waitAfter In milliseconds
	 */
	public void SwipeFromCenterToDirection (AndroidDriver mobileDriver, String direction, int pixelPath, int duration, int waitAfter){
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
	 * @param mobileDriver
	 * @param startX Use value 0-100, it is percent of app width
	 * @param startY Use value 0-100, it is percent of app height
	 * @param endX Use value 0-100, it is percent of app width
	 * @param endY Use value 0-100, it is percent of app height
	 * @param duration In milliseconds
	 * @param waitAfter In milliseconds
	 */
	public void SwipeFromPointToPoint (AndroidDriver mobileDriver, int startX, int startY, int endX, int endY, int duration, int waitAfter) {		
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
	 * 
	 * @param mobileDriver
	 * @param startX Use value 0-100, it is percent of app width
	 * @param startY Use value 0-100, it is percent of app height
	 * @param pixelPath From 0 to half of screen X, if path will be too high it will be set to edge of app
	 */
	public void ZoomInPointXY (AndroidDriver mobileDriver, int startX, int startY, int pixelPath) {
		startX = (int)((startX / 100f) * appNativeWidth);
		startY = (int)((startY / 100f) * appNativeHeight);
		TouchAction touchOne = new TouchAction(mobileDriver);
		TouchAction touchTwo = new TouchAction(mobileDriver);
		MultiTouchAction multiTouch = new MultiTouchAction(mobileDriver);
		int halfPath = 0;
		int endXOne = 0;
		int endXTwo = 0;
		int offSet = 0;
		if (pixelPath < appNativeWidth) {
			halfPath = pixelPath / 2;
		} else {
			halfPath = (appNativeWidth - 2) / 2;
		}
		endXOne = startX - halfPath;
		if (endXOne < 0) {
			offSet = -endXOne;
			endXOne = 0;
		}
		endXTwo = startX + halfPath;
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
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		touchOne.press(startX, startY).moveTo(endXOne, startY).release();
		touchTwo.press(startX, startY).moveTo(endXTwo, startY).release();
		multiTouch.add(touchOne);
		multiTouch.add(touchTwo);
		multiTouch.perform();
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
}
