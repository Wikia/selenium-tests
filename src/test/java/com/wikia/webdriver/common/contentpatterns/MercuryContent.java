package com.wikia.webdriver.common.contentpatterns;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SpecialMercuryPageObject;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak
 */
public class MercuryContent {

	public static final String MERCURY_WIKI = "mercurywikitests.wikia.com";
	public static final String MERCURY_SPECIAL_PAGE = "Special:Mercury";

	//Articles prepared for Mercury
	public static final String MERCURY_TEST = "MercuryTest";
	public static final String MERCURY_MAPS = "embedMap";
	public static final String MERCURY_GALLERY_TEST_ARTICLE= "MercuryGallery";
	public static final String MERCURY_VIDEO_TEST_ARTICLE = "MercuryVideoTest";
	public static final String MERCURY_COMMENTS_TEST_ARTICLE = "MercuryCommentsTest";
	public static final String MERCURY_CATEGORY_TEST_ARTICLE = "MercuryCategoryTests";
	public static final String MERCURY_INFOBOX_TEST_ARTICLE = "MercuryInfobox";
	public static final String MERCURY_TOC_TEST_ARTICLE = "MercuryToc";
	public static final String MERCURY_MAIN_ARTICLE = "MercuryWikiTests_Wiki";

	//Index variables for Mercury
	public static final int MERCURY_GALLERY_IMAGE_INDEX = 0;
	public static final int MERCURY_SEARCH_CLICK_INDEX = 1;

	//Mercury content strings
	public static final String MERCURY_SEARCH_PASS = "test p";
	public static final String MERCURY_SEARCH_NOT_MATCH = "Sorry, we couldn't find anything that matched your search!";

	public static void turnOnMercurySkin(WebDriver driver, String wikiURL) {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		SpecialMercuryPageObject mercuryPage = base.openSpecialMercury(wikiURL);
		mercuryPage.clickMercuryButton(); //Uncomment this line after temporary solution won`t be needed any more
	}
}
