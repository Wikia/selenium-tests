package com.wikia.webdriver.pageobjectsfactory.pageobject.category;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by nikodem on 27.07.15.
 */
public class CategoryPageObject extends WikiBasePageObject {

    @FindBy(css = "#WikiaPageHeader h1")
    protected WebElement categoryHeader;


    public CategoryPageObject(WebDriver driver) {
        super(driver);
    }

    public String getCategoryName() {
        String categoryName = categoryHeader.getText();
        PageObjectLogging.log("getCategoryname", "the name of the category is: " + categoryName, true);
        return categoryName;
    }

    public void verifyCategoryPageTitle(String title) {
        waitForElementVisibleByElement(categoryHeader);
        Assertion.assertEquals(categoryHeader.getText(), title);
    }

}
