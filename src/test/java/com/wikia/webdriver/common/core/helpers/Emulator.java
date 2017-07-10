package com.wikia.webdriver.common.core.helpers;

import java.util.Map;

import lombok.Getter;

import com.google.common.collect.ImmutableMap;

public enum Emulator {
  GOOGLE_NEXUS_5("Google Nexus 5"),
  APPLE_IPHONE_6("Apple iPhone 6"),
  DESKTOP_BREAKPOINT_BIG(
      new ImmutableMap.Builder<String, Object>()
          .put("width", 1296)
          .put("height", 900)
          .put("pixelRatio", 1.0)
          .put("touch", false)
          .put("mobile", false)
          .build()),
  DESKTOP_BREAKPOINT_SMALL(
      new ImmutableMap.Builder<String, Object>()
          .put("width", 1024)
          .put("height", 720)
          .put("pixelRatio", 1.0)
          .put("touch", false)
          .put("mobile", false)
          .build()),
  DESKTOP_BREAKPOINT_LARGE(
      new ImmutableMap.Builder<String, Object>()
          .put("width", 1920)
          .put("height", 1080)
          .put("pixelRatio", 1.0)
          .put("touch", false)
          .put("mobile", false)
          .build()),
  DEFAULT("");

  @Getter
  private String deviceName;
  @Getter
  private Map<String, Object> deviceMetrics;

  Emulator(String deviceName) {
    this.deviceName = deviceName;
  }

  Emulator(Map<String, Object> deviceMetrics) {
    this.deviceMetrics = deviceMetrics;
  }
}
