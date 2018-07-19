package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public final class SpecialUnusedVideosPage extends WikiBasePageObject {

  private static final String SPECIAL_UNUSED_VIDEOS_PATH = "Special:UnusedVideos";

  @Getter(lazy = true)
  private final GalleryGrid galleryGrid = new GalleryGrid();

  public SpecialUnusedVideosPage open() {
    getUrl(urlBuilder.getUrlForWikiPage(SPECIAL_UNUSED_VIDEOS_PATH));
    Log.log("Special Unused Videos Page", SPECIAL_UNUSED_VIDEOS_PATH + " opened", true);

    return this;
  }
}
