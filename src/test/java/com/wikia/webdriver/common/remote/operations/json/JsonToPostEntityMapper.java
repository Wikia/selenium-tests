package com.wikia.webdriver.common.remote.operations.json;

import com.jayway.jsonpath.DocumentContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;

public class JsonToPostEntityMapper {

  private final DocumentContext json;

  public JsonToPostEntityMapper(DocumentContext json) {
    this.json = json;
  }

  public PostEntity.Data toData() {
    return PostEntity.Data.builder()
        .id(json.read("$.id"))
        .category(json.read("$.forumName"))
        .title(json.read("$.title"))
        .description(json.read("$.rawContent"))
        .authorId(json.read("$.createdBy.id"))
        .firstPostId(json.read("$.firstPostId"))
        .build();
  }
}
