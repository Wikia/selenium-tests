package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.channels.ByteChannel;

/**
 * Created by rcunningham on 6/30/15.
 */
public class MobileSignupPageObject extends MobileBasePageObject {

    public MobileSignupPageObject(WebDriver driver) { super(driver); }

    // UI Mapping
    @FindBy(css = "#signupEmail")
    private WebElement signupEmail;
    @FindBy(css = "#signupUsername")
    private WebElement signupUsername;
    @FindBy(css = "#signupPassword")
    private WebElement signupPassword;
    @FindBy(css = "#signupBirthDate")
    private WebElement signupBirthdate;
    @FindBy(css = ".birth-month")
    private WebElement signupBirthMonth;
    @FindBy(css = ".birth-day")
    private WebElement signupBirthDay;
    @FindBy(css = ".birth-year")
    private WebElement signupBirthYear;
    @FindBy(css = "#signupSubmit")
    private WebElement signupButton;
    @FindBy(css = ".avatar")
    private WebElement avatar;


    public MobileSignupPageObject typeEmailAddress(String email) {
        waitForElementByElement(signupEmail);
        signupEmail.sendKeys(email);
        return this;
    }

    public MobileSignupPageObject typeUsername(String username) {
        waitForElementByElement(signupUsername);
        signupUsername.sendKeys(username);
        return this;
    }

    public MobileSignupPageObject typePassword(String password) {
        waitForElementByElement(signupPassword);
        signupPassword.sendKeys(password);
        return this;
    }

    public MobileSignupPageObject typeBirthdate(String month, String day, String year) {
        waitForElementByElement(signupBirthdate);
        scrollAndClick((signupBirthdate));

        waitForElementByElement(signupBirthMonth);
        scrollAndClick((signupBirthMonth));
        signupBirthMonth.sendKeys(month);

        waitForElementByElement(signupBirthDay);
        signupBirthDay.click();
        signupBirthDay.sendKeys(day);

        waitForElementByElement(signupBirthYear);
        signupBirthYear.click();
        signupBirthYear.sendKeys(year);

        return this;
    }

    public void register() {
        waitForElementByElement(signupButton);
        scrollAndClick(signupButton);
    }

    public void verifyAvatarAfterSignup() {
        waitForElementByElement(avatar);
        Assertion.assertTrue(avatar.isDisplayed());
    }

}
