package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public final class SpecialUnusedFilesPage extends WikiBasePageObject {

  private static final String SPECIAL_UNUSED_FILES_PATH = "Special:UnusedFiles";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUnusedFilesPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(SPECIAL_UNUSED_FILES_PATH));
    Log.log("Special Unused Files Page", SPECIAL_UNUSED_FILES_PATH + " opened", true);

    return this;
  }
}
