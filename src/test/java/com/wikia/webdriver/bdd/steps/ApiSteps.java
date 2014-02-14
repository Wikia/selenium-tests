package com.wikia.webdriver.bdd.steps;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.bdd.context.ScenarioContext;
import com.wikia.webdriver.bdd.matchers.CollectionMatchers;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.hamcrest.Matchers;
import org.testng.Assert;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.with;

public class ApiSteps {
	@Inject
	private ScenarioContext scenarioContext;

	private String responseAsString;
	private Response response;

	private void performQuery(String url, List<ApiParameter> parameters) throws IOException {
		char joinChar = '?';
		for ( ApiParameter apiParameter: parameters ) {
			url += joinChar + apiParameter.getKey() + "=" + URLEncoder.encode(apiParameter.getValue(), "utf8");
			joinChar = '&';
		}
		System.out.println(url);
		WebClient client = WebClient.create(url);
		response = client.accept("application/json").get();
		responseAsString = IOUtils.toString((InputStream) response.getEntity());
	}

	@When("^I ask \\\"([^\\\"]*)\\\" api for \\\"([^\\\"]*)/([^\\\"]*)\\\"$")
	public void I_go_to_ask_api_for(String apiVersion, String apiGroup, String apiEndpoint) throws Throwable {
		I_ask_api_for_with_parameters(apiVersion,
				apiGroup,
				apiEndpoint);
	}

    @Then("^I should get list of \\\"([^\\\"]*)\\\" in descending or equal order$")
    public void I_should_get_list_of_in_descended_order(String fieldName) throws Throwable {

        List<Map<String,Object>> items = JsonPath.read( responseAsString, "$.items[*]" );
        int prevValue = 0;
        int currValue = 0;
        for( Map<String,Object> item: items ) {
            Assert.assertTrue( item.containsKey( fieldName ) );
            if ( item.get( fieldName ) != null ) {
                currValue = Integer.parseInt( item.get( fieldName ).toString() );
                if ( prevValue == 0 ) {
                    prevValue = currValue;
                }
                Assert.assertTrue( currValue <= prevValue );
                prevValue = currValue;
            }
        }
    }

	@Then("^I should get list of no more than (\\d+) most recent articles created on wiki$")
	public void I_should_get_list_of_no_more_than_most_recent_articles_created_on_wiki(int count) throws Throwable {
		// items should have ordered
		with(responseAsString).assertThat("$.items[*].id" , CollectionMatchers.inDescendingOrder());
		with(responseAsString).assertThat("$.items",
				Matchers.hasSize(Matchers.lessThanOrEqualTo(count)));
	}

