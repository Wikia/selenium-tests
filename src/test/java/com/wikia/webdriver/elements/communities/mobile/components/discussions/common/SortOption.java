package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import lombok.Getter;

public enum SortOption {

  LATEST("latest"), TRENDING("trending");

  @Getter
  private String query;

  /**
   * @param option is a keyword that will appear in URL query param for sorting option
   */
  SortOption(String option) {
    query = option;
  }
}
