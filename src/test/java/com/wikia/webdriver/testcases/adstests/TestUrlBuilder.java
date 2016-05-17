package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestUrlBuilder extends TemplateNoFirstLoad {

  private static Object[][] TEST_DATA = new Object[][]{
      {
          "runescape", "RuneScape_Wiki", "http://runescape.wikia.com/wiki/RuneScape_Wiki",
          "http://preview.runescape.wikia.com/wiki/RuneScape_Wiki",
          "http://sandbox.runescape.wikia.com/wiki/RuneScape_Wiki",
          "http://sandbox-mercurydev.runescape.wikia.com/wiki/RuneScape_Wiki",
          "http://runescape.dmytror.wikia-dev.com/wiki/RuneScape_Wiki",
          "http://runescape.wikia-staging.com/wiki/RuneScape_Wiki"
      },
      {
          "yugioh", "Main_Page", "http://yugioh.wikia.com/wiki/Main_Page",
          "http://preview.yugioh.wikia.com/wiki/Main_Page",
          "http://sandbox.yugioh.wikia.com/wiki/Main_Page",
          "http://sandbox-mercurydev.yugioh.wikia.com/wiki/Main_Page",
          "http://yugioh.dmytror.wikia-dev.com/wiki/Main_Page",
          "http://yugioh.wikia-staging.com/wiki/Main_Page"
      },
      {
          "naruto", "Narutopedia", "http://naruto.wikia.com/wiki/Narutopedia",
          "http://preview.naruto.wikia.com/wiki/Narutopedia",
          "http://sandbox.naruto.wikia.com/wiki/Narutopedia",
          "http://sandbox-mercurydev.naruto.wikia.com/wiki/Narutopedia",
          "http://naruto.dmytror.wikia-dev.com/wiki/Narutopedia",
          "http://naruto.wikia-staging.com/wiki/Narutopedia"
      },
      {
          "leagueoflegends", "League_of_Legends_Wiki",
          "http://leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://preview.leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://sandbox.leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://sandbox-mercurydev.leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.dmytror.wikia-dev.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.wikia-staging.com/wiki/League_of_Legends_Wiki"
      },
      {
          "es.drama", "Portada", "http://es.drama.wikia.com/wiki/Portada",
          "http://preview.es.drama.wikia.com/wiki/Portada",
          "http://sandbox.es.drama.wikia.com/wiki/Portada",
          "http://sandbox-mercurydev.es.drama.wikia.com/wiki/Portada",
          "http://es.drama.dmytror.wikia-dev.com/wiki/Portada",
          "http://es.drama.wikia-staging.com/wiki/Portada"
      },
      {
          "de.marvel-filme", "Marvel-Filme", "http://de.marvel-filme.wikia.com/wiki/Marvel-Filme",
          "http://preview.de.marvel-filme.wikia.com/wiki/Marvel-Filme",
          "http://sandbox.de.marvel-filme.wikia.com/wiki/Marvel-Filme",
          "http://sandbox-mercurydev.de.marvel-filme.wikia.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.dmytror.wikia-dev.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.wikia-staging.com/wiki/Marvel-Filme"
      },
      {
          "de.wikia", "index.php?search=elder&fulltext=Search",
          "http://de.wikia.com/index.php?search=elder&fulltext=Search",
          "http://preview.de.wikia.com/index.php?search=elder&fulltext=Search",
          "http://sandbox.de.wikia.com/index.php?search=elder&fulltext=Search",
          "http://sandbox-mercurydev.de.wikia.com/index.php?search=elder&fulltext=Search",
          "http://wikiaglobal.dmytror.wikia-dev.com/index.php?search=elder&fulltext=Search",
          "http://de.wikia-staging.com/index.php?search=elder&fulltext=Search"
      },
      {
          "it.squadraspecialecobra11", "Squadra_speciale_Cobra_11",
          "http://it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
          "http://preview.it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
          "http://sandbox.it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
          "http://sandbox-mercurydev.it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
          "http://it.squadraspecialecobra11.dmytror.wikia-dev.com/wiki/Squadra_speciale_Cobra_11",
          "http://it.squadraspecialecobra11.wikia-staging.com/wiki/Squadra_speciale_Cobra_11"
      },
      {
          "it.onepiece", "One_Piece_Wiki_Italia",
          "http://it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
          "http://preview.it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
          "http://sandbox.it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
          "http://sandbox-mercurydev.it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
          "http://it.onepiece.dmytror.wikia-dev.com/wiki/One_Piece_Wiki_Italia",
          "http://it.onepiece.wikia-staging.com/wiki/One_Piece_Wiki_Italia"
      },
      {
          "zh.pad", "Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://preview.zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://sandbox.zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://sandbox-mercurydev.zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.dmytror.wikia-dev.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.wikia-staging.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA"
      },
      {
          "ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://preview.ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://sandbox.ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://sandbox-mercurydev.ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://ja.gundam.dmytror.wikia-dev.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://ja.gundam.wikia-staging.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"
      },
      {
          "de.naruto", "Narutopedia", "http://de.naruto.wikia.com/wiki/Narutopedia",
          "http://preview.de.naruto.wikia.com/wiki/Narutopedia",
          "http://sandbox.de.naruto.wikia.com/wiki/Narutopedia",
          "http://sandbox-mercurydev.de.naruto.wikia.com/wiki/Narutopedia",
          "http://de.naruto.dmytror.wikia-dev.com/wiki/Narutopedia",
          "http://de.naruto.wikia-staging.com/wiki/Narutopedia"
      },
      {
          "de.gameofthrones", "Staffel_4", "http://de.gameofthrones.wikia.com/wiki/Staffel_4",
          "http://preview.de.gameofthrones.wikia.com/wiki/Staffel_4",
          "http://sandbox.de.gameofthrones.wikia.com/wiki/Staffel_4",
          "http://sandbox-mercurydev.de.gameofthrones.wikia.com/wiki/Staffel_4",
          "http://de.gameofthrones.dmytror.wikia-dev.com/wiki/Staffel_4",
          "http://de.gameofthrones.wikia-staging.com/wiki/Staffel_4"
      },
      {
          "de.gta", "Fahrzeuge_(V)", "http://de.gta.wikia.com/wiki/Fahrzeuge_(V)",
          "http://preview.de.gta.wikia.com/wiki/Fahrzeuge_(V)",
          "http://sandbox.de.gta.wikia.com/wiki/Fahrzeuge_(V)",
          "http://sandbox-mercurydev.de.gta.wikia.com/wiki/Fahrzeuge_(V)",
          "http://de.gta.dmytror.wikia-dev.com/wiki/Fahrzeuge_(V)",
          "http://de.gta.wikia-staging.com/wiki/Fahrzeuge_(V)"
      },
      {
          "de.fahrrad", "Reifenumfang_%28Tabelle%29",
          "http://de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
          "http://preview.de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
          "http://sandbox.de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
          "http://sandbox-mercurydev.de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
          "http://de.fahrrad.dmytror.wikia-dev.com/wiki/Reifenumfang_%28Tabelle%29",
          "http://de.fahrrad.wikia-staging.com/wiki/Reifenumfang_%28Tabelle%29"
      },
      {
          "de.bindingofisaac", "Items", "http://de.bindingofisaac.wikia.com/wiki/Items",
          "http://preview.de.bindingofisaac.wikia.com/wiki/Items",
          "http://sandbox.de.bindingofisaac.wikia.com/wiki/Items",
          "http://sandbox-mercurydev.de.bindingofisaac.wikia.com/wiki/Items",
          "http://de.bindingofisaac.dmytror.wikia-dev.com/wiki/Items",
          "http://de.bindingofisaac.wikia-staging.com/wiki/Items"
      },
      {
          "de.videospielehub", "Videospiele_Hub",
          "http://de.videospielehub.wikia.com/wiki/Videospiele_Hub",
          "http://preview.de.videospielehub.wikia.com/wiki/Videospiele_Hub",
          "http://sandbox.de.videospielehub.wikia.com/wiki/Videospiele_Hub",
          "http://sandbox-mercurydev.de.videospielehub.wikia.com/wiki/Videospiele_Hub",
          "http://de.videospielehub.dmytror.wikia-dev.com/wiki/Videospiele_Hub",
          "http://de.videospielehub.wikia-staging.com/wiki/Videospiele_Hub"
      },
      {
          "jedipedia", "Jedipedia:Hauptseite",
          "http://jedipedia.wikia.com/wiki/Jedipedia:Hauptseite",
          "http://preview.jedipedia.wikia.com/wiki/Jedipedia:Hauptseite",
          "http://sandbox.jedipedia.wikia.com/wiki/Jedipedia:Hauptseite",
          "http://sandbox-mercurydev.jedipedia.wikia.com/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.dmytror.wikia-dev.com/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.wikia-staging.com/wiki/Jedipedia:Hauptseite"
      },
      {
          "en.memory-alpha", "Portal:Main", "http://en.memory-alpha.wikia.com/wiki/Portal:Main",
          "http://preview.en.memory-alpha.wikia.com/wiki/Portal:Main",
          "http://sandbox.en.memory-alpha.wikia.com/wiki/Portal:Main",
          "http://sandbox-mercurydev.en.memory-alpha.wikia.com/wiki/Portal:Main",
          "http://en.memory-alpha.dmytror.wikia-dev.com/wiki/Portal:Main",
          "http://en.memory-alpha.wikia-staging.com/wiki/Portal:Main"
      },
      {
          "de.memory-alpha", "Hauptseite", "http://de.memory-alpha.wikia.com/wiki/Hauptseite",
          "http://preview.de.memory-alpha.wikia.com/wiki/Hauptseite",
          "http://sandbox.de.memory-alpha.wikia.com/wiki/Hauptseite",
          "http://sandbox-mercurydev.de.memory-alpha.wikia.com/wiki/Hauptseite",
          "http://de.memory-alpha.dmytror.wikia-dev.com/wiki/Hauptseite",
          "http://de.memory-alpha.wikia-staging.com/wiki/Hauptseite"
      },
      {
          "yoyo", "Main_Page", "http://yoyo.wikia.com/wiki/Main_Page",
          "http://preview.yoyo.wikia.com/wiki/Main_Page",
          "http://sandbox.yoyo.wikia.com/wiki/Main_Page",
          "http://sandbox-mercurydev.yoyo.wikia.com/wiki/Main_Page",
          "http://yoyo.dmytror.wikia-dev.com/wiki/Main_Page",
          "http://yoyo.wikia-staging.com/wiki/Main_Page"
      },
      {
          "wowwiki", "Portal:Main", "http://wowwiki.wikia.com/wiki/Portal:Main",
          "http://preview.wowwiki.wikia.com/wiki/Portal:Main",
          "http://sandbox.wowwiki.wikia.com/wiki/Portal:Main",
          "http://sandbox-mercurydev.wowwiki.wikia.com/wiki/Portal:Main",
          "http://wowwiki.dmytror.wikia-dev.com/wiki/Portal:Main",
          "http://wowwiki.wikia-staging.com/wiki/Portal:Main"
      }
  };

  @Test(groups = "TestUrlBuilder")
  public void urlBuilder() {
    Configuration.setTestValue("qs", "");

    for (Object[] data : TEST_DATA) {
      Assertion.assertEquals(new UrlBuilder("prod")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[2]);
      Assertion.assertEquals(new UrlBuilder("preview")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[3]);
      Assertion.assertEquals(new UrlBuilder("sandbox")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[4]);
      Assertion.assertEquals(new UrlBuilder("sandbox-mercurydev")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[5]);
      Assertion.assertEquals(new UrlBuilder("dev-dmytror")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[6]);
      Assertion.assertEquals(new UrlBuilder("staging")
                                 .getUrlForPath((String) data[0], (String) data[1]),
                             (String) data[7]);
    }
  }

  @Test(groups = "TestUrlBuilder")
  public void appendQueryString() {
    String cb = "cb=1111";
    Configuration.setTestValue("qs", cb);

    Assertion.assertEquals(new UrlBuilder("prod").getUrlForPath("wowwiki", "Portal:Main"),
                           "http://wowwiki.wikia.com/Portal:Main?" + cb);
  }

  @AfterMethod(alwaysRun = true)
  public void clearCustomTestProperties() {
    Configuration.clearCustomTestProperties();
  }
}
