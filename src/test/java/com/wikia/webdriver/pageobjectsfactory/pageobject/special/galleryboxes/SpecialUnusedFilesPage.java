package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public final class SpecialUnusedFilesPage extends GalleryBox {

  private static final String SPECIAL_UNUSED_FILES_PATH = "Special:UnusedFiles";

  public SpecialUnusedFilesPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNUSED_FILES_PATH));
    PageObjectLogging.log("Special Unused Files Page", SPECIAL_UNUSED_FILES_PATH + " opened", true);

    return this;
  }
}
