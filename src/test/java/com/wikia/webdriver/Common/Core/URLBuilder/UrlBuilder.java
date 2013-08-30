package com.wikia.webdriver.Common.Core.URLBuilder;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class UrlBuilder {

	private String env;
	private Boolean isWikia;

	public UrlBuilder(String environment) {
		env = environment;
	}

	public UrlBuilder() {
		env = null;
	}

	public String getUrlForPath(String wikiName, String wikiPath) {
		String url = getUrlForWiki(wikiName);
		if (!(isWikia)) {
			url+= "wiki/";
		}
		url += wikiPath;
		return url;
	}

	public String getUrlForWiki(String wikiName) {
		String suffix;
		isWikia = wikiName.endsWith("wikia");
		String url = "http://";

		if (isWikia) {
			suffix = ".com/";
		} else {
			suffix = ".wikia.com/";
		}

		try {
			if (env.equals("prod")) {
				url += wikiName + suffix;
			} else if (env.equals("preview")) {
				url += "preview." + wikiName + suffix;
			} else if (env.contains("dev")) {
				String devBoxOwner = env.split("-")[1];
				if (isWikia) {
					wikiName = "wikiaglobal";
				}
				url += wikiName + "." + devBoxOwner + "." + "wikia-dev.com/";
			}
		} catch (NullPointerException ex) {
			System.out.println("ENV property is not set!");
		}
		return url;
	}

	public String appendQueryStringToURL(String url, String qs) {
		String temp;
		if (url.contains("?")) {
			temp = url + "&" + qs;
			return temp;
		} else {
			temp = url + "?" + qs;
			return temp;
		}
	}
}
