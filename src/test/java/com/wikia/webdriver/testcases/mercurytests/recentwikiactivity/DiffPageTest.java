package com.wikia.webdriver.testcases.mercurytests.recentwikiactivity;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.DiffPage;

import org.testng.annotations.Test;

@Execute(onWikia = "aga")
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)

public class DiffPageTest extends NewTestTemplate{

  @Test()
  public void diff_goingBackToRecentWikiActivity() {
    new DiffPage()
        .getDiff()
        .goBackToRWA();
  }
}
