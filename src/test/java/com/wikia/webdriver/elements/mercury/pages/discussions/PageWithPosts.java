package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;


public interface PageWithPosts {

  Post getPost();

  SignInToFollowModalDialog getSignInToFollowModalDialog();

}
