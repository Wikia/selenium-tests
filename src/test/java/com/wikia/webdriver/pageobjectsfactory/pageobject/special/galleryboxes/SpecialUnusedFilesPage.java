package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import lombok.Getter;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public final class SpecialUnusedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_UNUSED_FILES_PATH = "Special:UnusedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUnusedFilesPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNUSED_FILES_PATH));
    PageObjectLogging.log("Special Unused Files Page", SPECIAL_UNUSED_FILES_PATH + " opened", true);

    return this;
  }
}
