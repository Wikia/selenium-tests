package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileSearchPageObject;

public class SearchTests extends NewTestTemplate {

	private String query = "PMG";

	@Test(groups={"searchTest_001", "mobile"})
	public void SearchTests_001_Suggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions();
	}

	@Test(groups={"searchTest_002", "mobile"})
	public void SearchTests_002_SuggestionsPlus() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions();
		mobile.verifySuggestionsPlusButton();
		mobile.selectPlusFromSuggestions(3);
	}

	@Test(groups={"searchTest_003", "mobile"})
	public void SearchTests_003_SearchPage() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileSearchPageObject search = mobile.searchQuery(query);
		search.verifySearchResultsList();
	}

	@Test(groups={"searchTest_004", "mobile"})
	public void SearchTests_004_SearchPageButtons() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileSearchPageObject search = mobile.searchQuery(query);
		search.verifySearchResultsList();
		search.verifyNextPageButtonIsVisible();
		search.clickOnSearchNextPageButton();
		search.verifyPreviousPageButtonIsVisible();
		search.clickOnSearchPreviousPageButton();
		search.verifyNextPageButtonIsVisible();
	}


	@Test(groups={"searchTest_005", "mobile"})
	public void SearchTests_005_SearchPageResults() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileSearchPageObject search = mobile.searchQuery(query);
		search.verifySearchResultsList();
		search.verifyNextPageButtonIsVisible();
		List<String> firstPage = search.getResult();
		search.clickOnSearchNextPageButton();
		search.verifyPreviousPageButtonIsVisible();
		List<String> secondPage = search.getResult();
		search.clickOnSearchPreviousPageButton();
		search.verifyNextPageButtonIsVisible();
		List<String> thirdPage = search.getResult();
		search.compareResultsEquals(firstPage, thirdPage);
		search.compareResultsNotEquals(secondPage, firstPage);
	}

}
