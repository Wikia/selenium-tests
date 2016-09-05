package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CategoryPills;

public interface PostsCreator {

  boolean isSignInDialogVisible();

  PostsCreator click();

  PostsCreator closeGuidelinesMessage();

  CategoryPills clickAddCategoryButton();

  PostsCreator fillDescriptionWith(final String text);

  PostsCreator clickSubmitButton();

  PostsCreator waitForSpinnerToAppearAndDisappear();
}
