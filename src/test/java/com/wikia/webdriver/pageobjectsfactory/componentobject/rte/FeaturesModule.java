package com.wikia.webdriver.pageobjectsfactory.componentobject.rte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeaturesModule {
  @FindBy(css = ".cke_button__addinfobox span.cke_button_label")
  private WebElement infoboxButton;

  private final WebDriver webDriver;
  private final WebDriverWait webDriverWait;

  public FeaturesModule(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
    this.webDriverWait = new WebDriverWait(webDriver, 1);
  }

  public InfoboxChoiceModal openInfoboxChoiceModal() {
    webDriverWait.until(ExpectedConditions.elementToBeClickable(infoboxButton));
    infoboxButton.click();

    return new InfoboxChoiceModal(webDriver);
  }
}
