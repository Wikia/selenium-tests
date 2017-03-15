package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends WikiBasePageObject {

    @FindBy(css = ".footer-callout-emphasis")
    private WebElement signInButton;
    @FindBy(css = ".signup-providers li a")
    private WebElement connectWithFacebookButton;

    private Wait wait;

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new Wait(driver);
    }

    public SignInPage clickSignInButton() {
        wait.forElementClickable(signInButton);
        signInButton.click();

        return new SignInPage(driver);
    }

    public RegisterPage isConnetctWithFacebookButtonVisible() {
        wait.forElementVisible(connectWithFacebookButton);

        return this;
    }
}

