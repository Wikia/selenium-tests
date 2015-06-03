package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import java.awt.*;

/**
 * Created by wikia on 2015-06-03.
 */
public class ModalTests extends NewTestTemplate {

    Credentials credentials = config.getCredentials();

    @Test(groups = {"ArticleFeaturesCRUDUser_001", "ArticleFeaturesCRUDUser", "Smoke"})
    public void ArticleFeaturesCRUDUser_001_addModifyGallery() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.logInCookie(credentials.userName, credentials.password, wikiURL);
        ArticlePageObject article = base.openRandomArticle(wikiURL);
        VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
        //under this window height scrollbar should be visible
        //TODO: define proper number for this dimension.
        //TODO: evaluate creating more tests for different resolutions
        Dimension dimension = new Dimension(1000, 500);
        driver.manage().window().setSize(dimension);
        GalleryBuilderComponentObject galleryBuilder = visualEditMode.clickGalleryButton();

    }

}
