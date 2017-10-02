package com.wikia.webdriver.elements.mercury.components.discussions.common;

import lombok.Getter;

public enum SortOption {

  LATEST("latest"), TRENDING("trending");

  @Getter
  private String query;

  SortOption(String option) {
    query = option;
  }
}
