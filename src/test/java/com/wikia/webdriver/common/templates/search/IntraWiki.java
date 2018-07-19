package com.wikia.webdriver.common.templates.search;

import static com.wikia.webdriver.common.contentpatterns.URLsContent.COMMUNITY_COUNCIL_WIKI;
import static com.wikia.webdriver.common.contentpatterns.URLsContent.COMMUNITY_WIKI;
import static com.wikia.webdriver.common.contentpatterns.URLsContent.MUPPET_WIKI;

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
    testedWiki = UrlBuilder.createUrlBuilderForWiki(MUPPET_WIKI).getUrl();
    communityWiki = urlBuilder.createUrlBuilderForWikiAndLang(COMMUNITY_WIKI, "en").getUrl();
    searchSuggestionsWiki = UrlBuilder.createUrlBuilderForWikiAndLang(COMMUNITY_COUNCIL_WIKI, "en")
        .getUrl();
  }
}
