package com.wikia.webdriver.pageobjectsfactory.pageobject.template;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Rodriuki on 16/06/15.
 * Created by nikodamn on 20/07/15.
 */
public class TemplatePageObject extends WikiBasePageObject{

    @FindBy (css=".wikia-button #edit")
    private WebElement createButton;

    public TemplatePageObject(WebDriver driver) {
        super(driver);
    }

    public SourceEditModePageObject clickCreate() {
        waitForElementByElement(createButton);
        createButton.click();
        return new SourceEditModePageObject(driver);
    }

}
