package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileSearchPageObject;

public class MobileSearchTests extends NewTestTemplate {

	private String query = "PMG";

	@Test(groups={"searchTest_001", "searchTests", "mobile"})
	public void SearchTests_001_Suggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
	}

	@Test(groups={"searchTest_002", "searchTests", "mobile"})
	public void SearchTests_002_SuggestionsPlus() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
		mobile.verifySuggestionsPlusButton();
		mobile.selectPlusFromSuggestions(3);
	}

	@Test(groups={"searchTest_003", "searchTests", "mobile"})
	public void SearchTests_003_SearchPage() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileSearchPageObject search = mobile.searchQuery(query);
		search.verifySearchResultsList();
	}

	@Test(groups={"searchTest_004", "searchTests", "mobile"})
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

	@Test(groups={"searchTest_005", "searchTests", "mobile"})
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

	@Test(groups={"searchTest_006", "searchTests", "mobile"})
	public void SearchTests_006_clickOnSuggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
		mobile.selectAndVerifyClickOnSuggestion(1);
	}

}
