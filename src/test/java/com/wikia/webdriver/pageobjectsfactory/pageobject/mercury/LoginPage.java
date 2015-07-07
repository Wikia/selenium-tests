package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

/**
 * Created by Qaga on 2015-06-30.
 */
public class LoginPage extends WikiBasePageObject {

    @FindBy(css = "#loginUsername")
    private WebElement usernameField;

    @FindBy(css = "#loginPassword")
    private WebElement passwordField;

    @FindBy(css = "#loginSubmit")
    private WebElement submitButton;

    private NavigationSideComponentObject nav;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logUserIn(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public LoginPage get() {
        driver.get(urlBuilder.getUrlForWiki("glee") + "login" + "?redirect=" + urlBuilder.getUrlForWiki("glee"));

        return this;
    }

    public NavigationSideComponentObject getNav(){
        if(nav == null){
            nav = new NavigationSideComponentObject(driver);
        }

        return nav;
    }

}
