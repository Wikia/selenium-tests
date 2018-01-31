package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.Dimension;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AssertionAds {
  public static void assertAspectRatio(Dimension size, double expected) {
    final double actual = (double) size.getWidth() / (double) size.getHeight();
    // Some divergent is possible because of browser size rounding
    Assertion.assertEquals(roundAspectRatio(actual), roundAspectRatio(expected), 0.03, "Aspect ratios are divergent");
  }

  private static double roundAspectRatio(double aspectRatio) {
    return new BigDecimal(aspectRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
