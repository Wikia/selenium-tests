package com.wikia.webdriver.common.remote.operations.json;

import com.jayway.jsonpath.DocumentContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntity;

public class JsonToReplyPostEntityMapper {

  private final DocumentContext json;

  public JsonToReplyPostEntityMapper(DocumentContext json) {
    this.json = json;
  }

  public ReplyEntity.Data toData() {
    return ReplyEntity.Data.builder()
      .id(json.read("$.id"))
      .threadId(json.read("$.threadId"))
      .body(json.read("$.body"))
      .authorId(json.read("$.creatorId"))
      .build();
  }

}
