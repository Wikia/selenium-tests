package com.wikia.webdriver.common.core.helpers;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePurger extends BasePageObject {

  @FindBy(css = ".mw-htmlform-submit")
  private WebElement confirmationButton;

  public ArticlePurger() {
    super();
  }

  public void purgeArticleAsLoggedUser() {
    driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), "action=purge"));
  }

  public void purgeArticleAsAnon() {
    driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), "action=purge"));
    wait.forElementVisible(confirmationButton);
    confirmationButton.click();
  }
}
