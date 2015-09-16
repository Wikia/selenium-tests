package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class GoogleFormWidgetPageObject extends WidgetPageObject {

    @FindBy(css = "iframe[data-wikia-widget=\"googleform\"]")
    private WebElement googleFormIframe;
    @FindBy(css = "div.widget")
    private WebElement googleFormBody;

    private static final String TAG_NAME = "googleform";
    private static final String ARTICLE_NAME = "GoogleFormWidget";
    private static final String TAG = "<googleform url=\"https://docs.google.com/a/wikia-inc.com/forms/d/1cwWn51i5vXFBy7c5VkRzapj6FXxbjZy48VkEZyP33R4/viewform?embedded=true\" />";
    private static final String INCORRECT_TAG = "<googleform />";
    private static final String ERROR_MESSAGE = "Failed to render the Google Form widget. Please check if \"url\" param is properly coped from Embed dialog in Google.";

    public GoogleFormWidgetPageObject(WebDriver driver) {
        super(driver);
    }

    protected String getArticleName() {
        return ARTICLE_NAME;
    }

    protected String getTagName() {
        return TAG_NAME;
    }

    public String getTag() {
        return TAG;
    }

    protected String getIncorrectTag() {
        return INCORRECT_TAG;
    }

    protected String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    protected boolean isTagLoadedOnMercury() {
        if(!isElementVisible(googleFormIframe)) {
            return false;
        }

        driver.switchTo().frame(googleFormIframe);
        boolean result = isElementVisible(googleFormBody);
        driver.switchTo().parentFrame();

        return result;
    }

    protected boolean isTagLoadedOnOasis() {
        if(!isElementVisible(googleFormIframe)) {
            return false;
        }

        driver.switchTo().frame(googleFormIframe);
        boolean result = isElementVisible(googleFormBody);
        driver.switchTo().parentFrame();

        return result;
    }
}
