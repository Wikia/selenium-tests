package com.wikia.webdriver.common.templates.search;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;

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
