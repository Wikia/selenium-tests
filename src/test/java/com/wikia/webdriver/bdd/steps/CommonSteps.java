package com.wikia.webdriver.bdd.steps;

import com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs.ApiDocsPage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.bdd.context.TestingContext;
import com.wikia.webdriver.bdd.context.TestingContextImpl;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonSteps {
	private TestingContext testingContext;
	private String currentWikiUrl;

	@Before
	public void init() {
		testingContext = new TestingContextImpl() {{
			init();
		}};
	}

	@After
	public void destroy() {
		testingContext.close();
		testingContext = null;
	}

	@Given("I am on wikia global")
	public void I_am_on_wikia_global() throws Throwable {
		HomePageObject homePageObject = new HomePageObject(testingContext.getDriver());
		homePageObject.openHomePage();
		testingContext.setPage(homePageObject);
	}

	@Then("^I should see \"([^\"]*)\"$")
	public void I_see(String phrase) throws Throwable {
		testingContext.getPage().waitForText(phrase);
	}

	@When("^I search for \"([^\"]*)\"$")
	public void I_search_for(String phrase) throws Throwable {
		testingContext.setPage(((HomePageObject) testingContext.getPage()).searchFor(phrase));
	}

	@Then("^I see following search results first:$")
	public void I_see_following_search_results_first(DataTable searchResultTable) throws Throwable {
		((CrossWikiSearchPageObject) testingContext.getPage())
				.verifyTopResultsAre(searchResultTable.<String>asList(String.class));
	}

	@When("^I click \"([^\"]*)\"$")
	public void I_click(String buttonOrLinkText) throws Throwable {
		testingContext.getPage().clickLink(buttonOrLinkText);
	}

	@Given("^non-corporate Wiki$")
	public void non_corporate_Wiki() throws Throwable {
		// Express the Regexp above with the code you wish you had
		currentWikiUrl = testingContext.getWikiURL();
	}

	@When("^I go to API (.+) documentation page$")
	public void I_go_to_API_v_documentation_page(String apiVersion) throws Throwable {
		ApiDocsPage apiDocsPage = new ApiDocsPage(testingContext.getDriver());
		apiDocsPage.openApiDocsPage(currentWikiUrl, apiVersion);
		testingContext.setPage(apiDocsPage);
	}

	@Then("^I should see description for \"([^\"]*)\" parameter$")
	public void I_want_to_see_description_for_parameter$(String propertyName) throws Throwable {
		((ApiDocsPage) testingContext.getPage()).waitForParameterDescription(propertyName);
	}

	@Then("^I should see \"([^\"]*)\" in model description$")
	public void I_want_to_see_in_model_description(String parameterName) throws Throwable {
		((ApiDocsPage) testingContext.getPage()).waitForModelPropertyDescription(parameterName);
	}

	@When("^I put valid articleId on current wiki as \"([^\"]*)\"$")
	public void I_put_valid_articleId_on_current_wiki_as(String id) throws Throwable {
		((ApiDocsPage) testingContext.getPage()).putParameter(id, String.valueOf(8736));
	}

	@When("^I click try it out$")
	public void I_click_try_it_out() throws Throwable {
		((ApiDocsPage) testingContext.getPage()).clickTryItOut();
	}

	@Then("^I should see successful response$")
	public void I_want_to_see_successful_response() throws Throwable {
		((ApiDocsPage) testingContext.getPage()).validateResponseCode(200);
	}

}
