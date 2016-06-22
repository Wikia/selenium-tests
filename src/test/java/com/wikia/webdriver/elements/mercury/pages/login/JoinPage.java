package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinPage extends WikiBasePageObject {

    @FindBy(css = ".footer-callout-emphasis")
    private WebElement SignInButton;

    private Wait wait;

    public JoinPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new Wait(driver);
    }

    public SignInPage clickSignInButton() {
        wait.forElementClickable(SignInButton);
        SignInButton.click();

        return new SignInPage(driver);
    }
}

