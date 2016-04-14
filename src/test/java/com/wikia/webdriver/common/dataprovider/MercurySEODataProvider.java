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
                new Page("sktest123", "/wiki/Style-5H2?action=edit"), "Editing Style-5H2 - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Style-5H3?vaction=edit"), "Style-5H3 - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Style-5H3?action=history"), "Revision history of \"Style-5H3\" - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:Version"), "Version - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:Videos"), "Videos on this wiki - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:NewFiles"), "Images - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Blog:Recent_posts"), "Blog:Recent posts - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/User_blog:Sktest/test_blog_1"), "User blog:Sktest/test blog 1 - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:Forum"), "Forum - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Board:General_Discussion"), "General Discussion board - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Thread:2610"), "Test post - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Premium_Videos"), "Category:Premium Videos - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Non-premium_Videos"), "Category:Non-premium Videos - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Premium"), "PremiumVideos - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Message_Wall:Sktest"), "Message Wall:Sktest - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Category:Thread:2160"), "Welcome to Sktest123 Wiki! - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:Maps"), "Maps - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Template:Welcome"), "Template:Welcome - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/MediaWiki:Common.css"), "MediaWiki:Common.css - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/MediaWiki:Edit"), "MediaWiki:Edit - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/User:Sktest"), "User:Sktest - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/File:THE_HOBBIT_Trailer_-_2012_Movie_-_Official_HD"), "Video - THE HOBBIT Trailer - 2012 Movie - Official HD - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/File:Giant_prominence_on_the_sun_erupted.jpg"), "Image - Giant prominence on the sun erupted.jpg - Sktest123 Wiki - Wikia"
            }, {
                new Page("sktest123", "/wiki/Special:NonExistingSpecialPage"), "Error - Sktest123 Wiki - Wikia"
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
