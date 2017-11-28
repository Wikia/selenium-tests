package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

@AllArgsConstructor
public class Transitions {

  private static final long TIMEOUT_IN_SECONDS = 5;

  private final WebDriver webDriver;

  public void waitForPostDetailsPageTransition() {
    waitToAppear("post-details-view");
  }

  private void waitToAppear(final String cssClassName) {
    new WebDriverWait(webDriver, TIMEOUT_IN_SECONDS).until(new Function<WebDriver, Boolean>() {
      @Override
      public Boolean apply(@Nullable WebDriver input) {
        return ExpectedConditions.presenceOfElementLocated(By.className(cssClassName))
            .apply(input)
            .isDisplayed();
      }
    });
  }

  public void waitForUserPostsPageTransition() {
    waitToAppear("discussion-user-list");
  }
}
