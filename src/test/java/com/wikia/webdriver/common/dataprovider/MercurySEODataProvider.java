package com.wikia.webdriver.common.dataprovider;

import com.wikia.webdriver.common.core.url.Page;
import org.testng.annotations.DataProvider;

public class MercurySEODataProvider {

    @DataProvider
    public static Object[][] htmlTitles() {
        return new Object[][] {
            {
                new Page("sktest123", "/wiki/Sktest123_Wiki"), "Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Style-5H2"), "Style-5H2 - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/TestDisplayTitle"), "testing abc - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Premium_Videos"), "Category:Premium Videos - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Non-premium_Videos"), "Category:Non-premium Videos - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Premium"), "PremiumVideos - Sktest123 Wiki - Wikia"
            }, {
                new Page("es.pokemon", "/wiki/WikiDex"), "WikiDex - Wikia"
            }, {
                new Page("es.pokemon", "/wiki/Lista_de_Pokémon"), "Lista de Pokémon - WikiDex - Wikia"
            }, {
                new Page("es.pokemon", "/wiki/Categoría:Regiones"), "Categoría:Regiones - WikiDex - Wikia"
            }, {
                new Page("starwars", "/wiki/Main_Page"), "Wookieepedia - Wikia"
            }, {
                new Page("starwars", "/wiki/Droid_starfighter"), "Droid starfighter - Wookieepedia - Wikia"
            }, {
                new Page("starwars", "/main/category/Future_films"), "Future_films"
            }, {
                new Page("starwars", "/main/section/Films"), "Films"
            }, {
                new Page("mediawiki119", "/d/f/203236/latest"), ""
            }, {
                new Page("mediawiki119", "/d/p/2706859396041803285"), ""
            }, {
                new Page("mediawiki119", "/d/u/27334045"), ""
            }, {
                new Page("dnd4", "/wiki/Dungeons_&_Dragons"), "Dungeons & Dragons - D&D4 Wiki - Wikia"
            }
        };
    }
}
