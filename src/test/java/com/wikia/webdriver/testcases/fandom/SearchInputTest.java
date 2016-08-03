package com.wikia.webdriver.testcases.fandom;

import com.gargoylesoftware.htmlunit.javascript.host.URL;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.components.SearchInput;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by psko on 8/1/16.
 */

@Test(groups = {"Fandom", "Fandom_Search"})
public class SearchInputTest extends FandomTestTemplate {

    @Test
    public void searchInputAcceptsValue() {
        SearchInput searchInput = new SearchInput().searchInputValue();
        SearchInput searchInputSubmit = new SearchInput().searchInputSubmit();

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://qa.fandom.wikia.com/?s=TEST");

        SearchInput firstPostTitle = new SearchInput().getPostTitle();
        SearchInput firstInstance = new SearchInput().firstPostInstance();
        SearchInput postTitleFinal = new SearchInput().getPostTitleFinal();

        Assert.assertEquals(firstPostTitle, postTitleFinal);

    }

    @Test
    public void differentUrlForPostsAndVideo() {
        SearchInput searchInput = new SearchInput().searchInputValue();
        SearchInput searchInputSubmit = new SearchInput().searchInputSubmit();
        SearchInput postsResult = new SearchInput().searchResultsPosts();

        String postUrl = driver.getCurrentUrl();

        SearchInput videosResult = new SearchInput().searchResultsVideos();

        String videoUrl = driver.getCurrentUrl();

        Assert.assertNotEquals(postUrl, videoUrl);
    }
}

