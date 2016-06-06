package com.wikia.webdriver.elements.mercury.components.login;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.pages.login.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinButtons {

    @FindBy(css = ".footer-callout-emphasis")
    private WebElement SignInButton;

    private WebDriver driver;
    private Wait wait;

    public JoinButtons(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);

        PageFactory.initElements(driver, this);
    }

    public SignInPage clickSignInButton() {
        wait.forElementClickable(SignInButton);
        SignInButton.click();

        return new SignInPage();
    }
}
