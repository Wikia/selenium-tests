package com.wikia.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

public class GermanAdsDataProvider {

  public static final Object[][] IVW2_TEST_DATA = new Object[][]{
      {"de.wikia", "Wikia", "RC_WIKIA_HOME"},
      {"de.wikia", "Spezial:Kontakt", "RC_WIKIA_SVCE"},
      {"de.wikia", "%C3%9Cber_Wikia", "RC_WIKIA_SVCE"},
      {"de.wikia", "Presse", "RC_WIKIA_SVCE"},
      {"de.wikia", "Stellen", "RC_WIKIA_SVCE"},
      {"de.wikia", "Projekt:Datenschutz", "RC_WIKIA_SVCE"},
      {"de.wikia", "Spezial:Kontakt", "RC_WIKIA_SVCE"},
      {"de.wikia", "Spezial:UserSignup", "RC_WIKIA_SVCE"},
      {"de.wikia", "Mobil", "RC_WIKIA_MOBIL"},
      {"de.wikia", "Mobil/LyricWiki", "RC_WIKIA_MOBIL"},
      {"de.wikia", "Mobil/GameGuides", "RC_WIKIA_MOBIL"},
      {"de.wikia", "Spezial:Suche?search=elder&fulltext=Search", "RC_WIKIA_SEARCH"},
      {"de.wikia", "Entertainment/Anime", "RC_WIKIA_START"},
      {"de.videospielehub", "Videospiele_Hub", "RC_WIKIA_START"},
      {"de.lifestylehub", "Lifestyle_Hub", "RC_WIKIA_START"},
      {"de.filmhub", "Film_Hub", "RC_WIKIA_START"},
      {"de.tvhub", "TV_Hub", "RC_WIKIA_START"},
      {"de.literaturhub", "Literatur_Hub", "RC_WIKIA_START"},
      {"de.comicshub", "Comics_Hub", "RC_WIKIA_START"},
      {"de.musikhub", "Musik_Hub", "RC_WIKIA_START"},
      {"de.community", "Admin-Bereich:Hauptseite", "RC_WIKIA_COMMUNITY"},
      {"de.community", "Community_Deutschland", "RC_WIKIA_COMMUNITY"},
      {"de.community", "Blog%3AWikia_Deutschland_News", "RC_WIKIA_COMMUNITY"},
      {"de.community", "Forum:%C3%9Cbersicht", "RC_WIKIA_PIN"},
      {"de.elderscrolls", "Elder_Scrolls_Wiki", "RC_WIKIA_UGCGAMES"},
      {"de.swtor", "Star_Wars_-_The_Old_Republic_Wiki", "RC_WIKIA_UGCGAMES"},
      {"shaun", "Shaun", "RC_WIKIA_UGCENT"},
      {"de.marvel-filme", "Marvel-Filme", "RC_WIKIA_UGCLIFESTYLE"},
      {"de.gta", "Hauptseite", "RC_WIKIA_UGCGAMES"},
      {"de.green", "Hauptseite", "RC_WIKIA_UGCLIFESTYLE"},
      {"de.naruto", "Narutopedia", "RC_WIKIA_UGCANIME"}
  };

  private GermanAdsDataProvider() {

  }

  @DataProvider
  public static Object[][] germanArticles() {
    return new Object[][]{
        {"de.deathnote", "Mary_Kenwood"},
        {"de.narnia", "Sandro_Kopp"}
    };
  }
}
