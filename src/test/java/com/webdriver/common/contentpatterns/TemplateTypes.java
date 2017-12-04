package com.webdriver.common.contentpatterns;

import org.apache.commons.lang.StringUtils;

public enum TemplateTypes {

  UNKNOWN, INFOBOX, QUOTE;

  private final String type;

  TemplateTypes() {
    this.type = StringUtils.capitalize(this.toString().toLowerCase());
  }

  public String getType() {
    return this.type;
  }
}
