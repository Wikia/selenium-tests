package com.wikia.webdriver.bdd.steps;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.wikia.webdriver.bdd.context.ScenarioContext;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matchers;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import static com.jayway.jsonassert.JsonAssert.with;

public class ApiSteps {
	@Inject
	private ScenarioContext scenarioContext;

	private String responseAsString;
	private int responseCode = 0;
	private Response response;

	private void performQuery(String url, Map<String, String> stringStringMap) throws IOException {
		char joinChar = '?';
		System.out.println(url);
		WebClient client = WebClient.create(url);
		response = client.accept("application/json").get();
		responseAsString = IOUtils.toString((InputStream) response.getEntity());
	}

	@When("^I ask \\\"([^\\\"]*)\\\" api for \\\"([^\\\"]*)/([^\\\"]*)\\\"$")
	public void I_go_to_ask_api_for(String apiVersion, String apiGroup, String apiEndpoint) throws Throwable {
		I_ask_api_for_with_parameters(apiVersion,
				apiGroup,
				apiEndpoint,
				Lists.<Map<String, String>>newArrayList(new HashMap<String, String>()));
	}

	@Then("^I should get list of no more than (\\d+) most recent articles created on wiki$")
	public void I_should_get_list_of_no_more_than_most_recent_articles_created_on_wiki(int count) throws Throwable {
		// items should have ordered
		with(responseAsString).assertThat("$.items[*].id" , new InDescendingOrder());
		with(responseAsString).assertThat("$.items",
				Matchers.hasSize(Matchers.lessThanOrEqualTo(count)));
	}

	@Then("^I see in each result following fields:$")
	public void I_see_in_each_result_following_fields(DataTable table) throws Throwable {
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat("$.items[*]." + fieldName, Matchers.notNullValue());
		}
	}

	@When("^I ask \\\"([^\\\"]*)\\\" api for \\\"([^\\\"]*)/([^\\\"]*)\\\" with parameters:$")
	public void I_ask_api_for_with_parameters(String apiVersion, String apiGroup, String apiEndpoint, List<Map<String,String>> parameters) throws Throwable {
		if ( apiVersion.contains("v1") ) {
			performQuery(scenarioContext.getCurrentWiki().getUrl() + "api/v1/" + apiGroup + "/" + apiEndpoint, parameters.get(0));
		}
	}

	@Then("^I should get list of most recent articles created on wiki$")
	public void I_should_get_list_of_most_recent_articles_in_namespace_created_on_wiki() throws Throwable {
		with(responseAsString).assertThat("$.items[*].id", new InDescendingOrder());
	}

	@Then("^all results should have integer field \"([^\"]*)\" equal to \"([^\"]*)\"$")
	public void all_results_should_have_field_equal_to(String fieldName, int value) throws Throwable {
		with(responseAsString).assertThat("$.items[*]." + fieldName, new AllInSet<Integer>(Sets.newHashSet(value)));
	}

	@Then("^all results should have integer field \"([^\"]*)\" equal to \"([^\"]*)\" or \"([^\"]*)\"$")
	public void all_results_should_have_field_equal_to_or(String fieldName, int value, int value2) throws Throwable {
		with(responseAsString).assertThat("$.items[*]." + fieldName, new AllInSet<Integer>(Sets.newHashSet(value, value2)));
	}

	public static class InDescendingOrder extends BaseMatcher {

		@Override
		public boolean matches(Object o) {
			Iterator<Comparable> iterator = ((Iterable<Comparable>) o).iterator();
			if ( ! iterator.hasNext() ) {
				return true;
			}
			Comparable prev = iterator.next();
			while ( iterator.hasNext() ) {
				Comparable cur = iterator.next();
				if ( cur.compareTo(prev) > 0 ) {
					return false;
				}
				prev = cur;
			}
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("values needs to be ordered");
		}
	}

	public static class AllInSet<T> extends BaseMatcher {
		private final Set<T> allowedElements;

		public AllInSet(Set<T> allowedElements) {
			this.allowedElements = allowedElements;
		}

		@Override
		public boolean matches(Object o) {
			Iterable<T> iterable = ((Iterable<T>) o);

			for( T element :iterable ) {
				if ( !allowedElements.contains(element) ) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("All values needs to be in set");
		}
	}
}
