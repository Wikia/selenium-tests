package com.wikia.webdriver.testcases.mediatests.providers;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.dataprovider.VideoUrlProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetProvidersTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @RelatedIssue(issueID = "MAIN-3413")
  @Test(dataProviderClass = VideoUrlProvider.class, dataProvider = "videoUrl", groups = {
      "VetProvidersArticle", "VetProvidersTests_001", "Media"})
  public void VetProvidersTests_001_article(String videoUrl, String videoName) {
    PageObjectLogging.log("", videoUrl, true);
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
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
