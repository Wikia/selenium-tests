package com.wikia.webdriver.common.remote.context;

import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CategoryContext extends RemoteContext {

  private final String categoryId;

  private final String categoryName;

  @Builder
  public CategoryContext(String siteId, String categoryId, String categoryName) {
    super(siteId);
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public static CategoryContext defaultContextUsing(final String siteId, final CategoryPill.Data data) {
    Objects.requireNonNull(data.getId());

    return CategoryContext.builder()
        .siteId(siteId)
        .categoryId(data.getId())
        .categoryName(data.getName())
        .build();
  }
}
