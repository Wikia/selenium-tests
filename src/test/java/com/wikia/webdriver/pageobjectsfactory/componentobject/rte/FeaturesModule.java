package com.wikia.webdriver.pageobjectsfactory.componentobject.rte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeaturesModule {
  @FindBy(css = ".RTEInfoboxButton span.cke_label")
  private WebElement infoboxButton;

  private final WebDriver webDriver;

  public FeaturesModule(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
  }

  public InfoboxChoiceModal openInfoboxChoiceModal() {
    infoboxButton.click();
    return new InfoboxChoiceModal(webDriver);
  }
}
