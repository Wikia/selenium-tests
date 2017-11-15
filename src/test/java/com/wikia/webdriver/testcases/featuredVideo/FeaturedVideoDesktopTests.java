package com.wikia.webdriver.testcases.featuredVideo;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo.FeaturedVideoComponentObject;

import org.testng.annotations.Test;

@Test(groups = {"FeaturedVideoDesktop"})
@Execute(onWikia = "featuredvideo", asUser = User.ANONYMOUS)
public class FeaturedVideoDesktopTests extends NewTestTemplate {

  @Test
  public void videoIsPresentOnArticle() {
    FeaturedVideoComponentObject video = new FeaturedVideoComponentObject()
        .setAutoplayCookie(false)
        .openWikiArticle("FeaturedVideo");

    Assertion.assertTrue(video.isFeaturedVideo());

  }


}
