package com.wikia.webdriver.testcases.mobilewikitests;

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

import java.util.HashMap;
import java.util.Map;
@Test(groups = "Mercury_Header")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HeaderTest extends NewTestTemplate {

  private static final String HEADER_MESSAGE = "Header";
  private static final String PAGE_TITLE_MESSAGE = "Page title";
  private static final String HERO_IMAGE_MESSAGE = "Hero image";

  private static final String VISIBLE_MESSAGE = "visible";
  private static final String INVISIBLE_MESSAGE = "invisible";

  private static final String ELEMENT_EXPECTATION_MESSAGE_TEMPLATE = "%s was expected to be %s.";
  private static final String INVALID_ATTRIBUTE_MESSAGE_TEMPLATE = "%s has invalid %s attribute.";


  @Test(groups = "mercury_header_checkElementsVisibilityWithoutInfobox")
  public void mercury_header_checkElementsVisibilityWithoutInfobox() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.NO_INFOBOX)
            .getHeader();

    Assertion.assertTrue(
        header.isHeaderVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HEADER_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertTrue(
        header.isPageTitleVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, PAGE_TITLE_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertFalse(
        header.isHeroImageVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, INVISIBLE_MESSAGE)
    );
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(
        header.isHeaderVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HEADER_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertTrue(
        header.isPageTitleVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, PAGE_TITLE_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertTrue(
        header.isHeroImageVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, VISIBLE_MESSAGE)
    );
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_2)
            .getHeader();

    Assertion.assertTrue(
        header.isHeaderVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HEADER_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertTrue(
        header.isPageTitleVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, PAGE_TITLE_MESSAGE, VISIBLE_MESSAGE)
    );
    Assertion.assertFalse(
        header.isHeroImageVisible(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, INVISIBLE_MESSAGE)
    );
  }

  @Test(groups = "mercury_header_heroImageIsProperlyStyled")
  public void mercury_header_heroImageIsProperlyStyled() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Map<String, String> attributeExpectedValues = new HashMap<String, String>() {{
      put("background-color", "rgba(255, 255, 255, 1)");
      put("background-position", "50% 50%");
      put("background-repeat", "no-repeat");
    }};

    for (Map.Entry<String, String> attribute : attributeExpectedValues.entrySet()) {
      String attributeName = attribute.getKey();
      Assertion.assertEquals(
          header.getHeroImageCssValue(attributeName),
          attribute.getValue(),
          String.format(INVALID_ATTRIBUTE_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, attributeName)
      );
    }
  }

  @Test(groups = "mercury_header_heroImageIsSquare")
  public void mercury_header_heroImageIsSquare() {
    Header header =
        new ArticlePage()
            .open(MercurySubpages.INFOBOX_1)
            .getHeader();

    Assertion.assertTrue(
        header.isHeroImageSquare(),
        String.format(ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, "square")
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
        String.format(
            ELEMENT_EXPECTATION_MESSAGE_TEMPLATE, HERO_IMAGE_MESSAGE, "rectangular (not square)"
        )
    );
  }
}
