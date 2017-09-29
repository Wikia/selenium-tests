package com.wikia.webdriver.testcases.discussions;


import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OpengraphTests extends NewTestTemplate {

  @Test
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpengraphPreviewInNewPostOnDesktop() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorDesktop();
    postsCreator.click().closeGuidelinesMessage();
    String url = "http://dman.wikia.com/d";
    postsCreator.addTitleWith(TextGenerator.defaultText()).addDescriptionWith(new URL(url));
    Assertion.assertTrue(postsCreator.hasOpenGraph());
    postsCreator.clickAddCategoryButton().selectFirstCategory();
    postsCreator.clickSubmitButton();
    page.waitForPageReload();
    page.getPost().waitForPostToAppearWith(url);
    Assertion.assertTrue(page.getPost().findNewestPost().hasOpenGraphAtContentEnd());
  }
}
