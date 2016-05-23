package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HeaderTest extends NewTestTemplate {

  @Test(groups = "mercury_header_checkElementsVisibilityWithoutInfobox")
  public void mercury_header_checkElementsVisibilityWithoutInfobox() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.NO_INFOBOX)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(), "header is visible");
    Assertion.assertTrue(header.isPageTitleVisible(), "page title is visible");
    Assertion.assertFalse(header.isHeroImageVisible(), "hero image is visible");
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(), "header is visible");
    Assertion.assertTrue(header.isPageTitleVisible(), "page title is visible");
    Assertion.assertTrue(header.isHeroImageVisible(), "hero image is visible");
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_2)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(), "header is visible");
    Assertion.assertTrue(header.isPageTitleVisible(), "page title is visible");
    Assertion.assertFalse(header.isHeroImageVisible(), "hero image is visible");
  }

  @Test(groups = "mercury_header_heroImageIsProperlyStyled")
  public void mercury_header_heroImageIsProperlyStyled() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertEquals(
        header.getHeroImageCssValue("background-color"),
        "rgba(255, 255, 255, 1)",
        "hero image has proper background-color attribute"
    );
    Assertion.assertEquals(
        header.getHeroImageCssValue("background-position"),
        "50% 50%",
        "hero image has proper background-position attribute"
    );
    Assertion.assertEquals(
        header.getHeroImageCssValue("background-repeat"),
        "no-repeat",
        "hero image has proper background-repeat attribute"
    );
  }

  @Test(groups = "mercury_header_heroImageIsSquare")
  public void mercury_header_heroImageIsSquare() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(header.isHeroImageSquare(), "hero image is square");
  }

  @Test(groups = "mercury_header_heroImageIsRectangle")
  public void mercury_header_heroImageIsRectangle() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_4)
            .getHeader();

    Assertion.assertFalse(header.isHeroImageSquare(), "hero image is rectangular (not square)");
  }
}
