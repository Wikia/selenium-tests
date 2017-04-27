package com.wikia.webdriver.elements.mercury.components.discussions.common;

import lombok.Builder;

public class ReplyEntity {

  @Builder
  @lombok.Data
  public static class Data {
    private final String id;
    private final String title;
    private final String authorId;
    private final String threadId;
  }
}
