package com.wikia.webdriver.testcases.fandom;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.helpers.FandomUser;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.AdminLoginPage;
import com.wikia.webdriver.elements.fandom.pages.NewPostPage;

@Test(groups = {"Fandom", "Fandom_WPAdmin"})
public class WPAdminPageTest extends FandomTestTemplate {

  @Test
  public void cannotPublishPostWithoutFeatureImage() {
    new AdminLoginPage().open().getLoginBox().login(FandomUser.EDITOR);

    NewPostPage postPage = new NewPostPage().open();

    postPage.typeTitle("This is My new Title");

    postPage
        .getTextEditor()
        .type("This is my new message");

    postPage.publish();

    Assertion.assertTrue(postPage.getNotifications().isNotificationVisible(
        "A featured image is required for this post type. This post is saved as a draft."));
  }
}
