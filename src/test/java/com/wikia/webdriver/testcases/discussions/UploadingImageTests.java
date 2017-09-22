package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

@Execute(onWikia = "sandbox-s3.dman", asUser = User.USER_2)
public class UploadingImageTests extends NewTestTemplate {

  private static final String DESKTOP = "uploading-image-desktop";
  private static final String MOBILE = "uploading-image-mobile";

  @Test
  public void userCanUploadImageToTheirPost() {
    PostsListPage page = new PostsListPage();
    startPostCreation(page).uploadFile().clickSubmitButton();

    page.waitForPageReload();

    Assertion.assertTrue(page.getPost().findNewestPost().hasImage());
  }

  private PostsCreatorDesktop startPostCreation(PostsListPage page) {
    PostsCreatorDesktop postCreator = page
      .open()
      .getPostsCreatorDesktop();
    postCreator
      .click()
      .closeGuidelinesMessage()
      .addTitleWith(TextGenerator.defaultText())
      .addDescriptionWith(TextGenerator.defaultText())
      .clickAddCategoryButton()
      .findCategoryOn(0)
      .click();
    return postCreator;
  }

}
