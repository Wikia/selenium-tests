package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VUAP {
    private static By playTriggerButtonSelector = By.id("button");
    private static By UIElementsSelector = By.className("overVideoLayer");
    private final Wait wait;
    private WikiaWebDriver driver;
    private WebElement iframe;
    private WebElement playTriggerButton;

    public VUAP(WikiaWebDriver driver, String slotName) {
        this.wait = new Wait(driver);
        this.driver = driver;
        iframe = findIframe(slotName);
        playTriggerButton = findTriggerButton(driver);
    }

    private WebElement findIframe(String slotName) {
        By iframeSelector = By.id(getAdUnit(slotName));
        wait.forElementPresent(iframeSelector);
        return driver.findElement(iframeSelector);
    }

    private String getAdUnit(String slotName) {
        return "google_ads_iframe_/5441/wka.life/_project43//article/gpt/" + slotName + "_0";
    }

    private WebElement findTriggerButton(WikiaWebDriver driver) {
        driver.switchTo().frame(iframe);

        wait.forElementClickable(playTriggerButtonSelector);
        WebElement element = driver.findElement(playTriggerButtonSelector);
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

        playTriggerButton.click();
        driver.switchTo().defaultContent();
    }

    public void waitForVideoPlayerVisible() {
        wait.forElementVisible(UIElementsSelector);
    }

    public void waitForVideoPlayerHidden() {
        wait.forElementNotVisible(UIElementsSelector);
    }
}
