package com.wikia.webdriver.elements.communities.desktop.components.categories;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaginationControls<T> extends BasePageObject {

  private final T outer;
  @FindBy(xpath = "//a[contains(text(),'First')]")
  private WebElement firstButton;
  @FindBy(css = ".category-page__pagination-next")
  private WebElement nextButton;
  @FindBy(css = ".category-page__pagination-prev")
  private WebElement previousButton;
  @FindBy(xpath = "//a[contains(text(),'Last')]")
  private WebElement lastButton;

  public PaginationControls (T outer) {
    this.outer = outer;
  }

  public boolean isFirstButtonIsVisible() {
    return isVisible(firstButton);
  }

  public boolean isNextButtonVisible() {
    return isVisible(nextButton);
  }

  public boolean isPreviousButtonVisible() {
    return isVisible(previousButton);
  }

  public boolean isLastButtonVisible() {
    return isVisible(lastButton);
  }

  public T clickFirstButton() {
    this.scrollAndClick(firstButton);
    Log.info("First page button clicked.");

    return outer;
  }

  public T clickNextButton() {
    this.scrollAndClick(nextButton);
    Log.info("Next page button clicked.");

    return outer;
  }

  public T clickPreviousButton() {
    this.scrollAndClick(previousButton);
    Log.info("Previous page button clicked.");

    return outer;
  }

  public T clickLastButton() {
    this.scrollAndClick(lastButton);
    Log.info("Last page button clicked.");

    return outer;
  }
}
