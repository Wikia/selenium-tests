package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.contentpatterns.MobilePageContent;
import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileSpecialUserLogin extends MobileBasePageObject {

  @FindBy(css = ".wkErr")
  private WebElement errorMessage;

  public MobileSpecialUserLogin(WebDriver driver) {
    super(driver);
  }

  public void verifyWrongPasswordErrorMessage() {
    wait.forElementVisible(errorMessage);
    Assertion.assertEquals(
            errorMessage.getText(), MobilePageContent.LOGIN_WRONG_PASSWORD_ERROR_MESSAGE
    );
  }

  public void verifyWrongLoginErrorMessage() {
    wait.forElementVisible(errorMessage);
    Assertion.assertEquals(
            errorMessage.getText(), MobilePageContent.LOGIN_WRONG_LOGIN_ERROR_MESSAGE
    );
  }

  public void verifyEmptyPasswordErrorMessage() {
    wait.forElementVisible(errorMessage);
    Assertion.assertEquals(
            errorMessage.getText(), MobilePageContent.LOGIN_EMPTY_PASSWORD_ERROR_MESSAGE
    );
  }
}
