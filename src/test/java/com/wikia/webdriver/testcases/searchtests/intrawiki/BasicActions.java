package com.wikia.webdriver.testcases.searchtests.intrawiki;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.IntraWikiSearchProvider;
import com.wikia.webdriver.common.templates.search.IntraWiki;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject.sortOptions;

import org.testng.annotations.Test;

import java.util.List;

/*
 * anonSearch: As anon basic search action and verify you are on search result page. This also
 * prevents goSearch being active by default. userSearch: As user do basic search action and verify
 * you are on search result page. This also prevents goSearch being active by default. 1. Search for
 * different phrases and verify if they give correct first result 2. pagination: Check search page
 * pagination 3. resultsCount: Verify number of results on page 4. noResults: Search for not
 * existing phrase and verify there is no results 5. filtering: Search for some phrase and verify
 * filtering options work correctly and give correct results 6. sortingVideos: Search for some
 * phrase and verify sorting options for video give correct results 7. sortingImages: Search for
 * some phrase and verify sorting options for images give correct results 9. Verify search page hubs
 * and titles are translatable 10. Select photos only option and verify there are only photos, then
 * select videos only option and verify: 1. the number of videos = 25 2. the number of videos equals
 * the number of play buttons 3. video titles start with "file" prefix 11. Verify if there are
 * correct advanced option set as a default 12. Search for some image without typing extension
 * (.jpg) and verify photo is found 13. Search for different phrases and verify there are correct
 * namespaces in result titles 14. Verify top module 15. Verify push to top is working in
 * community.wikia.com
 */
@Test(groups = {"IntraWikiSearchBasicActions", "IntraWikiSearch"})
public class BasicActions extends IntraWiki {

  @Test(groups = {"anonSearch", "Search", "Search1"})
  public void anonSearch() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(testedWiki);
    NavigationBar navigation = new NavigationBar(driver);
    IntraWikiSearchPageObject search = navigation.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.verifyFirstArticleNameTheSame(SearchContent.SEARCH_PHRASE_RESULTS);
  }

  @Test(groups = {"userSearch", "Search", "Search2"})
  @Execute(asUser = User.USER)
  public void userSearch() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage(testedWiki);
    NavigationBar navigation = new NavigationBar(driver);
    IntraWikiSearchPageObject search = navigation.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.verifyFirstArticleNameTheSame(SearchContent.SEARCH_PHRASE_RESULTS);
  }

  @Test(groups = {"IntraWikiSearch_002", "Search", "Search3"})
  @RelatedIssue(issueID = "MAIN-5044", comment = "make sure there is a pagination, "
                                                 + "built of 6 or 11 elements depending or what "
                                                 + "pagination link you enter")
  public void pagination() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PAGINATION_RESULTS);
    String firstResult = search.getTitleInnerText();
    search.verifyPagination();
    search.clickNextPaginator();
    search.verifyFirstArticleNameNotTheSame(firstResult);
    search.verifyPagination();
    search.clickPrevPaginator();
    search.verifyFirstArticleNameTheSame(firstResult);
    search.verifyPagination();
    search.verifyLastResultPage();
  }

  @Test(groups = {"IntraWikiSearch_003", "Search", "Search4"})
  public void resultsCount() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE_HIGH);
    search.clickNextPaginator();
    search.verifyResultsCount(SearchContent.RESULTS_PER_PAGE_HIGH);
  }

  @Test(groups = {"IntraWikiSearch_004", "Search", "Search1"})
  public void noResults() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_NO_RESULTS_2);
    search.verifyNoResults();
  }

  @Test(groups = {"IntraWikiSearch_005", "Search", "Search2"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void filtering() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.selectPhotosVideos();
    search.verifyTitlesNotEmpty();
    search.selectPhotosOnly();
    search.verifyTitlesNotEmpty();
    search.verifyAllResultsImages(SearchContent.RESULTS_PER_PAGE_HIGH);
    search.selectVideosOnly();
    search.verifyTitlesNotEmpty();
    search.verifyAllResultsVideos(SearchContent.RESULTS_PER_PAGE_HIGH);
  }

  @Test(groups = {"IntraWikiSearch_006", "Search", "Search3"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void sortingVideos() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.selectPhotosVideos();
    search.selectVideosOnly();
    search.verifyTitlesNotEmpty();
    search.sortBy(sortOptions.DURATION);
    List<String> titles1 = search.getTitles();
    search.sortBy(sortOptions.RELEVANCY);
    List<String> titles2 = search.getTitles();
    search.sortBy(sortOptions.PUBLISH_DATE);
    List<String> titles3 = search.getTitles();
    search.compareTitleListsNotEquals(titles1, titles2);
    search.compareTitleListsNotEquals(titles1, titles3);
    search.compareTitleListsNotEquals(titles2, titles3);
  }

  @Test(groups = {"IntraWikiSearch_007", "Search", "Search4"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void sortingImages() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.selectPhotosVideos();
    search.selectPhotosOnly();
    search.verifyTitlesNotEmpty();
    search.sortBy(sortOptions.RELEVANCY);
    List<String> titles1 = search.getTitles();
    search.sortBy(sortOptions.PUBLISH_DATE);
    List<String> titles2 = search.getTitles();
    search.compareTitleListsNotEquals(titles1, titles2);
  }

  @Test(groups = {"IntraWikiSearch_009", "Search", "Search1"})
  public void languageTranslation() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.addQqxUselang();
    search.verifyLanguageTranslation();
  }

  @Test(groups = {"IntraWikiSearch_010", "Search", "Search2"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void selectImagesOrVideos() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.selectPhotosVideos();
    search.selectPhotosOnly();
    search.verifyPhotosOnly();
    search.selectVideosOnly();
    search.verifyVideosOnly();
  }

  @Test(groups = {"IntraWikiSearch_011", "Search", "Search3"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void defaultNamespaces() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.clickAdvancedButton();
    search.verifyDefaultNamespaces();
  }

  @Test(groups = {"IntraWikiSearch_012", "Search", "Search4"})
  public void noFileExtensionNeed() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_RESULT_WITH_EXTENSION);
    search.selectPhotosVideos();
    search.verifyFirstResultExtension(SearchContent.SEARCH_RESULT_WITH_EXTENSION);
  }

  @Test(dataProviderClass = IntraWikiSearchProvider.class, dataProvider = "getNamespaces",
      groups = {"IntraWikiSearch_013", "Search", "Search1"})
  @RelatedIssue(issueID = "MAIN-7142",
      comment = "Product code defect. No need to test manually")
  public void namespaces(String searchPhrase, String namespace) {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(searchPhrase);
    search.selectAllAdvancedOptions();
    SearchPageObject searchPage = new SearchPageObject(driver);
    searchPage.clickSearchButton();
    searchPage.setSearchTab(SearchPageObject.SearchTab.EVERYTHING);
    search.verifyNamespace(namespace);
  }

  @Test(groups = {"IntraWikiSearch_014", "Search", "Search2"})
  public void topModule() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(testedWiki);
    search.searchFor(SearchContent.SEARCH_PHRASE_RESULTS);
    search.verifyTopModule();
  }

  @Test(groups = {"IntraWikiSearch_015", "Search", "Search3"})
  public void communityPushToTopWikiResult() {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(communityWiki);
    search.searchForInGlobalNavIfPresent(SearchContent.SEARCH_WIKI);
    search.verifyPushToTopWikiTitle(SearchContent.SEARCH_WIKI);
    search.verifyPushToTopWikiThumbnail();
  }
}
