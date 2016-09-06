package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

@AllArgsConstructor
public class Transitions {

  private final WebDriver webDriver;

  public void waitForPostDetailsTransition() {
    new WebDriverWait(webDriver, 5).until(new Predicate<WebDriver>() {
      @Override
      public boolean apply(@Nullable WebDriver input) {
        return ExpectedConditions.presenceOfElementLocated(By.className("post-details-view"))
            .apply(input)
            .isDisplayed();
      }
    });
  }
}
