package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook;

import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ConfirmationMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(css="input[name='username']")
    private WebElement userNameField;
    @FindBy(css="input[name='password']")
    private WebElement passwordField;
    @FindBy(css=".login-button[value='Confirm and log in']")
    private WebElement submitButton;

    public ConfirmationMonoBookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String userName, String password) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        PageObjectLogging.log("fillForm", "form's fields filled", true, driver);
    }

    public void submitForm() {
        MailFunctions.deleteAllMails(Properties.email, Properties.emailPassword);
        clickAndWait(submitButton);
        PageObjectLogging.log(
            "submit button clicked ", "submit button clicked", true, driver
        );
    }

}
