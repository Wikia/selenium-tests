package com.wikia.webdriver.testcases.mercurytests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

/**
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
*/

public class TopBarTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	//TBT01
	@Test(groups = {"MercuryTopBarTests_001", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_001_TappingTopBarSearchButtonOpenNavMenu() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyMenuView();
	}

	//TBT02
	@Test(groups = {"MercuryTopBarTests_002", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_002_ClickingOptionWithoutChevronOpenArticle() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyOpeningArticleInNav(0);
	}

	//TBT03
	@Test(groups = {"MercuryTopBarTests_003", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_003_ClickingOptionWithChevronOpensNextLevel() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyOpeningNextLevelInNav(0);
	}

	//TBT04
	@Test(groups = {"MercuryTopBarTests_004", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_004_BackLinkFunctionality() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyBackLinkFunctionality(0);
	}

	//TBT05 - Change js tap to touch tap
	@Test(groups = {"MercuryTopBarTests_005", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_005_TappingOutsideCloseNav() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyClosingNav();
	}

	//TBT06
	@Test(groups = {"MercuryTopBarTests_006", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_006_TextEllipsis() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyTextEllipsis(0);
	}
}
