package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.xpath.operations.String;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by psko on 8/1/16.
 */
public class SearchInput extends BasePageObject {

    @FindBy(css = ".search-field")
    private WebElement searchFieldInput;

    public SearchInput searchInputValue() {
        searchFieldInput.sendKeys("TEST");

        return new SearchInput();
    }

    public SearchInput searchInputSubmit() {
        searchFieldInput.submit();

        return new SearchInput();

    }

}
