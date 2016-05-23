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

  private static final String HEADER_MSG = "Header";
  private static final String PAGE_TITLE_MSG = "Page title";
  private static final String HERO_IMAGE_MSG = "Hero image";

  private static final String VISIBLE_MSG = "visible";
  private static final String INVISIBLE_MSG = "invisible";

  private static final String ELEMENT_MSG_TEMPLATE = "%s was expected to be %s.";
  private static final String INVALID_ATTRIBUTE_MSG_TEMPLATE = "%s has invalid %s attribute.";


  @Test(groups = "mercury_header_checkElementsVisibilityWithoutInfobox")
  public void mercury_header_checkElementsVisibilityWithoutInfobox() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.NO_INFOBOX)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, HEADER_MSG, VISIBLE_MSG));
    Assertion.assertTrue(header.isPageTitleVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, PAGE_TITLE_MSG, VISIBLE_MSG));
    Assertion.assertFalse(header.isHeroImageVisible(),
                          String.format(ELEMENT_MSG_TEMPLATE, HERO_IMAGE_MSG, INVISIBLE_MSG));
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, HEADER_MSG, VISIBLE_MSG));
    Assertion.assertTrue(header.isPageTitleVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, PAGE_TITLE_MSG, VISIBLE_MSG));
    Assertion.assertTrue(header.isHeroImageVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, HERO_IMAGE_MSG, VISIBLE_MSG));
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_2)
            .getHeader();

    Assertion.assertTrue(header.isHeaderVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, HEADER_MSG, VISIBLE_MSG));
    Assertion.assertTrue(header.isPageTitleVisible(),
                         String.format(ELEMENT_MSG_TEMPLATE, PAGE_TITLE_MSG, VISIBLE_MSG));
    Assertion.assertFalse(header.isHeroImageVisible(),
                          String.format(ELEMENT_MSG_TEMPLATE, HERO_IMAGE_MSG, INVISIBLE_MSG));
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
        String.format(INVALID_ATTRIBUTE_MSG_TEMPLATE, HERO_IMAGE_MSG, "background-color")
    );
    Assertion.assertEquals(
        header.getHeroImageCssValue("background-position"),
        "50% 50%",
        String.format(INVALID_ATTRIBUTE_MSG_TEMPLATE, HERO_IMAGE_MSG, "background-position")
    );
    Assertion.assertEquals(
        header.getHeroImageCssValue("background-repeat"),
        "no-repeat",
        String.format(INVALID_ATTRIBUTE_MSG_TEMPLATE, HERO_IMAGE_MSG, "background-repeat")
    );
  }

  @Test(groups = "mercury_header_heroImageIsSquare")
  public void mercury_header_heroImageIsSquare() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(
        header.isHeroImageSquare(),
        String.format(ELEMENT_MSG_TEMPLATE, HERO_IMAGE_MSG, "square")
    );
  }

  @Test(groups = "mercury_header_heroImageIsRectangle")
  public void mercury_header_heroImageIsRectangle() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_4)
            .getHeader();

    Assertion.assertFalse(
        header.isHeroImageSquare(),
        String.format(ELEMENT_MSG_TEMPLATE, HERO_IMAGE_MSG, "rectangular (not square)")
    );
  }
}
