package com.wikia.webdriver.testcases.mediatests.modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject.PositionsVideo;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

@Test(groups = {"VetTests", "Media", "VetModalAlignment"})
public class VetModalAlignmentTests extends NewTestTemplate {

  /**
   * 1. Verify left alignment in visual mode, source mode, preview, and published page, 2. Verify
   * left alignment in vet modal when page edited, 3. Verify right alignment in visual mode, source
   * mode, preview, and published page, 4. Verify right alignment in vet modal when page edited, 5.
   * Verify center alignment in visual mode, source mode, preview, and published page, 6. Verify
   * center alignment in vet modal when page edited,
   */

  @Test(groups = {"VetModalAlignment_001"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_001_leftOnPage() {
    new ArticleContent().push("");

    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().open().navigateToArticleEditPage();
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();

    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.adjustPosition(PositionsVideo.LEFT);
    vetOptions.submit();
    visualEditMode.verifyVideoPosition(PositionsVideo.LEFT);
    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    sourceEditMode.verifyVideoAlignment(PositionsVideo.LEFT);
    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    previewMode.verifyVideoAlignment(PositionsVideo.LEFT);
    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    article.verifyVideoAlignment(PositionsVideo.LEFT);
  }

  @Test(groups = {"VetModalAlignment_002"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_002_leftInModal() {
    new ArticleContent()
        .push("[[File:Batman_-_Following|thumb|left|335 px]]");

    VisualEditModePageObject visualEditModePageObject =
        new ArticlePageObject().open().navigateToArticleEditPage();
    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
    vetOptions.verifyVideoAlignmentSelected(PositionsVideo.LEFT);
  }

  @Test(groups = {"VetModalAlignment_003"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_003_centerOnPage() {
    new ArticleContent().push();

    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().open().navigateToArticleEditPage();
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.adjustPosition(PositionsVideo.CENTER);
    vetOptions.submit();
    visualEditMode.verifyVideoPosition(PositionsVideo.CENTER);
    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    sourceEditMode.verifyVideoAlignment(PositionsVideo.CENTER);
    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    previewMode.verifyVideoAlignment(PositionsVideo.CENTER);
    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    article.verifyVideoAlignment(PositionsVideo.CENTER);
  }

  @Test(groups = {"VetModalAlignment_004"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_004_centerInModal() {
    new ArticleContent()
        .push("[[File:Batman_-_Following|thumb|center|335 px]]");

    VisualEditModePageObject visualEditModePageObject =
        new ArticlePageObject().open().navigateToArticleEditPage();
    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
    vetOptions.verifyVideoAlignmentSelected(PositionsVideo.CENTER);
  }

  @Test(groups = {"VetModalAlignment_005"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_005_rightOnPage() {
    new ArticleContent().push();

    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().open().navigateToArticleEditPage();
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.adjustPosition(PositionsVideo.RIGHT);
    vetOptions.submit();
    visualEditMode.verifyVideoPosition(PositionsVideo.RIGHT);
    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    sourceEditMode.verifyVideoAlignment(PositionsVideo.RIGHT);
    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    previewMode.verifyVideoAlignment(PositionsVideo.RIGHT);
    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    article.verifyVideoAlignment(PositionsVideo.RIGHT);
  }

  @Test(groups = {"VetModalAlignment_006"})
  @Execute(asUser = User.USER_VET_MODAL)
  public void Vet_Modal_006_rightInModal() {
    new ArticleContent()
        .push("[[File:Batman_-_Following|thumb|right|335 px]]");

    VisualEditModePageObject visualEditModePageObject =
        new ArticlePageObject().open().navigateToArticleEditPage();

    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
    vetOptions.verifyVideoAlignmentSelected(PositionsVideo.RIGHT);
  }
}
