package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomeBackPage extends WikiBasePageObject {

    @FindBy(css = "#loginSubmit")
    private WebElement signInButton;
    @FindBy(css = "#loginUsername")
    private WebElement usernameField;
    @FindBy(css = "#loginPassword")
    private WebElement passwordField;

    private Wait wait;

    public WelcomeBackPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new Wait(driver);
    }

    public WelcomeBackPage clickSignInButton() {
        wait.forElementClickable(signInButton);
        signInButton.click();

        return new WelcomeBackPage(driver);
    }

    public WelcomeBackPage typeUsername(String name) {
        wait.forElementVisible(usernameField);
        usernameField.sendKeys(name);

        return new WelcomeBackPage(driver);
    }

    public WelcomeBackPage typePassword(String password) {
        wait.forElementVisible(passwordField);
        passwordField.sendKeys(password);

        return new WelcomeBackPage(driver);
    }
}

