package com.wikia.webdriver.TestCases.mercurytests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

/*
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
* */

public class TopBarTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	@Test(groups = {"MercuryTopBarTests_001", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_001_TappingTopBarSearchButtonOpenNavMenu() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyMenuView();
	}

	@Test(groups = {"MercuryTopBarTests_002", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_002_ClickingOptionWithoutChevronOpenArticle() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyOpeningArticleInNav(0);
	}

	@Test(groups = {"MercuryTopBarTests_003", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_003_ClickingOptionWithChevronOpenArticle() { //FIX TYPO
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyOpeningNextLevelInNav(0);
	}

	@Test(groups = {"MercuryTopBarTests_004", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_004_BackLinkFunctionality() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyBackLinkFunctionality(0);
	}

	@Test(groups = {"MercuryTopBarTests_005", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_005_TappingOutsideCloseNav() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyClosingNav();
	}

	@Test(groups = {"MercuryTopBarTests_006", "MercuryTopBarTests", "Mercury"})
	public void MercuryTopBarTests_006_TextEllipsis() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAIN_ARTICLE);
		SearchNavSideMenuComponentObject nav = article.clickSearchButton();
		nav.verifyTextEllipsis(0);
	}
}
