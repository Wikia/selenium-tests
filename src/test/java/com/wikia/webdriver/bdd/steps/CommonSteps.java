package com.wikia.webdriver.bdd.steps;

import com.wikia.webdriver.Common.Core.Configuration.AbstractConfiguration;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CommonSteps {
	private WebDriver driver;
	private AbstractConfiguration config;
	@SuppressWarnings("unused")
	private String wikiURL;
	@SuppressWarnings("unused")
	private String wikiCorporateURL;
	private WikiBasePageObject page;

	@Before
	public void before() {
		Properties.setProperties();
		config = ConfigurationFactory.getConfig();
		EventFiringWebDriver eventDriver = NewDriverProvider.getDriverInstanceForBrowser(
				config.getBrowser()
		);
		eventDriver.register(new PageObjectLogging());
		driver = eventDriver;
		prepareURLs();
	}

	private void prepareURLs() {
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		wikiURL = urlBuilder.getUrlForWiki(config.getWikiName());
		wikiCorporateURL = urlBuilder.getUrlForWiki("wikia");
	}

	@After
	public void after() {
		driver.close();
	}

	@Given("I am on wikia global")
	public void I_am_on_wikia_global() throws Throwable {
		HomePageObject homePageObject = new HomePageObject(driver);
		homePageObject.openHomePage();
		page = homePageObject;
	}

	@Then("^I should see \"([^\"]*)\"$")
	public void I_see(String phrase) throws Throwable {
		page.waitForText(phrase);
	}
	@When("^I search for \"([^\"]*)\"$")
	public void I_search_for(String phrase) throws Throwable {
		page = ((HomePageObject) page).searchFor(phrase);
	}

	@Then("^I see following search results first:$")
	public void I_see_following_search_results_first(DataTable searchResultTable) throws Throwable {
		((CrossWikiSearchPageObject) page).verifyTopResultsAre(searchResultTable.<String>asList(String.class));
	}

	@When("^I click \"([^\"]*)\"$")
	public void I_click(String buttonOrLinkText) throws Throwable {
		page.clickLink(buttonOrLinkText);
	}

	@Given("^I am on \"([^\"]*)\"$")
	public void I_am_on(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@When("^I go to API v(\\d+) documentation page$")
	public void I_go_to_API_v_documentation_page(int arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^I want to see \"([^\"]*)\"$")
	public void I_want_to_see(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^I want to see description for \"([^\"]*)\" property$")
	public void I_want_to_see_description_for_property(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^I want to see \"([^\"]*)\" in model description$")
	public void I_want_to_see_in_model_description(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@When("^I put valid articleId on current wiki as \"([^\"]*)\"$")
	public void I_put_valid_articleId_on_current_wiki_as(String arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

	@Then("^I want to see successful response$")
	public void I_want_to_see_successful_response() throws Throwable {
		// Express the Regexp above with the code you wish you had
		throw new PendingException();
	}

}
