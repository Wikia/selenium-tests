package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import lombok.Getter;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public final class SpecialUnusedVideosPage extends WikiBasePageObject {

  private static final String SPECIAL_UNUSED_VIDEOS_PATH = "Special:UnusedVideos";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUnusedVideosPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNUSED_VIDEOS_PATH));
    PageObjectLogging.log("Special Unused Videos Page", SPECIAL_UNUSED_VIDEOS_PATH + " opened",
        true);

    return this;
  }
}
