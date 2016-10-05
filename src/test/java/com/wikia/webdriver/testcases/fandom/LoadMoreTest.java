package com.wikia.webdriver.testcases.fandom;

/**
 * Created by psko on 8/15/16.
 */

import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.components.LoadMore;
import com.wikia.webdriver.elements.fandom.pages.MainPage;
import org.testng.annotations.Test;

@Test(groups = {"Fandom", "Fandom_Load_More"})
public class LoadMoreTest extends FandomTestTemplate {

    @Test
    public void loadMoreButtonFunctionsCorrectly() {
        MainPage mainPage = new MainPage().open();
        LoadMore loadMore = new LoadMore().loadMoreBtn();
        LoadMore topStories = new LoadMore().moreTopStories();
    }
}
