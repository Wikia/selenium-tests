package com.wikia.webdriver.common.core.helpers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.Map;

public enum Emulator {
  GOOGLE_NEXUS_5(
      new ImmutableMap.Builder<String, Object>().put("width", 360)
          .put("height", 640)
          .put("pixelRatio", 3.0)
          .put("touch", true)
          .put("mobile", false)
          .build(),
      "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Mobile Safari/537.36",
      true
  ), GOOGLE_NEXUS_5_DEFAULT(
      // FIXME: this is workaround for mobile: false flag, remove it if original flag will be reverted
      new ImmutableMap.Builder<String, Object>().put("width", 360)
          .put("height", 640)
          .put("pixelRatio", 3.0)
          .put("touch", true)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Mobile Safari/537.36",
      true
  ), APPLE_IPHONE_6(
      new ImmutableMap.Builder<String, Object>().put("width", 375)
          .put("height", 667)
          .put("pixelRatio", 2.0)
          .put("touch", true)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25",
      true
  ), NEXUS_5X(
      new ImmutableMap.Builder<String, Object>().put("width", 412)
          .put("height", 732)
          .put("pixelRatio", 3.0)
          .put("touch", true)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Mobile Safari/537.36",
      true
  ), // NEXUS_5X_WITHOUT_TOUCH is a hack due to touch event issues, more: https://wikia-inc.atlassian.net/browse/ADEN-6264
  NEXUS_5X_WITHOUT_TOUCH(
      new ImmutableMap.Builder<String, Object>().put("width", 412)
          .put("height", 732)
          .put("pixelRatio", 3.0)
          .put("touch", false)
          .put("mobile", true)
          .build(),
      "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Mobile Safari/537.36",
      true
  ), DESKTOP_BREAKPOINT_BIG(new ImmutableMap.Builder<String, Object>().put("width", 1296)
                                .put("height", 900)
                                .put("pixelRatio", 1.0)
                                .put("touch", false)
                                .put("mobile", false)
                                .build(),
                            false
  ), DESKTOP_BREAKPOINT_SMALL(new ImmutableMap.Builder<String, Object>().put("width", 1024)
                                  .put("height", 720)
                                  .put("pixelRatio", 1.0)
                                  .put("touch", false)
                                  .put("mobile", false)
                                  .build(), false), DESKTOP_BREAKPOINT_LARGE(
      new ImmutableMap.Builder<String, Object>().put(
          "width",
          1920
      ).put("height", 1080).put("pixelRatio", 1.0).put("touch", false).put("mobile", false).build(),
      false
  ), DEFAULT("", false);

  @Getter
  private String deviceName;
  @Getter
  private Map<String, Object> deviceMetrics;
  @Getter
  private String userAgent;
  @Getter
  private boolean mobile;

  Emulator(String deviceName, boolean mobile) {
    this.deviceName = deviceName;
    this.userAgent = "";
    this.mobile = mobile;
  }

  Emulator(Map<String, Object> deviceMetrics, boolean mobile) {
    this.deviceMetrics = deviceMetrics;
    this.userAgent = "";
    this.mobile = mobile;
  }

  Emulator(Map<String, Object> deviceMetrics, String userAgent, boolean mobile) {
    this.deviceMetrics = deviceMetrics;
    this.userAgent = userAgent;
    this.mobile = mobile;
  }
}
