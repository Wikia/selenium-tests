package com.wikia.webdriver.testcases.mediatests.suggestions;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Content X-Wing
 */
public class VideoSuggestionsTests extends NewTestTemplate {

  @Test(
      groups = {"VideoSuggestions_001", "VideoSuggestions", "Media"}
  )
  @Execute(onWikia = "callofduty", asUser = User.USER)
  public void Vet_Tests_001_VerifyVideoSuggestionsIsDisplayed() {
    ArticlePageObject article = new ArticlePageObject(driver).open("Frank_Woods");
    VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
    ck.verifyContentLoaded();
    VetAddVideoComponentObject vetAddingVideo = ck.clickVideoButton();
    vetAddingVideo.verifySuggestionsIsDisplayed();
    WikiArticleEditMode edit = vetAddingVideo.clickCloseButton();
    edit.clickOnPublishButton();
  }
}
