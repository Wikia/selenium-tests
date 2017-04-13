package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by sczerwinski on 13/04/2017.
 */
public class Notification extends BasePageObject {

    WebElement parentElement;
    WebElement messageTextBox;
    By messageBy = By.className("msg");

    public Notification(WebDriver driver, WebElement parentElement) {
        super();
        this.parentElement = parentElement;
        this.messageTextBox = parentElement.findElement(messageBy);
    }

    public String getMessage(){
        return messageTextBox.getText();
    }

    public String getType(){
        String[] classes =  "banner confirm non-dismissable".split(" ",2);
        return classes[classes.length-1];
    }

}
