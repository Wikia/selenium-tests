package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.testng.annotations.Test;


    @Test(groups = "SzogiScreenshotTests")
    @Execute(onWikia = "wikiActivities", asUser = User.WIKIACTIVITY_USER)
    public class SzogiScreenshotTests extends NewTestTemplate {

        private User testUser = User.WIKIACTIVITY_USER;

        @Test
        public void webElementInViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleName();

        }

        @Test
        public void webElementBelowViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfSubmitCommentButton();

        }

        @Test
        public void webElementOverViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleNameAfterScroll();
        }

        //Firefox needs dpr=1 for mac?!?!?!?!?

//        @InBrowser(browser = Browser.FIREFOX)
//        @Test
//        public void webElementInViewportInFirefox() {
//            ArticlePageObject article = createArticle();
//            article.getScreenshotOfArticleName();
//
//        }
//
//        @InBrowser(browser = Browser.FIREFOX)
//        @Test
//        public void webElementBelowViewportInFirefox() {
//            ArticlePageObject article = createArticle();
//            article.getScreenshotOfSubmitCommentButton();
//
//        }
//
//        @InBrowser(browser = Browser.FIREFOX)
//        @Test
//        public void webElementOverViewportInFirefox() {
//            ArticlePageObject article = createArticle();
//            article.getScreenshotOfArticleNameAfterScroll();
//        }

        private ArticlePageObject createArticle() {
            new ArticleContent(testUser).push("content");
            return new ArticlePageObject().open();
        }
    }



