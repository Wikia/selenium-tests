package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;


 public class AdsTests extends TestTemplate{
	 

	@Test(groups = { "AdsTests_001", "AdsTests" })
	public void Top_10_list_Tests_001_preFooterEnabledOnWikiSearch() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiSearchMiddleLink1+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiSearchMiddleLink2+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_002", "AdsTests" })
	public void Top_10_list_Tests_002_preFooterDisabledOnWikiSearch() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiSearchMiddleLink1+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiSearchMiddleLink2+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_003", "AdsTests" })
	public void Top_10_list_Tests_003_preFooterEnabledOnWikiMainPage() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiMainPageLink1+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiMainPageLink2+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_004", "AdsTests" })
	public void Top_10_list_Tests_004_preFooterDisabledOnWikiMainPage() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiMainPageLink1+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiMainPageLink2+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_005", "AdsTests" })
	public void Top_10_list_Tests_005_preFooterEnabledOnWikiNewForum() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiNewForumLink1+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiNewForumLink2+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_006", "AdsTests" })
	public void Top_10_list_Tests_006_preFooterDisabledOnWikiNewForum() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiNewForumLink1+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiNewForumLink2+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_007", "AdsTests" })
	public void Top_10_list_Tests_007_preFooterEnabledOnWikiCategory() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiCategoryLink1+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiCategoryLink2+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_008", "AdsTests" })
	public void Top_10_list_Tests_008_preFooterDisabledOnWikiCategory() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiCategoryLink1+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiCategoryLink2+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_009", "AdsTests" })
	public void Top_10_list_Tests_009_preFooterEnabledOnWikiArticle() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiArticleLink1+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiArticleLink2+URLsContent.prefooterAdsEnabled);
		wikiObject.verifyAdsVisible_PrefooterAds();						
	}
	@Test(groups = { "AdsTests_010", "AdsTests" })
	public void Top_10_list_Tests_010_preFooterDisabledOnWikiArticle() {
		WikiBasePageObject wikiObject = new WikiBasePageObject(driver,Global.DOMAIN);
		wikiObject.getUrl(URLsContent.wikiArticleLink1+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();
		wikiObject.getUrl(URLsContent.wikiArticleLink2+URLsContent.prefooterAdsDisabled);
		wikiObject.verifyAdsInvisible_PrefooterAds();						
	}
	
	
		//WIKI SEARCH MIDDLE (>2400 px)
		// http://preview.plantsvszombies.wikia.com/wiki/Special:Search?ns0=1&ns14=1&search=zombies?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		// http://preview.arresteddevelopment.wikia.com/wiki/Special:Search?ns0=1&ns14=1&search=arrested?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
			
		//WIKI MAIN PAGE	
		// http://preview.arresteddevelopment.wikia.com/wiki/Main_Page?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		// http://preview.easycrafts.wikia.com/wiki/Main_Page?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
				
		// old FORUM MIDDLE (<2400 px)
		// ads not working on old forum type
				
		// newForum new type
		// preview.community.wikia.com/wiki/Thread:472861?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		// preview.community.wikia.com/wiki/Thread:477388?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		
		//Category 
		// http://preview.harrypotter.wikia.com/wiki/Category:Harry_Potter_Wiki?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		// http://preview.fatalfrontier.wikia.com/wiki/Category:Article_stubs?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED
		
		
		//ARTICLE MIDDLE (>2400 px)
		//http://preview.plantsvszombies.wikia.com/wiki/Ladder_Zombie?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_ENABLED
		//http://preview.arresteddevelopment.wikia.com/wiki/Pilot?AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_ENABLED
		
		
		
	
	
	
}