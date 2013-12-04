package com.wikia.webdriver.bdd.steps;

import com.google.inject.Inject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.bdd.context.ScenarioContext;
import com.wikia.webdriver.bdd.context.TestingContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonSteps {
	@Inject
	private TestingContext testingContext;

	@Inject
	private ScenarioContext scenarioContext;

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
		scenarioContext.setCurrentWiki( testingContext.getWiki() );
	}
}
