package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HeaderTest extends NewTestTemplate {

  private Header header;
  private Navigate navigate;

  private void init() {
    this.header = new Header(driver);
    this.navigate = new Navigate(driver);
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithoutInfobox")
  public void mercury_header_checkElementsVisibilityWithoutInfobox() {
    init();
    navigate.toPage(MercurySubpages.NO_INFOBOX);

    header
        .isPageTitleVisible()
        .isHeroImageNotVisible();
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithHeroImage() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

    header
        .isPageTitleVisible()
        .isHeroImageVisible();
  }

  @Test(groups = "mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage")
  public void mercury_header_checkElementsVisibilityWithInfoboxAndWithoutHeroImage() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_2);

    header
        .isPageTitleVisible()
        .isHeroImageNotVisible();
  }

  @Test(groups = "mercury_header_heroImageIsProperlyStyled")
  public void mercury_header_heroImageIsProperlyStyled() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

    header.isHeroImageProperlyStyled();
  }

  @Test(groups = "mercury_header_heroImageIsSquare")
  public void mercury_header_heroImageIsSquare() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_1);

    header.isHeroImageSquare();
  }

  @Test(groups = "mercury_header_heroImageIsRectangle")
  public void mercury_header_heroImageIsRectangle() {
    init();
    navigate.toPage(MercurySubpages.INFOBOX_4);

    header.isNotHeroImageSquare();
  }
}
