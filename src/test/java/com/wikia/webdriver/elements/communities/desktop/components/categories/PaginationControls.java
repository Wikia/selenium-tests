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

  //TODO:Check if it actually works
  public boolean isFirstButtonIsVisible() {

    boolean isVisible = isVisible(firstButton);
//    Log.info("Next page button is visible.");

    return isVisible;
  }

  //TODO:Check if it actually works
  public boolean isNextButtonVisible() {

    boolean isVisible = isVisible(nextButton);
//    Log.info("Next page button is visible.");

    return isVisible;
  }

  //TODO:Check if it actually works
  public boolean isPreviousButtonVisible() {

    boolean isVisible = isVisible(previousButton);
//    Log.info("Previous page button is visible.");

    return isVisible;
  }

  //TODO:Check if it actually works
  public boolean isLastButtonVisible() {

    boolean isVisible = isVisible(lastButton);
//    Log.info("Next page button is visible.");

    return isVisible;
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
