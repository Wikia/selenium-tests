package com.wikia.webdriver.TestCases.Mobile;

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

}
