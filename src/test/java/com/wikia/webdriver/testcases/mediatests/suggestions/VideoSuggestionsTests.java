package com.wikia.webdriver.testcases.mediatests.suggestions;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.testng.annotations.Test;

public class VideoSuggestionsTests extends NewTestTemplate {

  @Test(groups = {"VideoSuggestions_001", "VideoSuggestions", "VetTests", "Media"})
  @Execute(onWikia = "callofduty", asUser = User.USER)
  public void Vet_Tests_001_VerifyVideoSuggestionsIsDisplayed() {
    ArticlePageObject article = new ArticlePageObject().open("Frank_Woods");
    VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
    ck.verifyContentLoaded();
    VetAddVideoComponentObject vetAddingVideo = ck.clickVideoButton();
    vetAddingVideo.verifySuggestionsIsDisplayed();
    WikiArticleEditMode edit = vetAddingVideo.clickCloseButton();
    edit.clickOnPublishButton();
  }
}

