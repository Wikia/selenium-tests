package com.wikia.webdriver.common.core.urlbuilder;

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
		String prefix;
		String suffix;
		isWikia = wikiName.endsWith("wikia");
		String url = "http://";

		if (isWikia) {
			//If wikia.com than append www
			if (wikiName.equals("wikia")) {
				prefix = "www.";
			} else {
				//for different languages, for example de.wikia, don't prepend www
				prefix = "";
			}
			suffix = ".com/";
		} else {
			prefix = "";
			suffix = ".wikia.com/";
		}

		try {
			if (env.equals("prod")) {
				url += prefix + wikiName + suffix;
			} else if (env.equals("preview") || env.contains("sandbox")) {
				url += env + "." + prefix + wikiName + suffix;
			} else if (env.contains("dev")) {
				String devBoxOwner = env.split("-")[1];
				if (isWikia) {
					wikiName = "wikiaglobal";
				}
				url += wikiName + "." + devBoxOwner + "." + "wikia-dev.com/";
			} else if (env != null) {
				url += env + "." + prefix + wikiName + suffix;
			}
		} catch (NullPointerException ex) {
			throw new RuntimeException("ENV property is not set!");
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

	public String removeQueryStringsFromURL(String url) {
		if (url.contains("?")) {
			return url.substring(0, url.indexOf("?"));
		}
		return url;
	}

	public String removeProtocolServerNameFromUrl(String url) {
		return url.substring(url.indexOf('.') + 1, url.length());
	}
}
