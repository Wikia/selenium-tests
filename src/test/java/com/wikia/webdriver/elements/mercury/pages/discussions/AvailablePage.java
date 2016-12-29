package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;

/**
 * Page that is available for everyone, even not logged in users.
 */
public interface AvailablePage extends PageWithPosts {

  SignInToFollowModalDialog getSignInToFollowModalDialog();
}
