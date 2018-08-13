package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.driverprovider.DriverProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Frame {

  private final WebDriver webDriver;
  private final WebElement frameElement;

  public Frame(WebElement frameElement) {
    this.webDriver = DriverProvider.getActiveDriver();
    this.frameElement = frameElement;
  }

  public void frameScope(FrameScope frameScope) {
    try {
      webDriver.switchTo().frame(frameElement);
      frameScope.execute();
    } finally {
      webDriver.switchTo().defaultContent();
    }
  }

  public <T> T frameScope(Supplier<T> frameScopedSupplier) {
    try {
      webDriver.switchTo().frame(frameElement);
      return frameScopedSupplier.get();
    } finally {
      webDriver.switchTo().defaultContent();
    }
  }
}
