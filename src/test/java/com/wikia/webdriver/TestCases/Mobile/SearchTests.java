package com.wikia.webdriver.TestCases.Mobile;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileSearchPageObject;

public class SearchTests extends TestTemplate {

	@Test(groups={"mobile"})
	public void SearchTests_001_Suggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.triggerSearch();
		mobile.typeInSearchQuery("PMG");
		mobile.verifySuggestions();
	}

	@Test(groups={"mobile"})
	public void SearchTests_002_SuggestionsPlus() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		mobile.triggerSearch();
		mobile.typeInSearchQuery("PMG");
		mobile.verifySuggestions();
		mobile.verifySuggestionsPlusButton();
		mobile.selectPlusFromSuggestions(3);
	}

	@Test(groups={"mobile"})
	public void SearchTests_003_SearchPage() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileSearchPageObject search = mobile.searchQuery("PMG");
		search.verifySearchResultsList();
	}

	@Test(groups={"mobile"})
	public void SearchTests_004_SearchPageButtons() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileSearchPageObject search = mobile.searchQuery("PMG");
		search.verifySearchResultsList();
		search.verifyNextPageButtonIsVisible();
		search.clickOnSearchNextPageButton();
		search.verifyPreviousPageButtonIsVisible();
		search.clickOnSearchPreviousPageButton();
		search.verifyNextPageButtonIsVisible();
	}


	@Test(groups={"mobile"})
	public void SearchTests_005_SearchPageResults() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileSearchPageObject search = mobile.searchQuery("PMG");
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
