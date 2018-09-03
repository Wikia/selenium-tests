package com.wikia.webdriver.testcases.desktop.adstests;

import static com.wikia.webdriver.common.core.configuration.Configuration.DEFAULT_LANGUAGE;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import java.lang.reflect.Constructor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestUrlBuilder {


  private static Object[][] TEST_DATA = new Object[][]{
      {
          "runescape", "wiki/RuneScape_Wiki", false, false,
          "http://runescape.wikia.com/wiki/RuneScape_Wiki",
          "http://runescape.preview.wikia.com/wiki/RuneScape_Wiki",
          "http://runescape.sandbox.wikia.com/wiki/RuneScape_Wiki",
          "http://runescape.sandbox-mercurydev.wikia.com/wiki/RuneScape_Wiki",
          "http://runescape.dmytror.wikia-dev.pl/wiki/RuneScape_Wiki",
          "http://runescape.wikia-staging.com/wiki/RuneScape_Wiki"
      },
      {
          "yugioh", "wiki/Main_Page", false, false,
          "http://yugioh.wikia.com/wiki/Main_Page",
          "http://yugioh.preview.wikia.com/wiki/Main_Page",
          "http://yugioh.sandbox.wikia.com/wiki/Main_Page",
          "http://yugioh.sandbox-mercurydev.wikia.com/wiki/Main_Page",
          "http://yugioh.dmytror.wikia-dev.pl/wiki/Main_Page",
          "http://yugioh.wikia-staging.com/wiki/Main_Page"
      },
      {
          "naruto", "wiki/Narutopedia", false, false,
          "http://naruto.wikia.com/wiki/Narutopedia",
          "http://naruto.preview.wikia.com/wiki/Narutopedia",
          "http://naruto.sandbox.wikia.com/wiki/Narutopedia",
          "http://naruto.sandbox-mercurydev.wikia.com/wiki/Narutopedia",
          "http://naruto.dmytror.wikia-dev.pl/wiki/Narutopedia",
          "http://naruto.wikia-staging.com/wiki/Narutopedia"
      },
      {
          "leagueoflegends", "wiki/League_of_Legends_Wiki", false, false,
          "http://leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.preview.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.sandbox.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.sandbox-mercurydev.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.dmytror.wikia-dev.pl/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.wikia-staging.com/wiki/League_of_Legends_Wiki"
      },
      {
          "wowwiki", "wiki/Portal:Main", true, false,
          "https://wowwiki.wikia.com/wiki/Portal:Main",
          "https://wowwiki.preview.wikia.com/wiki/Portal:Main",
          "https://wowwiki.sandbox.wikia.com/wiki/Portal:Main",
          "https://wowwiki.sandbox-mercurydev.wikia.com/wiki/Portal:Main",
          "https://wowwiki.dmytror.wikia-dev.pl/wiki/Portal:Main",
          "https://wowwiki.wikia-staging.com/wiki/Portal:Main"
      },
      {
          "leagueoflegends", "wiki/League_of_Legends_Wiki", false, true,
          "http://leagueoflegends.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.preview.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.sandbox.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.sandbox-mercurydev.wikia.com/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.dmytror.wikia-dev.pl/wiki/League_of_Legends_Wiki",
          "http://leagueoflegends.wikia-staging.com/wiki/League_of_Legends_Wiki"
      },
      {
          "wowwiki", "wiki/Portal:Main", true, true,
          "https://wowwiki.wikia.com/wiki/Portal:Main",
          "https://wowwiki.preview.wikia.com/wiki/Portal:Main",
          "https://wowwiki.sandbox.wikia.com/wiki/Portal:Main",
          "https://wowwiki.sandbox-mercurydev.wikia.com/wiki/Portal:Main",
          "https://wowwiki.dmytror.wikia-dev.pl/wiki/Portal:Main",
          "https://wowwiki.wikia-staging.com/wiki/Portal:Main"
      },
  };

  private static Object[][] TEST_DATA_LANGUAGE = new Object[][]{
      {
          "drama", "wiki/Portada", false, false,
          "http://es.drama.wikia.com/wiki/Portada",
          "http://es.drama.preview.wikia.com/wiki/Portada",
          "http://es.drama.sandbox.wikia.com/wiki/Portada",
          "http://es.drama.sandbox-mercurydev.wikia.com/wiki/Portada",
          "http://es.drama.dmytror.wikia-dev.pl/wiki/Portada",
          "http://es.drama.wikia-staging.com/wiki/Portada",
          "es"
      },
      {
          "marvel-filme", "wiki/Marvel-Filme", false, false,
          "http://de.marvel-filme.wikia.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.preview.wikia.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.sandbox.wikia.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.sandbox-mercurydev.wikia.com/wiki/Marvel-Filme",
          "http://de.marvel-filme.dmytror.wikia-dev.pl/wiki/Marvel-Filme",
          "http://de.marvel-filme.wikia-staging.com/wiki/Marvel-Filme",
          "de"
      },
      {
          "pad", "wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA", false, false,
          "http://zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.preview.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.sandbox.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.sandbox-mercurydev.wikia.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.dmytror.wikia-dev.pl/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://zh.pad.wikia-staging.com/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "zh"
      },
      {
          "drama", "wiki/Portada", false, true,
          "http://drama.wikia.com/es/wiki/Portada",
          "http://drama.preview.wikia.com/es/wiki/Portada",
          "http://drama.sandbox.wikia.com/es/wiki/Portada",
          "http://drama.sandbox-mercurydev.wikia.com/es/wiki/Portada",
          "http://drama.dmytror.wikia-dev.pl/es/wiki/Portada",
          "http://drama.wikia-staging.com/es/wiki/Portada",
          "es"
      },
      {
          "marvel-filme", "wiki/Marvel-Filme", false, true,
          "http://marvel-filme.wikia.com/de/wiki/Marvel-Filme",
          "http://marvel-filme.preview.wikia.com/de/wiki/Marvel-Filme",
          "http://marvel-filme.sandbox.wikia.com/de/wiki/Marvel-Filme",
          "http://marvel-filme.sandbox-mercurydev.wikia.com/de/wiki/Marvel-Filme",
          "http://marvel-filme.dmytror.wikia-dev.pl/de/wiki/Marvel-Filme",
          "http://marvel-filme.wikia-staging.com/de/wiki/Marvel-Filme",
          "de"
      },
      {
          "squadraspecialecobra11", "wiki/Squadra_speciale_Cobra_11", false, true,
          "http://squadraspecialecobra11.wikia.com/it/wiki/Squadra_speciale_Cobra_11",
          "http://squadraspecialecobra11.preview.wikia.com/it/wiki/Squadra_speciale_Cobra_11",
          "http://squadraspecialecobra11.sandbox.wikia.com/it/wiki/Squadra_speciale_Cobra_11",
          "http://squadraspecialecobra11.sandbox-mercurydev.wikia.com/it/wiki/Squadra_speciale_Cobra_11",
          "http://squadraspecialecobra11.dmytror.wikia-dev.pl/it/wiki/Squadra_speciale_Cobra_11",
          "http://squadraspecialecobra11.wikia-staging.com/it/wiki/Squadra_speciale_Cobra_11",
          "it"
      },
      {
          "onepiece", "wiki/One_Piece_Wiki_Italia", false, true,
          "http://onepiece.wikia.com/it/wiki/One_Piece_Wiki_Italia",
          "http://onepiece.preview.wikia.com/it/wiki/One_Piece_Wiki_Italia",
          "http://onepiece.sandbox.wikia.com/it/wiki/One_Piece_Wiki_Italia",
          "http://onepiece.sandbox-mercurydev.wikia.com/it/wiki/One_Piece_Wiki_Italia",
          "http://onepiece.dmytror.wikia-dev.pl/it/wiki/One_Piece_Wiki_Italia",
          "http://onepiece.wikia-staging.com/it/wiki/One_Piece_Wiki_Italia",
          "it"
      },
      {
          "pad", "wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA", false, true,
          "http://pad.wikia.com/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://pad.preview.wikia.com/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://pad.sandbox.wikia.com/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://pad.sandbox-mercurydev.wikia.com/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://pad.dmytror.wikia-dev.pl/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "http://pad.wikia-staging.com/zh/wiki/Puzzle_%26_Dragons_%E7%BB%B4%E5%9F%BA",
          "zh"
      },
      {
          "gundam", "wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2", false,
          true,
          "http://gundam.wikia.com/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://gundam.preview.wikia.com/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://gundam.sandbox.wikia.com/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://gundam.sandbox-mercurydev.wikia.com/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://gundam.dmytror.wikia-dev.pl/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "http://gundam.wikia-staging.com/ja/wiki/%E3%82%AC%E3%83%B3%E3%83%80%E3%83%9A%E3%83%87%E3%82%A3%E3%82%A2",
          "ja"
      },
      {
          "naruto", "wiki/Narutopedia", false, true,
          "http://naruto.wikia.com/de/wiki/Narutopedia",
          "http://naruto.preview.wikia.com/de/wiki/Narutopedia",
          "http://naruto.sandbox.wikia.com/de/wiki/Narutopedia",
          "http://naruto.sandbox-mercurydev.wikia.com/de/wiki/Narutopedia",
          "http://naruto.dmytror.wikia-dev.pl/de/wiki/Narutopedia",
          "http://naruto.wikia-staging.com/de/wiki/Narutopedia",
          "de"
      },
      {
          "gameofthrones", "wiki/Staffel_4", false, true,
          "http://gameofthrones.wikia.com/de/wiki/Staffel_4",
          "http://gameofthrones.preview.wikia.com/de/wiki/Staffel_4",
          "http://gameofthrones.sandbox.wikia.com/de/wiki/Staffel_4",
          "http://gameofthrones.sandbox-mercurydev.wikia.com/de/wiki/Staffel_4",
          "http://gameofthrones.dmytror.wikia-dev.pl/de/wiki/Staffel_4",
          "http://gameofthrones.wikia-staging.com/de/wiki/Staffel_4",
          "de"
      },
      {
          "gta", "wiki/Fahrzeuge_(V)", false, true,
          "http://gta.wikia.com/de/wiki/Fahrzeuge_(V)",
          "http://gta.preview.wikia.com/de/wiki/Fahrzeuge_(V)",
          "http://gta.sandbox.wikia.com/de/wiki/Fahrzeuge_(V)",
          "http://gta.sandbox-mercurydev.wikia.com/de/wiki/Fahrzeuge_(V)",
          "http://gta.dmytror.wikia-dev.pl/de/wiki/Fahrzeuge_(V)",
          "http://gta.wikia-staging.com/de/wiki/Fahrzeuge_(V)",
          "de"
      },
      {
          "fahrrad", "wiki/Reifenumfang_%28Tabelle%29", false, true,
          "http://fahrrad.wikia.com/de/wiki/Reifenumfang_%28Tabelle%29",
          "http://fahrrad.preview.wikia.com/de/wiki/Reifenumfang_%28Tabelle%29",
          "http://fahrrad.sandbox.wikia.com/de/wiki/Reifenumfang_%28Tabelle%29",
          "http://fahrrad.sandbox-mercurydev.wikia.com/de/wiki/Reifenumfang_%28Tabelle%29",
          "http://fahrrad.dmytror.wikia-dev.pl/de/wiki/Reifenumfang_%28Tabelle%29",
          "http://fahrrad.wikia-staging.com/de/wiki/Reifenumfang_%28Tabelle%29",
          "de"
      },
      {
          "bindingofisaac", "wiki/Items", false, true,
          "http://bindingofisaac.wikia.com/de/wiki/Items",
          "http://bindingofisaac.preview.wikia.com/de/wiki/Items",
          "http://bindingofisaac.sandbox.wikia.com/de/wiki/Items",
          "http://bindingofisaac.sandbox-mercurydev.wikia.com/de/wiki/Items",
          "http://bindingofisaac.dmytror.wikia-dev.pl/de/wiki/Items",
          "http://bindingofisaac.wikia-staging.com/de/wiki/Items",
          "de"
      },
      {
          "videospielehub", "wiki/Videospiele_Hub", false, true,
          "http://videospielehub.wikia.com/de/wiki/Videospiele_Hub",
          "http://videospielehub.preview.wikia.com/de/wiki/Videospiele_Hub",
          "http://videospielehub.sandbox.wikia.com/de/wiki/Videospiele_Hub",
          "http://videospielehub.sandbox-mercurydev.wikia.com/de/wiki/Videospiele_Hub",
          "http://videospielehub.dmytror.wikia-dev.pl/de/wiki/Videospiele_Hub",
          "http://videospielehub.wikia-staging.com/de/wiki/Videospiele_Hub",
          "de"
      },
      {
          "jedipedia", "wiki/Jedipedia:Hauptseite", false, true,
          "http://jedipedia.wikia.com/de/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.preview.wikia.com/de/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.sandbox.wikia.com/de/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.sandbox-mercurydev.wikia.com/de/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.dmytror.wikia-dev.pl/de/wiki/Jedipedia:Hauptseite",
          "http://jedipedia.wikia-staging.com/de/wiki/Jedipedia:Hauptseite",
          "de"
      },
      {
          "memory-alpha", "wiki/Portal:Main", false, true,
          "http://memory-alpha.wikia.com/wiki/Portal:Main",
          "http://memory-alpha.preview.wikia.com/wiki/Portal:Main",
          "http://memory-alpha.sandbox.wikia.com/wiki/Portal:Main",
          "http://memory-alpha.sandbox-mercurydev.wikia.com/wiki/Portal:Main",
          "http://memory-alpha.dmytror.wikia-dev.pl/wiki/Portal:Main",
          "http://memory-alpha.wikia-staging.com/wiki/Portal:Main",
          "en"
      },
      {
          "memory-alpha", "wiki/Hauptseite", false, true,
          "http://memory-alpha.wikia.com/de/wiki/Hauptseite",
          "http://memory-alpha.preview.wikia.com/de/wiki/Hauptseite",
          "http://memory-alpha.sandbox.wikia.com/de/wiki/Hauptseite",
          "http://memory-alpha.sandbox-mercurydev.wikia.com/de/wiki/Hauptseite",
          "http://memory-alpha.dmytror.wikia-dev.pl/de/wiki/Hauptseite",
          "http://memory-alpha.wikia-staging.com/de/wiki/Hauptseite",
          "de"
      },
  };

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void urlBuilderTests() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Configuration.setTestValue("qs", "");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();

    for (Object[] data : TEST_DATA) {
      Assert.assertEquals(
          c.newInstance((String) data[0], "prod", (Boolean) data[2], (Boolean) data[3],
              DEFAULT_LANGUAGE)
              .getUrlForPath((String) data[1]),
          (String) data[4]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "preview", (Boolean) data[2], (Boolean) data[3],
              DEFAULT_LANGUAGE)
              .getUrlForPath((String) data[1]),
          (String) data[5]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "sandbox", (Boolean) data[2], (Boolean) data[3],
              DEFAULT_LANGUAGE)
              .getUrlForPath((String) data[1]),
          (String) data[6]);
      Assert.assertEquals(c.newInstance((String) data[0], "sandbox-mercurydev", (Boolean) data[2],
          (Boolean) data[3], DEFAULT_LANGUAGE)
              .getUrlForPath((String) data[1]),
          (String) data[7]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "dev-dmytror", (Boolean) data[2], (Boolean) data[3],
              DEFAULT_LANGUAGE)
              .getUrlForPath((String) data[1]),
          (String) data[8]);
    }
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void urlWithLanguageBuilderTests() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Configuration.setTestValue("qs", "");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();

    for (Object[] data : TEST_DATA_LANGUAGE) {
      Assert.assertEquals(
          c.newInstance((String) data[0], "prod", (Boolean) data[2], (Boolean) data[3],
              (String) data[10])
              .getUrlForPath((String) data[1]), (String) data[4]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "preview", (Boolean) data[2], (Boolean) data[3],
              (String) data[10])
              .getUrlForPath((String) data[1]),
          (String) data[5]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "sandbox", (Boolean) data[2], (Boolean) data[3],
              (String) data[10])
              .getUrlForPath((String) data[1]),
          (String) data[6]);
      Assert.assertEquals(c.newInstance((String) data[0], "sandbox-mercurydev", (Boolean) data[2],
          (Boolean) data[3], (String) data[10])
              .getUrlForPath((String) data[1]),
          (String) data[7]);
      Assert.assertEquals(
          c.newInstance((String) data[0], "dev-dmytror", (Boolean) data[2], (Boolean) data[3],
              (String) data[10])
              .getUrlForPath((String) data[1]),
          (String) data[8]);
    }
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void getWikiaUrlTests() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();

    Assert.assertEquals(c.newInstance("mediawiki119", "preview", false, false, DEFAULT_LANGUAGE)
            .getWikiGlobalURL(),
        "http://www.preview.wikia.com");
    Assert
        .assertEquals(c.newInstance("mediawiki119", "sandbox-s1", false, false, DEFAULT_LANGUAGE)
                .getWikiGlobalURL(),
            "http://www.sandbox-s1.wikia.com");
    Assert.assertEquals(c.newInstance("mediawiki119", "prod", false, false, DEFAULT_LANGUAGE)
            .getWikiGlobalURL(),
        "http://www.wikia.com");
  }

  private Constructor<UrlBuilder> getUrlBuilderConstructor() throws NoSuchMethodException {
    Constructor<UrlBuilder> c = UrlBuilder.class
        .getDeclaredConstructor(String.class, String.class, Boolean.class, Boolean.class,
            String.class);
    c.setAccessible(true);
    return c;
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void getUrlForPathTests() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();

    Assert.assertEquals(c.newInstance("mediawiki119", "preview", false, false, DEFAULT_LANGUAGE)
            .getUrlForPath("/wiki/Special:Version"),
        "http://mediawiki119.preview.wikia.com/wiki/Special:Version");

    String cb = "cb=1111";
    Configuration.setTestValue("qs", cb);

    Assert.assertEquals(c.newInstance("mediawiki119", "prod", false, false, DEFAULT_LANGUAGE)
            .getUrlForPath("Portal:Main"),
        "http://mediawiki119.wikia.com/Portal:Main?" + cb);
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void getUrlForPathWithLanguageTests() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();

    Assert.assertEquals(c.newInstance("mediawiki119", "preview", false, true, "szl")
            .getUrlForPath("/wiki/Special:Version"),
        "http://mediawiki119.preview.wikia.com/szl/wiki/Special:Version");

    Assert.assertEquals(c.newInstance("mediawiki119", "preview", false, false, "szl")
            .getUrlForPath("/wiki/Special:Version"),
        "http://szl.mediawiki119.preview.wikia.com/wiki/Special:Version");
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void appendQueryStringTests() throws Exception {
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();
    UrlBuilder urlBuilder = c
        .newInstance("mediawiki119", "preview", false, false, DEFAULT_LANGUAGE);

    Assert.assertEquals(urlBuilder.appendQueryStringToURL(
        "http://mediawiki119.preview.wikia.com/wiki/ArticleFeaturesCRUDTestsUserAddingImagePlaceholder?action=edit",
        "useFormat=1"),
        "http://mediawiki119.preview.wikia.com/wiki/ArticleFeaturesCRUDTestsUserAddingImagePlaceholder?action=edit&useFormat=1");
  }

  @Test(groups = {"TestUrlBuilder", "unitTests"})
  public void getUrlWithWWW() throws Exception {
    Configuration.setTestValue("forceFandomDomain", "false");
    Constructor<UrlBuilder> c = getUrlBuilderConstructor();
    UrlBuilder urlBuilder = c
        .newInstance("mercuryautomationtesting", "preview", false, false, DEFAULT_LANGUAGE);

    Assert.assertEquals(urlBuilder.getUrlForWikiPageWithWWW(MobileSubpages.MAIN_PAGE),
        "http://www.mercuryautomationtesting.preview.wikia.com/wiki/Mercury_automation_testing_Wiki");
  }

  @AfterMethod(alwaysRun = true)
  public void clearCustomTestProperties() {
    Configuration.clearCustomTestProperties();
  }
}
