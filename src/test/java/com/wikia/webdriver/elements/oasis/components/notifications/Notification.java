package com.wikia.webdriver.elements.oasis.components.notifications;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import jdk.nashorn.internal.objects.NativeRegExp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Notification extends BasePageObject {

    private WebElement parentElement;
    private WebElement messageTextBox;

    private By messageBy = By.className("msg");
    private By undeleteLinkBy = By.cssSelector(".banner-notification div.msg a");

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

    public void undelete(){
        WebElement undeleteLink  = wait.forElementVisible(undeleteLinkBy);
        undeleteLink.click();
    }

    public SpecialRestorePageObject clickUndeleteLinkInBannerNotification() {
        WebElement undeleteLink  = wait.forElementVisible(undeleteLinkBy);
        undeleteLink.click();
        return new SpecialRestorePageObject(driver);
    }

    public boolean isVisible() {
        return parentElement.isDisplayed();
    }
}
