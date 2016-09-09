package com.wikia.webdriver.elements.oasis.components.templatecontent;

import com.wikia.webdriver.common.core.api.ArticleContent;

public class TemplateContent {

  private static final String namespace = "Template:";

  public TemplateContent push(String templateName) {
    new ArticleContent().push("", namespace + templateName);

    return this;
  }
}
