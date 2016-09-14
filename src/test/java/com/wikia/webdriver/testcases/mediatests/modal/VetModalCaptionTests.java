package com.wikia.webdriver.testcases.mediatests.modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

@Test(groups = {"VetModalCaption", "VetTests", "Media"})
public class VetModalCaptionTests extends NewTestTemplate {

  /**
   * 1. Verify caption in visual mode, source mode, preview, and published page, 2. Verify caption
   * in vet modal when page edited, 3. Verify no caption in visual mode, source mode, preview, and
   * published page, 4. Verify no caption in vet modal when page edited, 5. Verify video name is not
   * editable for premium videos,
   */

  String articleTitleCaption;
  String articleTitleNotEditable;
  String caption;

  @Test(groups = {"VetModalCaption_001"})
  @Execute(asUser = User.USER)
  public void VetModalCaption_001_modalCaption() {
    WikiBasePageObject base = new WikiBasePageObject();
    articleTitleCaption = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditModePageObject visualEditMode =
        base.navigateToArticleEditPage(wikiURL, articleTitleCaption);
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    caption = PageContent.CAPTION + vetOptions.getTimeStamp();
    vetOptions.setCaption(caption);
    vetOptions.submit();
    Assertion.assertEquals(visualEditMode.getVideoCaption(), caption);

    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    Assertion.assertTrue(sourceEditMode.getContent().contains(caption));

    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    Assertion.assertEquals(previewMode.getVideoCaption(), caption);

    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    Assertion.assertEquals(article.getVideoCaption(), caption);
  }

  @Test(groups = {"VetModalCaption_002"}, dependsOnGroups = "VetModalCaption_001")
  @Execute(asUser = User.USER)
  public void VetModalCaption_002_captionInModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    VisualEditModePageObject visualEditModePageObject =
        base.navigateToArticleEditPage(wikiURL, articleTitleCaption);
    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
    vetOptions.verifyCaption(caption);
  }

  @Test(groups = {"VetModalCaption_005"})
  @Execute(asUser = User.USER)
  public void VetModalCaption_005_videoNameNotEditable() {
    WikiBasePageObject base = new WikiBasePageObject();
    articleTitleNotEditable = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject visualEditMode =
        base.navigateToArticleEditPage(wikiURL, articleTitleNotEditable);
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 1);
    vetOptions.verifyNameNotEditable();
  }
}
