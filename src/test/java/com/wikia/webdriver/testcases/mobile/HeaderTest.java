package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.Header;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import org.testng.annotations.Test;
@Test(groups = "Mercury_Header")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HeaderTest extends NewTestTemplate {

  private static final String HEADER_MESSAGE = "Header";
  private static final String PAGE_TITLE_MESSAGE = "Page title";
  private static final String HERO_IMAGE_MESSAGE = "Hero image";

  private static final String VISIBLE_MESSAGE = "visible";
  private static final String INVISIBLE_MESSAGE = "invisible";

  private static final String ELEMENT_EXPECTATION_MESSAGE_TEMPLATE = "%s was expected to be %s.";
  private static final String INFOBOX1_INVOCATION = ContentLoader.loadWikiTextContent(
          "Infobox1_Invocation");
  private static final String INFOBOX1_TEMPLATE = ContentLoader.loadWikiTextContent(
          "Infobox1_Template");

  @Test(groups = "mercury_header_checkElementsVisibilityWithoutInfobox")
  public void mercury_header_checkElementsVisibilityWithoutInfobox() {
    Header header =
        new ArticlePage()
            .open(MobileSubpages.NO_INFOBOX)
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
    new ArticleContent().push(INFOBOX1_TEMPLATE, "Template:InfoboxAutomation");
    new ArticleContent().push(INFOBOX1_INVOCATION, "Infobox1");

    Header header =
        new ArticlePage()
            .open(MobileSubpages.INFOBOX_1)
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

}
