package com.wikia.webdriver.common.core.urlbuilder;

import com.google.common.collect.ImmutableMap;

import org.apache.commons.lang3.tuple.Pair;


/**
 * @author drets
 */
public class UrlBuilder {

  final String XIPIO_ADDRESS_FORMAT = ".%s:%d";

  final String XIPIO_DEFAULT_DOMAIN = "127.0.0.1.xip.io";
  final int XIPIO_DEFAULT_PORT = 8000;

  private String browser;
  private String env;

  public UrlBuilder(String env) {
    this.env = env;
  }

  public UrlBuilder(String env, String browser) {
    this.env = env;
    this.browser = browser;
  }

  private ImmutableMap<String, Pair> customWikiNames = ImmutableMap.<String, Pair>builder()
      .put("de.jedipedia", Pair.of("www.jedipedia.de", "dejedipedia"))
      .put("memory-alpha.org", Pair.of("en.memory-alpha.org", "enmemoryalpha"))
      .put("de.memory-alpha", Pair.of("de.memory-alpha.org", "dememoryalpha"))
      .put("yoyo", Pair.of("www.yoyowiki.org", "yoyo"))
      .put("wowwiki", Pair.of("www.wowwiki.com", "wowwiki"))
      .build();


  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = getUrlForWiki(wikiName);
    String separator = wikiName.endsWith("wikia") || wikiName.equals("wowwiki") ? "" : "wiki/";

    if ("CHROMEMOBILE".equalsIgnoreCase(browser)) {
      return appendQueryStringToURL(url, "useskin=wikiamobile");
    }

    return url + separator + wikiPath;
  }

  public String getUrlForWiki(String wikiName) {
    String prefix = getUrlPrefix(wikiName);
    String suffix = getUrlSuffix(wikiName);

    if (customWikiNames.containsKey(wikiName)) {
      if (env.contains("dev")) {
        prefix = "";
        wikiName = (String) customWikiNames.get(wikiName).getRight();
      } else {
        prefix = suffix = "";
        wikiName = (String) customWikiNames.get(wikiName).getLeft();
      }
    }

    return composeUrl(prefix, wikiName, suffix);
  }

  public String appendQueryStringToURL(String url, String qs) {
    String separator = url.contains("?") ? "&" : "?";
    return url + separator + qs;
  }

  private String getUrlPrefix(String wikiName) {
    return wikiName.equals("wikia") ? "www." : "";
  }

  private String getUrlSuffix(String wikiName) {
    if (env.contains("dev") && isMercuryBrowser()) {
      return String.format(XIPIO_ADDRESS_FORMAT, XIPIO_DEFAULT_DOMAIN, XIPIO_DEFAULT_PORT);
    }

    if (env.contains("dev")) {
      String devBoxOwner = env.split("-")[1];
      return "." + devBoxOwner + "." + "wikia-dev.com";
    }

    return wikiName.endsWith("wikia") ? ".com" : ".wikia.com";
  }

  private Boolean isMercuryBrowser() {
    return browser != null && browser.equalsIgnoreCase("CHROMEMOBILEMERCURY");
  }

  private String composeUrl(String prefix, String wikiName, String suffix) {
    if (env != null) {
      if (!env.contains("dev") && !env.equals("prod")) {
        prefix = env + "." + prefix;
      }

      if (env.contains("dev") && !isMercuryBrowser() && wikiName.endsWith("wikia")) {
        wikiName = "wikiaglobal";
      }
    }

    return "http://" + prefix + wikiName + suffix + "/";
  }
}
