package com.wikia.webdriver.common.templates.fandom;

public class AdsFandomTestTemplate extends FandomTestTemplate {

  @Override
  protected void loadFirstPage() {
    // we want to avoid going to qa.fandom.com as logged in user
  }
}
