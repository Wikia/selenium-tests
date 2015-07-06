package com.wikia.webdriver.common.dataprovider.ads;

import org.testng.annotations.DataProvider;

/**
 * Bogna 'bognix' Knychala
 */
public class GermanAdsDataProvider {

  private GermanAdsDataProvider() {

  }

  @DataProvider
  public static Object[][] popularGermanArticles() {
    return new Object[][]{
        {"de.naruto", "Narutopedia"},
        {"de.gameofthrones", "Staffel_4"},
        {"de.gta", "Fahrzeuge_(V)"},
        {"de.fahrrad", "Reifenumfang_%28Tabelle%29"},
        {"de.bindingofisaac", "Items"},
        {"de.videospielehub", "Videospiele_Hub"},
        {"jedipedia", "Obi-Wan_Kenobi"},
        {"de.memory-alpha", "Hauptseite"}
    };
  }

  @DataProvider
  public static Object[][] germanCorpPages() {
    return new Object[][]{
        {"de.wikia", "Wikia"}
    };
  }

  @DataProvider
  public static Object[][] germanArticles() {
    return new Object[][]{
        {"de.gta", "Fahrzeuge_(V)"},
        {"de.gameofthrones", "Staffel_4"}
    };
  }

  @DataProvider
  public static Object[][] popularDEArticlesWithParams() {
    return new Object[][]{
        {"de.clashofclans", "Clash_of_Clans_Wiki",
         "SOI_SITE: wikia; SOI_SUBSITE: gaming; SOI_SUB2SITE: declashofclans; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: mobile,casual,strategy,war,rts,declashofc"},
        {"de.harry-potter", "Severus_Snape",
         "SOI_SITE: wikia; SOI_SUBSITE: entertainment; SOI_SUB2SITE: dehpw; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: pc,xbox360,ps3,nintendo,wii,fantasy,dehpw"},
        {"de.harry-potter", "Hauptseite",
         "SOI_SITE: wikia; SOI_SUBSITE: entertainment; SOI_SUB2SITE: dehpw; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: pc,xbox360,ps3,nintendo,wii,fantasy,dehpw"},
        {"de.terraria", "Terraria_Wiki",
         "SOI_SITE: wikia; SOI_SUBSITE: gaming; SOI_SUB2SITE: deterraria; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: pc,adventure,rpg,deterraria"},
        {"de.gameofthrones", "Game_of_Thrones_Wiki",
         "SOI_SITE: wikia; SOI_SUBSITE: entertainment; SOI_SUB2SITE: usseries; SOI_SUB3SITE: degameofthrones; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; " +
         "SOI_KEYWORDS: pc,ps3,xbox360,tv,book,movie,adventure,strategy,rpg,adventure,drama,degameofth"},
        {"dietributevonpanem", "Lied_vom_Henkersbaum_(The_Hanging_Tree)",
         "SOI_SITE: wikia; SOI_SUBSITE: entertainment; SOI_SUB2SITE: dedietributevonpanem; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: dedietribu"},
        {"jedipedia", "Jedipedia:Hauptseite",
         "SOI_SITE: wikia; SOI_SUBSITE: entertainment; SOI_SUB2SITE: dejedipedia; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: pc,ps3,xbox360,nintendo,movie,scifi,dejedipedi"},
        {"de.clashofclans", "Kategorie:Truppen",
         "SOI_SITE: wikia; SOI_SUBSITE: gaming; SOI_SUB2SITE: declashofclans; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: mobile,casual,strategy,war,rts,declashofc"},
        {"de.gta", "Fahrzeuge_(V)",
         "SOI_SITE: wikia; SOI_SUBSITE: gaming; SOI_SUB2SITE: degta; SOI_SUB3SITE: ; SOI_CONTENT: content; "  +
         "SOI_WERBUNG: true; SOI_KEYWORDS: pc,ps3,xbox360,nintendo,shooter,degta"},
        {"de.clashofclans", "Dorfaufbau",
         "SOI_SITE: wikia; SOI_SUBSITE: gaming; SOI_SUB2SITE: declashofclans; SOI_SUB3SITE: ; " +
         "SOI_CONTENT: content; SOI_WERBUNG: true; SOI_KEYWORDS: mobile,casual,strategy,war,rts,declashofc"},
    };
  }
}
