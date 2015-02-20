package com.wikia.webdriver.testcases.mediatests.providers;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.dataprovider.VideoUrlProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetProvidersTests extends NewTestTemplate {

  @Test(dataProviderClass = VideoUrlProvider.class, dataProvider = "videoUrl", groups = {
      "VetProvidersArticle", "VetProvidersTests_001", "Media"})
  @ExecuteAs(user = User.USER)
  public void VetProvidersTests_001_article_MAIN_3413(String videoUrl, String videoName) {
    PageObjectLogging.log("", videoUrl, true);
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.clearContent();
    VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(videoUrl);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    visualEditMode.submitArticle();
    article.verifyVideo();
    article.verifyVideoName(videoName);
  }
}
