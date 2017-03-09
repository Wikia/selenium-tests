package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.InteractiveMapsComponentObject;

import org.testng.annotations.Test;

@Test(groups = "Mercury_InteractiveMaps")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class InteractiveMapsTests extends NewTestTemplate {

  private InteractiveMapsComponentObject maps;

  private void init() {
    this.maps = new InteractiveMapsComponentObject();

    new Navigate().toPage(MercurySubpages.MAP);
  }

  @RelatedIssue(issueID = "SUS-1775", comment = "Test won't pass until product is fixed, please don't bother reruning")
  @Test(groups = "MercuryInteractiveMapsTest_001")
  @DontRun(env={"dev", "sandbox", "preview"})
  public void MercuryInteractiveMapsTest_001_MapModal_Url_Title_PinPopUp_Close() {
    init();

    maps.clickMapThumbnail();

    Assertion.assertTrue(
        maps.isMapModalVisible(),
        "Map modal is hidden"
    );

    PageObjectLogging.log(
        "Map modal",
        "is visible",
        true
    );

    boolean result = maps.isMapIdInUrl();
    PageObjectLogging.log(
        "Url",
        "match pattern ?map=",
        "does not match pattern ?map=",
        result
    );

    result = maps.isTextInMapTitleHeader();
    PageObjectLogging.log(
        "Map title in header",
        "is displayed",
        "is not displayed",
        result
    );

    maps.switchToMapFrame();
    maps.clickPin();

    result = maps.isPinPopUp();
    PageObjectLogging.log(
        "Pin popup",
        "appears",
        "does not appear",
        result
    );

    maps.switchToDefaultFrame();
    maps.clickCloseButton();

    result = !maps.isMapModalVisible();
    PageObjectLogging.log(
        "Map modal",
        "is closed",
        "is opened",
        result
    );
  }
}
