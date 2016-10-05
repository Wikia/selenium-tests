package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.jetty.html.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

/**
 * Created by psko on 8/15/16.
 */
public class LoadMore extends BasePageObject {


    @FindBy(css = ".load-more-btn")
    private WebElement endlessArticlesFeed;


    @FindBy( css = ".post.loaded")
    private WebElement listMoreTopStories;


    public LoadMore loadMoreBtn() {
        endlessArticlesFeed.click();
        return this;
    }

    public LoadMore moreTopStories() {
        listMoreTopStories.click();
        return this;
    }

}
