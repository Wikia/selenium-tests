package com.wikia.webdriver.common.core.configuration;

import lombok.Getter;

public enum EnvType {
  PROD("prod", "wikia.com", "fandom.com"),
  SANDBOX("sandbox", "wikia.com", "fandom.com"),
  DEV("dev", "wikia-dev.pl", "fandom-dev.pl");

  @Getter
  private final String wikiaDomain;

  private final String fandomDomain;

  @Getter
  private final String key;

  EnvType(String key, String wikiaDomain, String fandomDomain) {
    this.key = key;
    this.wikiaDomain = wikiaDomain;
    this.fandomDomain = fandomDomain;
  }

  public String getDomain() {
    return Configuration.getForceFandomDomain() ? fandomDomain : wikiaDomain;
  }
}
