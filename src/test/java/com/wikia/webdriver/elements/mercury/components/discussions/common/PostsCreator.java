package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CategoryPills;

public interface PostsCreator {

  boolean isSignInDialogVisible();

  boolean isPostButtonActive();

  PostsCreator click();

  PostsCreator closeGuidelinesMessage();

  CategoryPills clickAddCategoryButton();

  PostsCreator fillTitleWith(final String text);

  PostsCreator clearTitle();

  PostsCreator fillDescriptionWith(final String text);

  PostsCreator clearDescription();

  PostsCreator clickSubmitButton();
}
