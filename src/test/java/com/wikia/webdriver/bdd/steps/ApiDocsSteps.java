package com.wikia.webdriver.bdd.steps;

import com.google.inject.Inject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs.ApiDocsPage;
import com.wikia.webdriver.bdd.context.TestingContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApiDocsSteps {
	@Inject
	private TestingContext testingContext;

	@When("^I go to API (.+) documentation page$")
	public void I_go_to_API_v_documentation_page(String apiVersion) throws Throwable {
		ApiDocsPage apiDocsPage = new ApiDocsPage(testingContext.getDriver());
		apiDocsPage.openApiDocsPage(CommonSteps.currentWikiUrl, apiVersion);
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

	@Then("^I should see following fields in model description:$")
	public void I_should_see_following_fields_in_model_description(DataTable listOfFields) throws Throwable {
		for ( String field: listOfFields.<String>asList(String.class) ) {
			I_want_to_see_in_model_description(field);
		}
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
