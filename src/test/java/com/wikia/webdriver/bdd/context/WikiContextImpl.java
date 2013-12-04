package com.wikia.webdriver.bdd.context;

public class WikiContextImpl implements WikiContext {
	private String wikiName;
	private String url;
	private Long exampleArticleId;

	public String getWikiName() {
		return wikiName;
	}

	public void setWikiName(String wikiName) {
		this.wikiName = wikiName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getExampleArticleId() {
		return exampleArticleId;
	}

	public void setExampleArticleId(Long exampleArticleId) {
		this.exampleArticleId = exampleArticleId;
	}
}
