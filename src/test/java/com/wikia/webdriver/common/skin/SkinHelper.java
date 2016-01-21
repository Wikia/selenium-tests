package com.wikia.webdriver.common.skin;

import com.wikia.webdriver.elements.mercury.old.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SkinHelper extends BasePageObject{

  @FindBy(css = "body.skin-oasis")
  private WebElement oasisClassInBody;
  @FindBy(css = "body.ember-application")
  private WebElement mercuryClassInBody;

  public SkinHelper(WebDriver driver) {
    super(driver);
  }

  public boolean isSkin(Skin skin) {
    switch (skin) {
      case OASIS:
        return wait.forElementInViewPort(oasisClassInBody);
      case MERCURY:
        return wait.forElementInViewPort(mercuryClassInBody);
      default:
        return false;
    }
  }
}

