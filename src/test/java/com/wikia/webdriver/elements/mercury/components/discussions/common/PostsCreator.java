package com.wikia.webdriver.elements.mercury.components.discussions.common;

public interface PostsCreator {

  boolean isSignInDialogVisible();

  PostsCreator click();

  PostsCreator closeGuidelinesMessage();

  CategoryPills clickAddCategoryButton();

  PostsCreator fillTitleWith(final String text);

  PostsCreator fillDescriptionWith(final String text);

  PostsCreator clickSubmitButton();

  PostsCreator waitForSpinnerToAppearAndDisappear();

  PostEntity.Data addPostWithRandomData();
}
