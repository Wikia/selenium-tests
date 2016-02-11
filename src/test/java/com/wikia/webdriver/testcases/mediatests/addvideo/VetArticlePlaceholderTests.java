package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

import org.testng.annotations.Test;

public class VetArticlePlaceholderTests extends NewTestTemplate {

  @Test(groups = {"VideoArticlePlacehoder_001", "VideoArticlePlacehoder", "Media"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  public void userCanAddVideoByUrlUsingPlaceholder() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    ArticlePageObject article = new ArticlePageObject(driver).open();
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_002", "VideoArticlePlacehoder", "Media"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  public void userCanAddVideoFromWikiaUsingPlaceholder() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    ArticlePageObject article = new ArticlePageObject(driver).open();
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_003", "VideoArticlePlacehoder", "Media"})
  @Execute(asUser = User.STAFF, onWikia = "mobileregressiontesting")
  public void userCanAddVideoByUrlUsingPlaceholderInEditMode() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    //Added cause of MAIN-6374 issue
    new ArticlePageObject(driver).open();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    VetAddVideoComponentObject
        vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_004", "VideoArticlePlacehoder", "Media"})
  @Execute(asUser = User.STAFF, onWikia = "mobileregressiontesting")
  public void userCanAddVideoFromWikiaUsingPlaceholderInEditMode() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    //Added cause of MAIN-6374 issue
    new ArticlePageObject(driver).open();

    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject(driver).open();
    VetAddVideoComponentObject vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
  }
}
