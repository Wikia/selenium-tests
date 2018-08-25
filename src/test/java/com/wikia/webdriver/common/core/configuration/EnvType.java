package com.wikia.webdriver.common.core.configuration;

import lombok.Getter;

public enum EnvType {
	PROD("wikia.com", "prod"),
	DEV("wikia-dev.pl", "dev"),
	SANDBOX("wikia.com", "sandbox");

	@Getter
	private final String wikiaDomain;

	@Getter
	private final String key;

	EnvType(String wikiaDomain, String key) {
		this.wikiaDomain = wikiaDomain;
		this.key = key;
	}
}
