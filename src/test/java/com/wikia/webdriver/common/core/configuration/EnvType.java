package com.wikia.webdriver.common.core.configuration;

import lombok.Getter;

public enum EnvType {
  PROD("prod", "wikia.com", "fandom.com", "wikia.org"),
  SANDBOX("sandbox", "wikia.com", "fandom.com", "wikia.org"),
  DEV("dev", "wikia-dev.pl", "fandom-dev.pl", "wikia-dev.pl");

  @Getter
  private final String wikiaDomain;

  @Getter
  private final String fandomDomain;

  @Getter
  private final String wikiaOrgDomain;

  @Getter
  private final String key;

  EnvType(String key, String wikiaDomain, String fandomDomain, String wikiaOrgDomain) {
    this.key = key;
    this.wikiaDomain = wikiaDomain;
    this.fandomDomain = fandomDomain;
    this.wikiaOrgDomain = wikiaOrgDomain;
  }

  public String getDomain() {
    if(Configuration.getForceFandomDomain()){
      return fandomDomain;
    }else if(Configuration.getForceWikiOrg()){
      return wikiaOrgDomain;
    }else
      return wikiaDomain;
  }

  public String getDomain(String currentURL) {
    if(currentURL.contains("fandom.com")){
      return fandomDomain;
    }else if(currentURL.contains("wikia.org")){
      return wikiaOrgDomain;
    }else
      return wikiaDomain;
  }
}
