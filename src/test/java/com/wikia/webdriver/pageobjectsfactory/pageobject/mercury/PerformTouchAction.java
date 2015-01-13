package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.logging.PageObjectLogging;

/*
 * @authors: Tomasz Napieralski
 * @created: 9 Jan 2015
 * @updated: 12 Jan 2015
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
	
	public PerformTouchAction (WebDriver driver, AndroidDriver mobileDriver, Boolean showSmartBanner) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (!showSmartBanner) {
			js.executeScript("document.cookie='sb-closed=1'");
		}
		mobileDriver.context("NATIVE_APP");
		nativeHeight = mobileDriver.manage().window().getSize().height;
		nativeWidth = mobileDriver.manage().window().getSize().width;
		mobileDriver.context("WEBVIEW_1");
		System.out.println(mobileDriver.getContext());
		try {
			int startX = nativeWidth / 2;
			int startY = nativeHeight - 1;
			int endX = startX;
			int endY = nativeHeight / 3;
			int duration = startY - endY;
			mobileDriver.context("NATIVE_APP");
			mobileDriver.swipe(startX, startY, endX, endY, duration);
			mobileDriver.context("WEBVIEW_1");
			System.out.println("after swipe");
			try {
				Thread.sleep(duration * 2);
			} catch(Exception e) {System.out.println(e);}
			js.executeScript("window.scrollTo(0, 0)");
		} catch (Exception e) {System.out.println(e);}
		System.out.println(mobileDriver.getContext());
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
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
	
	public static void VarStatus () {
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
	
	public void SwipeFromCenterToDirection (WebDriver driver, AndroidDriver mobileDriver, String direction, int pixelPath, int duration){
		int centerX = appNativeWidth / 2;
		int centerY = appNativeHeight / 2;
		int path = 0;
		switch (direction) {
			case "left": 
				try {
					if (pixelPath < centerX) {
						path = pixelPath;
					} else {
						path = centerX - 1;
					}
					System.out.println(centerX + " " + centerY + " " + (centerX - path) + " " + centerY + " " + duration);
					mobileDriver.context("NATIVE_APP");
					mobileDriver.swipe(centerX, centerY, centerX - path, centerY, duration);
//					mobileDriver.swipe(360, 615, 300, 615, duration);
					mobileDriver.context("WEBVIEW_1");
					try {
						Thread.sleep(duration * 2);
					} catch (Exception e) {}
				} catch (Exception e) {}
				break;
				
			default: break;
		}
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
	}
	
	
	
	//Testing ground
	public static void SwipeToWebElement (WebDriver driver, AndroidDriver mobileDriver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.cookie='sb-closed=1'");
		elementStartPointX = element.getLocation().x;
		elementStartPointY = element.getLocation().y;
//		System.out.println(js.executeScript());
		appWebviewHeight = Integer.parseInt(js.executeScript("return $(window).height()").toString());
		appWebviewWidth = Integer.parseInt(js.executeScript("return $(window).width()").toString());
		loadedPageHeight = Integer.parseInt(js.executeScript("return $(document).height()").toString());
		loadedPageWidth = Integer.parseInt(js.executeScript("return $(document).width()").toString());
		mobileDriver.context("NATIVE_APP");
		nativeHeight = mobileDriver.manage().window().getSize().height;
		nativeWidth = mobileDriver.manage().window().getSize().width;
		mobileDriver.context("WEBVIEW_1");
		
		System.out.println("nativeHeight: "+nativeHeight); //1920
		System.out.println("nativeWidth: "+nativeWidth); //1080
		System.out.println("appWebviewHeight: "+appWebviewHeight); //559 should be 615
		System.out.println("appWebviewWidth: "+appWebviewWidth); //360
		System.out.println("loadedPageHeight: "+loadedPageHeight); //2484
		System.out.println("loadedPageWidth: "+loadedPageWidth); //360
		System.out.println("elementStartPointY: "+elementStartPointY); //1338
		System.out.println("elementStartPointX: "+elementStartPointX); //22
		
		ratio = nativeWidth / appWebviewWidth; //3
		webviewHeight = nativeHeight / ratio; //640
		webviewWidth = appWebviewWidth; //360
		taskbarWebviewHeight = webviewHeight - appWebviewHeight; //81 should be 25
		taskbarWebviewWidth = webviewWidth; //360
		taskbarNativeHeight = taskbarWebviewHeight * ratio; //243 should be 75
		taskbarNativeWidth = nativeWidth; //1080
		appNativeWidth = nativeWidth; //1080
		appNativeHeight = nativeHeight - taskbarNativeHeight; //1677
		
		System.out.println("=======================================");
		System.out.println("ratio: "+ratio);
		System.out.println("webviewHeight: "+webviewHeight);
		System.out.println("webviewWidth: "+webviewWidth);
		System.out.println("taskbarWebviewHeight: "+taskbarWebviewHeight);
		System.out.println("taskbarWebviewWidth: "+taskbarWebviewWidth);
		System.out.println("taskbarNativeHeight: "+taskbarNativeHeight);
		System.out.println("taskbarNativeWidth: "+taskbarNativeWidth);
		System.out.println("appNativeWidth: "+appNativeWidth);
		System.out.println("appNativeHeight: "+appNativeHeight);
		System.out.println("=======================================");
		
		
		//ele Y: 1338
		//app Y: 559
		
		
		
		
		try {
			Thread.sleep(3000);
			System.out.println("Before...");
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
//		System.out.println("getWindowHandle: "+mobileDriver.getWindowHandle());
		
		
		
//		mobileDriver.tap(1, 50, 243, 500);
		
		mobileDriver.context("NATIVE_APP");
		
		
		
		
//				mobileDriver.swipe(50, 1000, 50, 400, 600);
				
	
//		mobileDriver.tap(1, 200, 75, 300);
		
		
		
//		
//		int dur = deviceHeight - 1 - taskbarNativeHeight;
//		
//		for (int i=0; i<2; ++i){
//		
//			mobileDriver.swipe(50, deviceHeight-1, 50, taskbarNativeHeight,  dur);
//			
//			try {
//				Thread.sleep(dur+1);
//				System.out.println("Wait...");
//			} catch(InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//			
//		}
//		
//		
//		
//		int poz = 0;
//		PageObjectLogging.log("screen", "page", true, driver);
//		while ((elementStartPointY - poz) > appWebviewHeight) {
//		
//			mobileDriver.swipe(50, deviceHeight-1, 50, taskbarNativeHeight,  dur);
//			
//			try {
//				Thread.sleep(dur+1);
//				System.out.println("Wait...");
//			} catch(InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//			
//			PageObjectLogging.log("screen", "page", true, driver);
//			
//			poz += appWebviewHeight;
////			mobileDriver.swipe(50, deviceHeight-1, 50, taskbarNativeHeight,  dur);
//		
//		
//		}
		
//		System.out.println("capabilities: "+mobileDriver.getCapabilities());
//		System.out.println("Settings: "+mobileDriver.getSettings());
		
		
		//mobileDriver.swipe(startx, starty, endx, endy, duration);
		mobileDriver.context("WEBVIEW_1");
		try {
			Thread.sleep(3000);
			System.out.println("After...");
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		System.out.println("=======================================");
		System.out.println("elementStartPointY: "+elementStartPointY); //1338
		System.out.println("elementStartPointX: "+elementStartPointX); //22
		System.out.println("=======================================");
		List<WebElement> hubs = driver.findElements(By.cssSelector("img.wkhome-img"));
		WebElement hub = hubs.get(0);
		js.executeScript("window.scrollTo(0, 0)");
		System.out.println("elementStartPointY: "+hub.getLocation().y); //1338
		System.out.println("elementStartPointX: "+hub.getLocation().y); //22
		
		
	}
	
	
}
