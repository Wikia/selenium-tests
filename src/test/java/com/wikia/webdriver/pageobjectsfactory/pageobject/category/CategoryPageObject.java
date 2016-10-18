package com.wikia.webdriver.pageobjectsfactory.pageobject.category;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPageObject extends WikiBasePageObject {

    @FindBy(css = "#WikiaPageHeader h1")
    private WebElement categoryHeader;
    @FindBy(css = ".category-gallery-item img")
    private List<WebElement> categoryGalleryItemImages;
    @FindBy(css = ".category-gallery-item .title")
    private List<WebElement> categoryGalleryItemsName;

    public CategoryPageObject(WebDriver driver) {
        super();
    }

    public String getCategoryPageTitle() {
        wait.forElementVisible(categoryHeader);
        String title = categoryHeader.getText();
        PageObjectLogging.log(
            "getCategoryname",
            "the name of the category is: " + title,
            true
        );

        return title;
    }

    public int getArticleIndexInGalleryByName(String articleName) {
        int index = -1;
        for(int i=0; i<categoryGalleryItemsName.size(); i++) {
            if(articleName.equals(categoryGalleryItemsName.get(i).getText())) {
                index = i;
            }
        }
        return index;
    }

    public String getPageImageURL(int index) {
        wait.forElementVisible(categoryGalleryItemImages.get(index));
        return categoryGalleryItemImages.get(index).getAttribute("src");
    }
}
