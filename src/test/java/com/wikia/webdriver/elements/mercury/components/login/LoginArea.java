package com.wikia.webdriver.elements.mercury.components.login;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginArea {

    @FindBy(css = "#loginUsername")
    private WebElement usernameField;

    @FindBy(css = "#loginPassword")
    private WebElement passwordField;

    @FindBy(css = "#loginSubmit")
    private WebElement signInButton;

    private WebDriver driver;
    private Wait wait;

    public LoginArea(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);

        PageFactory.initElements(driver, this);
    }

    public LoginArea typeUsername(String username) {
        wait.forElementVisible(usernameField);
        usernameField.sendKeys(username);

        return this;
    }

    public LoginArea typePassword(String password) {
        wait.forElementVisible(passwordField);
        passwordField.sendKeys(password);

        return this;
    }

    public ArticlePage clickSignInButton() {
        wait.forElementClickable(signInButton);
        signInButton.click();

        return new ArticlePage();
    }
}
