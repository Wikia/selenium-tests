package com.wikia.webdriver.common.templates.search;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;

public class IntraWiki extends NewTestTemplate {

    protected Credentials credentials = config.getCredentials();

    protected String testedWiki;
    protected String communityWiki;
    protected String searchSuggestionsWiki;

    public IntraWiki() {
        UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
        testedWiki = urlBuilder.getUrlForWiki("muppet");
        communityWiki = urlBuilder.getUrlForWiki("community");
        searchSuggestionsWiki = urlBuilder.getUrlForWiki("communitycouncil");
    }

    protected static final int RESULTS_PER_PAGE = 25;
    protected static final String SEARCH_PHRASE_RESULTS = "A";
    protected static final String SEARCH_PAGINATION_RESULTS = "what";
    protected static final String SEARCH_RESULT_WITH_EXTENSION = "betweenlions";
    protected static final String SEARCH_PHRASE_NO_RESULTS = "qazwsxedcrfvtgb";
    protected static final String SEARCH_SUGGESTION_PHRASE = "Gon";
    protected static final String SEARCH_ARTICLE = "Gonzo";
    protected static final String SEARCH_WIKI = "Marvel";
}
