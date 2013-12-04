package com.wikia.webdriver.bdd.context;

public interface ScenarioContext {
	WikiContext getCurrentWiki();
	void setCurrentWiki(WikiContext wikiContext);
}
