package com.wikia.webdriver.testcases.mercurytests.recentwikiactivity;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.RecentWikiActivityPage;

import org.testng.annotations.Test;

@Execute(onWikia = "aga")
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class RecentWikiActivityTest extends NewTestTemplate {
//
//  @Test()
//  public void recentWikiActivity_cardWithDetailsOfEditIsOnTheList() {
//
//  }

  @Test()
  @Execute(asUser = User.ANONYMOUS)
  public void recentWikiActivity_redirectionToDiffPage() {
    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage();
  }
}
