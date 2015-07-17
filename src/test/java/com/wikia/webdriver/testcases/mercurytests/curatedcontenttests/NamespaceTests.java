package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedContentPageObject;

import org.testng.annotations.Test;

/**
 * Created by wikia on 2015-07-17.
 */
public class NamespaceTests extends NewTestTemplate {

  private static final String ROOT_ARTICLE_PATH = "/wiki/";
  private static final String ROOT_BLOG_PATH = "/wiki/User_blog:";
  private static final String ROOT_FILE_PATH = "/wiki/File:";

  // CCT06
  @Test(groups = {"MercuryCuratedNamespaceTests_001", "MercuryCuratedNamespaceTests",
                  "MercuryCuratedContentTests", "Mercury"})
  public void MercuryCuratedNavigationTests_001_navigateThroughNamespaces() {
    CuratedContentPageObject category = new CuratedContentPageObject(driver);
    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_ARTICLES)
        .isArticleIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinishReloadingPage();
    UrlChecker.isPathContainedInCurrentUrl(driver, ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isBlogIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinishReloadingPage();
    UrlChecker.isPathContainedInCurrentUrl(driver, ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isImageIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinishReloadingPage();
    UrlChecker.isPathContainedInCurrentUrl(driver, ROOT_ARTICLE_PATH);

    category
        .openCuratedContentPage(wikiURL, MercurySubpages.CC_CATEGORY_BLOGS)
        .isVideoIconVisible()
        .clickOnCuratedContentElementByIndex(0)
        .waitForLoadingSpinnerToFinishReloadingPage();
    UrlChecker.isPathContainedInCurrentUrl(driver, ROOT_ARTICLE_PATH);
  }

}
