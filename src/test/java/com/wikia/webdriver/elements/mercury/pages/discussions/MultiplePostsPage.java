package com.wikia.webdriver.elements.mercury.pages.discussions;


import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import java.util.Optional;

public abstract class MultiplePostsPage extends WikiBasePageObject implements PageWithPosts {

  public Optional<PostEntity> getPostById(String postId) {
    return Optional.ofNullable(
      getPost()
        .getPosts()
        .stream()
        .peek(postEntity -> scrollTo(postEntity.getWebElement()))
        .filter(postEntity -> postEntity.findId().equals(postId))
        .findFirst()
        .orElseThrow(() ->
          new RuntimeException(String.format("Post with id [%s] not found on page", postId))));
  }
}
