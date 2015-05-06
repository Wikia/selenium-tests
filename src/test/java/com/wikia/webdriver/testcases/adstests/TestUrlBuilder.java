package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEng
 */
public class TestUrlBuilder extends NewTestTemplate {

  private static Object[][] TEST_DATA = new Object[][]{
      {"runescape", "RuneScape_Wiki", "http://runescape.wikia.com/wiki/RuneScape_Wiki",
       "http://preview.runescape.wikia.com/wiki/RuneScape_Wiki",
       "http://sandbox.runescape.wikia.com/wiki/RuneScape_Wiki",
       "http://runescape.dmytror.wikia-dev.com/wiki/RuneScape_Wiki",
       "http://runescape.127.0.0.1.xip.io:8000/wiki/RuneScape_Wiki"
      },
      {"yugioh", "Main_Page", "http://yugioh.wikia.com/wiki/Main_Page",
       "http://preview.yugioh.wikia.com/wiki/Main_Page",
       "http://sandbox.yugioh.wikia.com/wiki/Main_Page",
       "http://yugioh.dmytror.wikia-dev.com/wiki/Main_Page",
       "http://yugioh.127.0.0.1.xip.io:8000/wiki/Main_Page"
      },
      {"naruto", "Narutopedia", "http://naruto.wikia.com/wiki/Narutopedia",
       "http://preview.naruto.wikia.com/wiki/Narutopedia",
       "http://sandbox.naruto.wikia.com/wiki/Narutopedia",
       "http://naruto.dmytror.wikia-dev.com/wiki/Narutopedia",
       "http://naruto.127.0.0.1.xip.io:8000/wiki/Narutopedia"
      },
      {"leagueoflegends", "League_of_Legends_Wiki",
       "http://leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
       "http://preview.leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
       "http://sandbox.leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
       "http://leagueoflegends.dmytror.wikia-dev.com/wiki/League_of_Legends_Wiki",
       "http://leagueoflegends.127.0.0.1.xip.io:8000/wiki/League_of_Legends_Wiki"
      },
      {"es.drama", "Portada", "http://es.drama.wikia.com/wiki/Portada",
       "http://preview.es.drama.wikia.com/wiki/Portada",
       "http://sandbox.es.drama.wikia.com/wiki/Portada",
       "http://es.drama.dmytror.wikia-dev.com/wiki/Portada",
       "http://es.drama.127.0.0.1.xip.io:8000/wiki/Portada"
      },
      {"de.marvel-filme", "Marvel-Filme", "http://de.marvel-filme.wikia.com/wiki/Marvel-Filme",
       "http://preview.de.marvel-filme.wikia.com/wiki/Marvel-Filme",
       "http://sandbox.de.marvel-filme.wikia.com/wiki/Marvel-Filme",
       "http://de.marvel-filme.dmytror.wikia-dev.com/wiki/Marvel-Filme",
       "http://de.marvel-filme.127.0.0.1.xip.io:8000/wiki/Marvel-Filme"
      },
      {"de.wikia", "index.php?search=elder&fulltext=Search",
       "http://de.wikia.com/index.php?search=elder&fulltext=Search",
       "http://preview.de.wikia.com/index.php?search=elder&fulltext=Search",
       "http://sandbox.de.wikia.com/index.php?search=elder&fulltext=Search",
       "http://wikiaglobal.dmytror.wikia-dev.com/index.php?search=elder&fulltext=Search",
       "http://de.wikia.127.0.0.1.xip.io:8000/index.php?search=elder&fulltext=Search"
      },
      {"it.squadraspecialecobra11", "Squadra_speciale_Cobra_11",
       "http://it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
       "http://preview.it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
       "http://sandbox.it.squadraspecialecobra11.wikia.com/wiki/Squadra_speciale_Cobra_11",
       "http://it.squadraspecialecobra11.dmytror.wikia-dev.com/wiki/Squadra_speciale_Cobra_11",
       "http://it.squadraspecialecobra11.127.0.0.1.xip.io:8000/wiki/Squadra_speciale_Cobra_11"
      },
      {"it.onepiece", "One_Piece_Wiki_Italia",
       "http://it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
       "http://preview.it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
       "http://sandbox.it.onepiece.wikia.com/wiki/One_Piece_Wiki_Italia",
       "http://it.onepiece.dmytror.wikia-dev.com/wiki/One_Piece_Wiki_Italia",
       "http://it.onepiece.127.0.0.1.xip.io:8000/wiki/One_Piece_Wiki_Italia"
      },
      {"zh.pad", "Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
       "http://zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
       "http://preview.zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
       "http://sandbox.zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
       "http://zh.pad.dmytror.wikia-dev.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
       "http://zh.pad.127.0.0.1.xip.io:8000/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA"
      },
      {"ja.gundam", "%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
       "http://ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
       "http://preview.ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
       "http://sandbox.ja.gundam.wikia.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
       "http://ja.gundam.dmytror.wikia-dev.com/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
       "http://ja.gundam.127.0.0.1.xip.io:8000/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2"
      },
      {"de.naruto", "Narutopedia", "http://de.naruto.wikia.com/wiki/Narutopedia",
       "http://preview.de.naruto.wikia.com/wiki/Narutopedia",
       "http://sandbox.de.naruto.wikia.com/wiki/Narutopedia",
       "http://de.naruto.dmytror.wikia-dev.com/wiki/Narutopedia",
       "http://de.naruto.127.0.0.1.xip.io:8000/wiki/Narutopedia"
      },
      {"de.gameofthrones", "Staffel_4", "http://de.gameofthrones.wikia.com/wiki/Staffel_4",
       "http://preview.de.gameofthrones.wikia.com/wiki/Staffel_4",
       "http://sandbox.de.gameofthrones.wikia.com/wiki/Staffel_4",
       "http://de.gameofthrones.dmytror.wikia-dev.com/wiki/Staffel_4",
       "http://de.gameofthrones.127.0.0.1.xip.io:8000/wiki/Staffel_4"
      },
      {"de.gta", "Fahrzeuge_(V)", "http://de.gta.wikia.com/wiki/Fahrzeuge_(V)",
       "http://preview.de.gta.wikia.com/wiki/Fahrzeuge_(V)",
       "http://sandbox.de.gta.wikia.com/wiki/Fahrzeuge_(V)",
       "http://de.gta.dmytror.wikia-dev.com/wiki/Fahrzeuge_(V)",
       "http://de.gta.127.0.0.1.xip.io:8000/wiki/Fahrzeuge_(V)"
      },
      {"de.fahrrad", "Reifenumfang_%28Tabelle%29",
       "http://de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
       "http://preview.de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
       "http://sandbox.de.fahrrad.wikia.com/wiki/Reifenumfang_%28Tabelle%29",
       "http://de.fahrrad.dmytror.wikia-dev.com/wiki/Reifenumfang_%28Tabelle%29",
       "http://de.fahrrad.127.0.0.1.xip.io:8000/wiki/Reifenumfang_%28Tabelle%29"
      },
      {"de.bindingofisaac", "Items", "http://de.bindingofisaac.wikia.com/wiki/Items",
       "http://preview.de.bindingofisaac.wikia.com/wiki/Items",
       "http://sandbox.de.bindingofisaac.wikia.com/wiki/Items",
       "http://de.bindingofisaac.dmytror.wikia-dev.com/wiki/Items",
       "http://de.bindingofisaac.127.0.0.1.xip.io:8000/wiki/Items"
      },
      {"de.videospielehub", "Videospiele_Hub",
       "http://de.videospielehub.wikia.com/wiki/Videospiele_Hub",
       "http://preview.de.videospielehub.wikia.com/wiki/Videospiele_Hub",
       "http://sandbox.de.videospielehub.wikia.com/wiki/Videospiele_Hub",
       "http://de.videospielehub.dmytror.wikia-dev.com/wiki/Videospiele_Hub",
       "http://de.videospielehub.127.0.0.1.xip.io:8000/wiki/Videospiele_Hub"
      },
      {"de.jedipedia", "Jedipedia:Hauptseite",
       "http://www.jedipedia.de/wiki/Jedipedia:Hauptseite",
       "http://preview.www.jedipedia.de/wiki/Jedipedia:Hauptseite",
       "http://sandbox.www.jedipedia.de/wiki/Jedipedia:Hauptseite",
       "http://dejedipedia.dmytror.wikia-dev.com/wiki/Jedipedia:Hauptseite",
       "http://dejedipedia.127.0.0.1.xip.io:8000/wiki/Jedipedia:Hauptseite"
      },
      {"memory-alpha.org", "Portal:Main", "http://en.memory-alpha.org/wiki/Portal:Main",
       "http://preview.en.memory-alpha.org/wiki/Portal:Main",
       "http://sandbox.en.memory-alpha.org/wiki/Portal:Main",
       "http://enmemoryalpha.dmytror.wikia-dev.com/wiki/Portal:Main",
       "http://enmemoryalpha.127.0.0.1.xip.io:8000/wiki/Portal:Main"
      },
      {"de.memory-alpha", "Hauptseite", "http://de.memory-alpha.org/wiki/Hauptseite",
       "http://preview.de.memory-alpha.org/wiki/Hauptseite",
       "http://sandbox.de.memory-alpha.org/wiki/Hauptseite",
       "http://dememoryalpha.dmytror.wikia-dev.com/wiki/Hauptseite",
       "http://dememoryalpha.127.0.0.1.xip.io:8000/wiki/Hauptseite"
      },
      {"yoyo", "Main_Page", "http://www.yoyowiki.org/wiki/Main_Page",
       "http://preview.www.yoyowiki.org/wiki/Main_Page",
       "http://sandbox.www.yoyowiki.org/wiki/Main_Page",
       "http://yoyo.dmytror.wikia-dev.com/wiki/Main_Page",
       "http://yoyo.127.0.0.1.xip.io:8000/wiki/Main_Page"
      },
      {"wowwiki", "Portal:Main", "http://www.wowwiki.com/Portal:Main",
       "http://preview.www.wowwiki.com/Portal:Main",
       "http://sandbox.www.wowwiki.com/Portal:Main",
       "http://wowwiki.dmytror.wikia-dev.com/Portal:Main",
       "http://wowwiki.127.0.0.1.xip.io:8000/Portal:Main"
      }
  };

  @Test(groups = "TestUrlBuilder")
  public void testUrlBuilder() {
    for (Object[] data : TEST_DATA) {
      Assertion.assertEquals((String) data[2], new UrlBuilder("prod")
          .getUrlForPath((String) data[0], (String) data[1]));
      Assertion.assertEquals((String) data[3], new UrlBuilder("preview")
          .getUrlForPath((String) data[0], (String) data[1]));
      Assertion.assertEquals((String) data[4], new UrlBuilder("sandbox")
          .getUrlForPath((String) data[0], (String) data[1]));
      Assertion.assertEquals((String) data[5], new UrlBuilder("dev-dmytror")
          .getUrlForPath((String) data[0], (String) data[1]));
        Assertion.assertEquals((String) data[6], new UrlBuilder("dev-nandy", "CHROMEMOBILEMERCURY")
                .getUrlForPath((String) data[0], (String) data[1]));
    }
  }
}
