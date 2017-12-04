package com.wikia.webdriver.testcases.social;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

public class Sharing extends NewTestTemplate {

  @Test(groups = "Sharing")
  @Execute(asUser = User.SUS_REGULAR_USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanShareArticle() {
    new ArticleContent(User.SUS_STAFF).push("Article to test sharing");

    ArticlePageObject article = new ArticlePageObject().open();
    article.getCommunityHeader().clickShareButton();





//    LightboxComponentObject lightbox = article.clickThumbnailImage();
//    lightbox.clickPinButton();
//    lightbox.makeHeaderVisible(); // Assure header buttons are visible if not hovered over
//    lightbox.clickShareButton();
//    lightbox.verifyShareButtons();
//    lightbox.clickFacebookShareButton();
//    lightbox.verifyUrlInNewWindow(URLsContent.FACEBOOK_DOMAIN);
//    lightbox.clickTwitterShareButton();
//    lightbox.verifyUrlInNewWindow(URLsContent.TWITTER_DOMAIN);
//    lightbox.clickStumbleUponShareButton();
//    lightbox.verifyUrlInNewWindow(URLsContent.STUMPLEUPON_DOMAIN);
//    lightbox.clickRedditShareButton();
//    lightbox.verifyUrlInNewWindow(URLsContent.REDDIT_DOMAIN);
//    lightbox.clickPlusOneShareButton();
//    lightbox.verifyUrlInNewWindow(URLsContent.GOOGLE_DOMAIN);
//    lightbox.clickCloseShareScreenButton();
//    lightbox.verifyShareScreenClosed();
//    lightbox.clickCloseButton();
//    lightbox.verifyLightboxClosed();
  }
}
