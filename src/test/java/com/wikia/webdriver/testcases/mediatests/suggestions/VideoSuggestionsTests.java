package com.wikia.webdriver.testcases.mediatests.suggestions;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

@Test(groups = {"VideoSuggestions", "VetTests", "Media"})
public class VideoSuggestionsTests extends NewTestTemplate {

  @Test(groups = {"VideoSuggestions_001"})
  @Execute(onWikia = "callofduty", asUser = User.USER)
  public void Vet_Tests_001_VerifyVideoSuggestionsIsDisplayed() {
    ArticlePageObject article = new ArticlePageObject().open("Frank_Woods");
    VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");

    VetAddVideoComponentObject vetAddingVideo = ck.clickVideoButton();
    Assertion.assertTrue(vetAddingVideo.areSuggestionsDisplayed(),
        "Video suggestions are not displayed");
  }
}
