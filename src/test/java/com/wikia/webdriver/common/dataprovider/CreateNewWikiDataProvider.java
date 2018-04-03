package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

public class CreateNewWikiDataProvider {

  private CreateNewWikiDataProvider() {

  }

  @DataProvider
  private static final Object[][] getLangs() {
    return new Object[][] { {"de"}, {"es"}, {"fr"}, {"it"}, {"ja"}, {"nl"}};
  }

  @DataProvider
  private static final Object[][] getWikis() {
    return new Object[][] {
            {"wikiActivities"},
            {"mercurycc"},
            {"mercuryntacc"},
            {"mercuryntvcc"},
            {"mercuryntavcc"},
            {"mercuryemptycc"},
            {"mercuryemptycceditor"},
            {"dauto2"},
            {"dauto3"},
            {"dauto4"},
            {"dauto5"},
            {"dauto6"},
            {"dauto7"},
            {"dauto-mobile"},
            {"discussions-messages-testing"},
            {"discussions-empty"},
            {"sustainingtestchat"},
            {"testprivatewiki"},
            {"qatestdiscussiosandforum"},
            {"featuredvideo"},
            {"chesskynet"},
            {"globalshortcuts-en"},
            {"infoboxeuropathemetest"},
            {"infoboxnoeuropathemetest"},
            {"mcfwithoutmoreofwikiarticles"},
            {"sydneybuses"},
            {"enwikiwithoutdiscussions"},
            {"mcfwithoutmoreofwikiarticles"},
            {"enwikiwithemptydiscussions"},
            {"gameofthrones"},
            {"enwikiwithemptydiscussions"},
            {"communitytest"},
            {"aga"}
    };
  }


  @DataProvider
  private static final Object[][] getLangSecondHalf() {
    return new Object[][] { {"no"}, {"pl"}, {"pt"}, {"pt-br"}, {"ru"}, {"zh"}};
  }
}
