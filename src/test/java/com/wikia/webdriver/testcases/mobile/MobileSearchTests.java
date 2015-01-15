package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileSearchPageObject;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author PMG
 *         <p/>
 *         Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 *         1. Verify that user is able to see suggestions
 *         2. Verify that when user click on + on suggestions text is copied to search field
 *         3. Verify that when user go to Search page when he press search button
 *         4. Verify that search results page looks correctly with pagination (1->2->1)
 *         5. Verify that search results on second page are different than on first page
 *         6. Verify that user is able to go to suggested from search suggestions
 */

public class MobileSearchTests extends NewTestTemplate {

	private String query = "PMG";

	@Test(groups = {"MobileSearch_001", "MobileSearch", "Mobile"})
	public void MobileSearch_001_Suggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
	}

	@Test(groups = {"MobileSearch_002", "MobileSearch", "Mobile"})
	public void MobileSearch_002_SuggestionsPlus() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
		mobile.verifySuggestionsPlusButton();
		mobile.selectPlusFromSuggestions(3);
	}

	@Test(groups = {"MobileSearch_003", "MobileSearch", "Mobile"})
	public void MobileSearch_003_SearchPage() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileSearchPageObject search = mobile.searchQuery(query);
		search.verifySearchResultsList();
	}

	@Test(groups = {"MobileSearch_004", "MobileSearch", "Mobile"})
	public void MobileSearch_004_SearchPageButtons() {
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

	@Test(groups = {"MobileSearch_005", "MobileSearch", "Mobile"})
	public void MobileSearch_005_SearchPageResults() {
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

	@Test(groups = {"MobileSearch_006", "MobileSearch", "Mobile"})
	public void MobileSearch_006_clickOnSuggestions() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.triggerSearch();
		mobile.typeInSearchQuery(query);
		mobile.verifySuggestions(query);
		mobile.selectAndVerifyClickOnSuggestion(1);
	}

}
