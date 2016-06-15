package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.components.login.LoginArea;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by neptunooo on 06/06/16.
 */
public class SignInPage extends WikiBasePageObject{

    @Getter(lazy = true)
    private final LoginArea loginArea = new LoginArea(driver);

    @FindBy(css = ".error")
    private WebElement errorMessage;

    private Wait wait;

    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new Wait(driver);
    }

    public String getErrorMessage() {
        wait.forElementVisible(errorMessage);
        return errorMessage.getText();
    }

    public SignInPage verifyErrorMessage(String errorMessage) {
        Assertion.assertEquals(getErrorMessage(), errorMessage);
        return this;
    }
}

