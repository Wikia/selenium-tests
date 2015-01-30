package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SearchDataProvider {

  @DataProvider
  public static final Object[][] getOnWikiHostsTermsAndMatchUrls() {
    return new Object[][]{
        {"http://starwars.wikia.com/", "darth vader",
         "http://starwars.wikia.com/wiki/Anakin_Skywalker"},
        {"http://callofduty.wikia.com/", "Frank Woods",
         "http://callofduty.wikia.com/wiki/Frank_Woods"}
    };
  }

  /*
   *
   */
  @DataProvider
  public static final Object[][] getExpectedWikiResults() {
    return new Object[][]{
        {"Marvel Database"},
        {"Marvel Movies"},
        {"Marvel: War of Heroes Wiki"},
        {"Marvel: Avengers Alliance Wiki"},
        {"Marvel Fanon"},
        {"Marvel-Microheroes Wiki"},
        {"Marvel Heroes Wiki"},
        {"Marvel Cinematic Universe Wiki"},
        {"Comic Crossroads"},
        {"Marvel Animated Universe Wiki"},
    };
  }

  public static final List<Integer> getSearchLimits() {
    List<Integer> limits = new ArrayList<Integer>();
    limits.add(20);
    limits.add(50);
    limits.add(100);
    limits.add(250);
    limits.add(500);
    return limits;
  }
}
