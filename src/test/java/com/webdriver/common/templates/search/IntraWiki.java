package com.webdriver.common.templates.search;

import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.url.UrlBuilder;
import com.webdriver.common.templates.NewTestTemplate;
import com.webdriver.common.properties.Credentials;

public class IntraWiki extends NewTestTemplate {

  protected static final String SEARCH_SUGGESTION_PHRASE = "Gon";
  protected static final String SEARCH_ARTICLE = "Gonzo";


  protected Credentials credentials = Configuration.getCredentials();
  protected String testedWiki;
  protected String communityWiki;
  protected String searchSuggestionsWiki;

  public IntraWiki() {
    UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv(), Configuration.getForceHttps(), Configuration.getNewStagingUrlFormat());
    testedWiki = urlBuilder.getUrlForWiki("muppet");
    communityWiki = urlBuilder.getUrlForWiki("community");
    searchSuggestionsWiki = urlBuilder.getUrlForWiki("communitycouncil");
  }
}
