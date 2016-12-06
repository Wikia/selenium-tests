package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VUAP {
    private static By triggerButtonSelector = By.id("button");
    private static By UIElementsSelector = By.className("overVideoLayer");
    private WikiaWebDriver driver;
    private WikiBasePageObject pageObject;
    private WebElement iframe;
    private WebElement triggerButton;

    public VUAP(WikiaWebDriver driver, WikiBasePageObject pageObject, String slotName) {
        this.driver = driver;
        this.pageObject = pageObject;
        iframe = findIframe(driver, pageObject, slotName);
        triggerButton = findTriggerButton(driver, pageObject);
    }

    private WebElement findIframe(WikiaWebDriver driver, WikiBasePageObject pageObject, String slotName) {
        By iframeSelector = By.id(getAdUnit(slotName));
        pageObject.wait.forElementPresent(iframeSelector);
        return driver.findElement(iframeSelector);
    }

    private String getAdUnit(String slotName) {
        return "google_ads_iframe_/5441/wka.life/_project43//article/gpt/" + slotName + "_0";
    }

    private WebElement findTriggerButton(WikiaWebDriver driver, WikiBasePageObject pageObject) {
        driver.switchTo().frame(iframe);

        pageObject.wait.forElementClickable(triggerButtonSelector);
        WebElement element = driver.findElement(triggerButtonSelector);
        driver.switchTo().defaultContent();
        return element;
    }

    public WebElement getIframe() {
        return iframe;
    }

    public void play() {
        driver.switchTo().frame(iframe);

        try {
            Thread.sleep(2000); // TODO replace with "wait for video will be ready to play"
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        triggerButton.click();
        driver.switchTo().defaultContent();
    }

    public void waitForVideoPlayerVisible() {
        pageObject.wait.forElementVisible(UIElementsSelector);
    }

    public void waitForVideoPlayerHidden() {
        pageObject.wait.forElementNotVisible(UIElementsSelector);
    }
}
