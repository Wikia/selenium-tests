package com.wikia.webdriver.bdd.context;

public class ScenarioContextImpl implements ScenarioContext {
	private WikiContext wikiContext;

	@Override
	public WikiContext getCurrentWiki() {
		return wikiContext;
	}

	@Override
	public void setCurrentWiki(WikiContext wikiContext) {
		this.wikiContext = wikiContext;
	}
}
