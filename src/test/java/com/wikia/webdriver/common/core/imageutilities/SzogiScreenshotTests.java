package com.wikia.webdriver.common.core.imageutilities;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.testng.annotations.Test;


    public class SzogiScreenshotTests extends NewTestTemplate {

        private User testUser = User.WIKIACTIVITY_USER;

        @Test(groups = "SzogiScreenshotTests")
        public void webElementInViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleName();
        }

        @Test(groups = "SzogiScreenshotTests")
        public void webElementBelowViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfSubmitCommentButton();
        }

        @Test(groups = "SzogiScreenshotTests")
        public void webElementOverViewport() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleNameAfterScroll();
        }

        @InBrowser(browser = Browser.FIREFOX)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementInViewportInFirefox() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleName();
        }

        @InBrowser(browser = Browser.FIREFOX)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementBelowViewportInFirefox() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfSubmitCommentButton();
        }

        @InBrowser(browser = Browser.FIREFOX)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementOverViewportInFirefox() {
            ArticlePageObject article = createArticle();
            article.getScreenshotOfArticleNameAfterScroll();
        }

        private ArticlePageObject createArticle() {
            new ArticleContent(testUser).push("content");
            return new ArticlePageObject().open();
        }

        @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
        @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementInViewportInMercury() {
            ArticlePage article = new ArticlePage().open("/ScrollPreserveTest");
            article.getScreenshotOfArticleName();
        }

        @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
        @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementBelowViewportInMercury() {
            ArticlePage article = new ArticlePage().open("/ScrollPreserveTest");
            article.getScreenshotOfCategoriesDropdown();
        }

        @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
        @Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
        @Test(groups = "SzogiScreenshotTests")
        public void webElementOverViewportInMercury() {
            ArticlePage article = new ArticlePage().open("/ScrollPreserveTest");
            article.getScreenshotOfArticleNameAfterScroll();
        }
    }



