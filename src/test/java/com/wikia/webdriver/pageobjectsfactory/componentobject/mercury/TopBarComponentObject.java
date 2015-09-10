package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Social
 */
public class TopBarComponentObject extends BasePageObject {

    @FindBy(css = ".icon.login")
    private WebElement loginIcon;

    public TopBarComponentObject(WebDriver driver) {
        super(driver);
    }

    public void clickLogInIcon(){
        wait.forElementVisible(loginIcon);
        loginIcon.click();
    }
}
