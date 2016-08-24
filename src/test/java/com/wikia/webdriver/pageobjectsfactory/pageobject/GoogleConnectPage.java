package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleConnectPage extends WikiBasePageObject {

  @FindBy(css = "#message")
  private WebElement message;

  @FindBy(css = ".abcRioButtonContentWrapper")
  private WebElement signUpButton;

  public GoogleConnectPage(WebDriver driver) {
    super();
  }

  public boolean isUserLoggedIn() {
    return wait.forTextInElement(message, "You are now logged in");
  }

  public GoogleConnectPage open() {
    if (Configuration.getEnvType().equals(EnvType.DEV)) {
      getUrl("https://services.wikia-dev.com/external-auth/login/google");
    } else {
      getUrl("https://services.wikia.com/external-auth/login/google");
    }

    return this;
  }

  public GoogleConnectPage signInWithGoogleAccount(String userName, String password) {
    signUpButton.click();

    new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfWindowsToBe(2));
    driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

    new GoogleLoginPopup(driver).SignInToGoogle(userName, password);
    driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

    return this;
  }
}
