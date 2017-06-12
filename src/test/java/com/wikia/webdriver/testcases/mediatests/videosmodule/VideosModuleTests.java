package com.wikia.webdriver.testcases.mediatests.videosmodule;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.videosmodule.VideosModuleComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;
import org.testng.annotations.Test;

@Execute(onWikia = "de.vidauto")
public class VideosModuleTests extends NewTestTemplate {

  @Test(groups = {"VideosModule", "VideosModuleTest_001", "Media"})
  public void visitorCanSeeVideosModuleOnArticleAndFilePages() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);
    new ArticlePageObject().open();
    videosModule.verifyVideosModuleShowing();

    new FilePage().open(VideoContent.YOUTUBE_VIDEO_URL2_FILENAME);
    videosModule.verifyVideosModuleShowing();
  }

  /**
   * Checks if the Videos Module does not show up where it shouldn't. This checks the main page and
   * Special:WikiActivity, however the Videos Module shouldn't show up anywhere besides Article or
   * File pages. This is just a smoke test to make sure nothing is seriously wrong.
   */
  @Test(groups = {"VideosModule", "VideosModuleTest_002", "Media"})
  public void VideosModuleTest_002() {
    VideosModuleComponentObject videosModule = new VideosModuleComponentObject(driver);

    new MainPage(driver).open();
    videosModule.verifyVideosModuleNotShowing();

    new SpecialWikiActivityPageObject(driver).open();
    videosModule.verifyVideosModuleNotShowing();
  }

  /**
   * Checks if the Videos Module is showing the correct number of videos. Currently that amount is
   * between 3 and 5.
   */
  @Test(groups = {"VideosModule", "VideosModuleTest_003", "Media"})
  public void VideosModuleTest_003() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    new ArticlePageObject().open();
    new VideosModuleComponentObject(driver).verifyDisplayCount();
  }

  /**
   * Checks if the Videos Module is not showing any duplicate videos
   */
  @Test(groups = {"VideosModule", "VideosModuleTest_004", "Media"})
  public void VideosModuleTest_004() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    new ArticlePageObject().open();
    new VideosModuleComponentObject(driver).verifyNoDuplicates();
  }
}
