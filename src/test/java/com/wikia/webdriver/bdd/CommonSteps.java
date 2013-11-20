package com.wikia.webdriver.bdd;

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
import cucumber.api.java.en.*;
import cucumber.api.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonSteps {
	private static Logger logger = LoggerFactory.getLogger(CommonSteps.class);

	private WebDriver driver;
	private AbstractConfiguration config;
	private String wikiURL;
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

}
