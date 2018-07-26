package com.wikia.webdriver.elements.mobile.components.discussions.common;

import lombok.Builder;

@Builder
@lombok.Data
public class ReplyEntityData {

  private final String id;
  private final String body;
  private final String authorId;
  private final String threadId;
}
