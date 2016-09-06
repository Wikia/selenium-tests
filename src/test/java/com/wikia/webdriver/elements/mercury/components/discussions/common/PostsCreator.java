package com.wikia.webdriver.elements.mercury.components.discussions.common;

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

  PostEntity.Data addPostWithRandomData();
}