	@Then("^I see in each result following fields:$")
	public void I_see_in_each_result_following_fields(DataTable table) throws Throwable {
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat("$.items[*]", CollectionMatchers.allElementsShouldContainField(fieldName));
		}
	}

	@Then("^I see in each result following fields not empty:$")
	public void I_see_in_each_result_following_fields_not_empty(DataTable table) throws Throwable {
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat("$.items[*]", CollectionMatchers.allElementsShouldContainField(fieldName));
			with(responseAsString).assertThat(String.format("$.items[*].%s" , fieldName),
					CollectionMatchers.allElementsShouldContainNotEmptyField());
		}
	}

	@Then("^I see in each result \\\"([^\\\"]*)\\\" object with following fields:$")
	public void I_see_in_each_result_object_with_following_fields(String object, DataTable table) {
		with(responseAsString).assertThat("$.items[*]", CollectionMatchers.allElementsShouldContainField(object));
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat(String.format("$.items[*].%s" , object),
					CollectionMatchers.allElementsShouldContainField(fieldName));
		}
	}

	@Then("^I see in each result \\\"([^\\\"]*)\\\" object with following fields not empty:$")
	public void I_see_in_each_result_object_with_following_fields_not_empty(String object, DataTable table) {
		with(responseAsString).assertThat("$.items[*]", CollectionMatchers.allElementsShouldContainField(object));
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat(String.format("$.items[*].%s" , object),
					CollectionMatchers.allElementsShouldContainField(fieldName));
			with(responseAsString).assertThat(String.format("$.items[*].%s.%s" , object, fieldName),
					CollectionMatchers.allElementsShouldContainNotEmptyField());
		}
	}

	@Then("^Together with \\\"([^\\\"]*)\\\" I see in each result \\\"([^\\\"]*)\\\" object with following fields:$")
	public void I_see_in_each_result_object_with_following_fields(String mandatoryField, String object, DataTable table) {
		List<Map<String,Object>> items = JsonPath.read( responseAsString, "$.items[*]" );
		for( Map<String,Object> item: items ) {
			Assert.assertTrue( item.containsKey( mandatoryField ) );
			if ( item.get( mandatoryField ) != null ) {
				Map<String,Object> og = (Map<String, Object>) item.get(object);
				Assert.assertNotNull(og);
				for ( String fieldName: table.<String>asList(String.class) ) {
					Assert.assertNotNull(og.get(fieldName));
				}
			}
		}

	}

	@When("^I ask \\\"([^\\\"]*)\\\" api for \\\"([^\\\"]*)/([^\\\"]*)\\\" with parameters:$")
	public void I_ask_api_for_with_parameters(String apiVersion, String apiGroup, String apiEndpoint, List<ApiParameter> parameters) throws Throwable {
		if ( apiVersion.contains("v1") ) {
			performQuery(scenarioContext.getCurrentWiki().getUrl() + "api/v1/" + apiGroup + "/" + apiEndpoint, parameters);
		}
	}

	public void I_ask_api_for_with_parameters(String apiVersion, String apiGroup, String apiEndpoint) throws Throwable {
		I_ask_api_for_with_parameters(apiVersion, apiGroup, apiEndpoint, new ArrayList());
	}

	@Then("^I should get list of most recent articles created on wiki$")
	public void I_should_get_list_of_most_recent_articles_in_namespace_created_on_wiki() throws Throwable {
		with(responseAsString).assertThat("$.items[*].id", CollectionMatchers.inDescendingOrder());
	}

	@Then("^all results should have integer field \"([^\"]*)\" equal to \"([^\"]*)\"$")
	public void all_results_should_have_field_equal_to(String fieldName, int value) throws Throwable {
		with(responseAsString).assertThat("$.items[*]." + fieldName, CollectionMatchers.allValuesShouldBeInSet(Sets.newHashSet(value)));
	}

	@Then("^all elements in \"([^\"]*)\" array should have field \"([^\"]*)\" equal to or greater than \"([^\"]*)\"$")
	public void all_results_should_have_field_equal_to(String object, String field, int value) throws Throwable {
		with(responseAsString).assertThat(String.format("$.%s[*].%s" , object, field),
												 CollectionMatchers.allValuesShouldBeGreaterThanOrEqualTo(value));
	}

	@Then("^all elements in \"([^\"]*)\" array should have field \"([^\"]*)\" equal to \"([^\"]*)\"$")
	public void all_results_should_have_field_equal(String object, String field, String value) throws Throwable {
		with(responseAsString).assertThat(String.format("$.%s[*].%s" , object, field),
												 CollectionMatchers.allValuesShouldBeEqualTo(value));
	}

	@Then("^all results should have integer field \"([^\"]*)\" equal to \"([^\"]*)\" or \"([^\"]*)\"$")
	public void all_results_should_have_field_equal_to_or(String fieldName, int value, int value2) throws Throwable {
		with(responseAsString).assertThat("$.items[*]." + fieldName,
												 CollectionMatchers.allValuesShouldBeInSet(Sets.newHashSet(value, value2)));
	}

	@Then( "^I should get \"([^\"]*)\" object with no more than (\\d+) characters$" )
	public void I_should_get_object_with_no_more_than_characters( String object, int limit ) throws Throwable {
		with(responseAsString).assertThat("$.items[*]", CollectionMatchers.allElementsShouldContainField(object));
		with(responseAsString).assertThat(String.format("$.items[*].%s" , object),
					CollectionMatchers.allElementsShouldContainLimitedLengthField(limit));
	}

	@Then( "^I should get list of \"([^\"]*)\"$" )
	public void I_should_get_object_with_articles_array(String object) throws Throwable {
		with(responseAsString).assertNotNull("$." + object + "[*]");
	}

	@Then( "^I see \"([^\"]*)\" array with each element having following fields not empty:$" )
	public void all_results_in_array_should_have_fields(String object, DataTable table) {
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat(String.format("$.%s[*]" , object),
													 CollectionMatchers.allElementsShouldContainField(fieldName));
			with(responseAsString).assertThat(String.format("$.%s[*].%s", object, fieldName),
													 CollectionMatchers.allElementsShouldContainNotEmptyField());
		}
	}

	@Then( "^I see \"([^\"]*)\" array with \"([^\"]*)\" array field with each element having following fields not empty:$" )
	public void all_results_in_array_should_have_fields(String object, String subObject, DataTable table) {
		for ( String fieldName: table.<String>asList(String.class) ) {
			with(responseAsString).assertThat(String.format("$.%s[*].%s" , object, subObject),
													 CollectionMatchers.allElementsShouldContainField(fieldName));
			with(responseAsString).assertThat(String.format("$.%s[*].%s[*].%s", object, subObject, fieldName),
													 CollectionMatchers.allElementsShouldContainNotEmptyField());
		}
	}

	public class ApiParameter {
		private String key;
		private String value;

		public ApiParameter(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}


}
