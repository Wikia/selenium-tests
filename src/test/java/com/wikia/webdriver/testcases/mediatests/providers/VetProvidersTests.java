package com.wikia.webdriver.testcases.mediatests.providers;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VideoUrlProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.testng.annotations.Test;

@Test(groups = {"VetProvidersArticle", "ProviderTests", "Media"})
public class VetProvidersTests extends NewTestTemplate {

  @Execute(asUser = User.USER)
  @Test(dataProviderClass = VideoUrlProvider.class, dataProvider = "videoUrl")
  @RelatedIssue(issueID= "SUS-789", comment= "P2 for gamestar")
  public void VetProvidersTests_001_article(String videoUrl, String videoName) {
    new ArticleContent().clear();

    PageObjectLogging.log("", videoUrl, true);
    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(videoUrl);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
    article.verifyVideoName(videoName);
  }
}
