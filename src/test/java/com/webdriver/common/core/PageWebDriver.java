package com.webdriver.common.core;

import com.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class PageWebDriver extends EventFiringWebDriver {

  private WebDriver webDriver;
  private boolean isMobile;
  private NetworkTrafficInterceptor proxy = null;

  public PageWebDriver(WebDriver webdriver, boolean isMobile) {
    super(webdriver);

    this.webDriver = webdriver;
    this.isMobile = isMobile;
  }

  public PageWebDriver(WebDriver webdriver, NetworkTrafficInterceptor proxy, boolean isMobile) {
    super(webdriver);

    this.webDriver = webdriver;
    this.isMobile = isMobile;
    this.proxy = proxy;
  }

  public NetworkTrafficInterceptor getProxy() {
    return proxy;
  }

  public boolean isChrome() {
    return webDriver instanceof ChromeDriver && !isMobile;
  }

  public boolean isChromeMobile() {
    return webDriver instanceof ChromeDriver && isMobile;
  }

  public boolean htmlUnit() {
    return webDriver instanceof HtmlUnitDriver;
  }

  public boolean isAndroid() {
    return webDriver instanceof AndroidDriver;
  }

  public boolean isFirefox() {
    return webDriver instanceof FirefoxDriver;
  }

  @Override
  public void quit() {
    if (proxy != null) {
      proxy.stop();
    }
    super.quit();
  }
}
