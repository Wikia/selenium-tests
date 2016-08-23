package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import lombok.Getter;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public final class SpecialUncategorizedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_UNCATEGORIZED_FILES_PATH = "Special:UncategorizedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUncategorizedFilesPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNCATEGORIZED_FILES_PATH));
    PageObjectLogging.log("Special Uncategorized Files Page",
        SPECIAL_UNCATEGORIZED_FILES_PATH + " opened", true);

    return this;
  }
}
