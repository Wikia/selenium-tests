package com.wikia.webdriver.common.core.helpers;

import java.util.Map;

import lombok.Getter;

import com.google.common.collect.ImmutableMap;

public enum Emulator {
  GOOGLE_NEXUS_5(
      new ImmutableMap.Builder<String, Object>()
          .put("width", 360)
          .put("height", 640)
          .put("pixelRatio", 3.0)
          .put("touch", true)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"),
  APPLE_IPHONE_6(
      new ImmutableMap.Builder<String, Object>()
          .put("width", 375)
          .put("height", 667)
          .put("pixelRatio", 2.0)
          .put("touch", true)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"),
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
  @Getter
  private String userAgent;

  Emulator(String deviceName) {
    this.deviceName = deviceName;
    this.userAgent = "";
  }

  Emulator(Map<String, Object> deviceMetrics) {
    this.deviceMetrics = deviceMetrics;
    this.userAgent = "";
  }

  Emulator(Map<String, Object> deviceMetrics, String userAgent) {
    this.deviceMetrics = deviceMetrics;
    this.userAgent = userAgent;
  }
}
