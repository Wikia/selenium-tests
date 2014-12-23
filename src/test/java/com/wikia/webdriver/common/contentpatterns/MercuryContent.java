package com.wikia.webdriver.common.contentpatterns;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SpecialMercuryPageObject;
import netscape.javascript.JSException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author: Rodrigo Gomez, Łukasz Nowak
 */
public class MercuryContent {

	public static final String mercurySpecialPage = "Special:Mercury";

	//Articles prepared for Mercury
	public static final String MERCURY_GALLERY_TEST_ARTICLE= "MercuryGallery";
	public static final String MERCURY_VIDEO_TEST_ARTICLE = "MercuryVideoTest";
	public static final String MERCURY_COMMENTS_TEST_ARTICLE = "MercuryCommentsTest";
	public static final String MERCURY_CATEGORY_TEST_ARTICLE = "MercuryCategoryTests";
	public static final String MERCURY_INFOBOX_TEST_ARTICLE = "MercuryInfobox";
	public static final String MERCURY_TOC_TEST_ARTICLE = "MercuryToc";
	public static final String MERCURY_MAIN_ARTICLE = "QAarticle1398440783467hh";

	//Index variables for Mercury
	public static final int MERCURY_GALLERY_IMAGE_INDEX = 0;
	public static final int MERCURY_SEARCH_CLICK_INDEX = 1;

	//Mercury content strings
	public static final String MERCURY_SEARCH_PASS = "test p";
	public static final String MERCURY_SEARCH_NOT_MATCH = "Sorry, we couldn't find anything that matched your search!";
	public static void turnOnMercurySkin(WebDriver driver, String wikiURL) {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		SpecialMercuryPageObject mercuryPage = base.openSpecialMercury(wikiURL);
		//Temporary solution
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.cookie='useskin=mercury;domain=.wikia.com;expires=' + (new Date('2015')) +';path=/'");
		}catch(JSException exec){

		}
		//		mercuryPage.clickMercuryButton(); Uncomment this line after temporary soultion won`t be needed any more
	}
}
