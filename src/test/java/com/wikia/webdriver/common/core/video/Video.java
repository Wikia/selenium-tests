package com.wikia.webdriver.common.core.video;

/**
 * Created by Ludwik Kaźmierczak on 2015-02-12.
 * 
 * Interface used for videos object.
 */

public interface Video {

  /**
   * When adding a video to WIKIA, backend is replacing some special characters, make sure you
   * return a title in the same form that is presented on front-end
   * 
   * @return
   */
  public String getTitle();

  /**
   * Simply a URL to video
   * 
   * @return
   */
  public String getUrl();

  /**
   * Get a video title in a WIKIA file link format. e. g. for a video titled 'WikiEvolution -
   * Poznańska Wiki-1424144130' a proper filename should be,
   * 'WikiEvolution_-_Poznańska_Wiki-1424144130', so a url to video on WIKIA should look like:
   * (wikiName).wikia.com/wiki/File:WikiEvolution_-_Poznańska_Wiki-1424144130
   * 
   * @return
   */
  public String getWikiFileName();
}
