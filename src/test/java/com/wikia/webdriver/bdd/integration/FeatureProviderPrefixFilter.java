package com.wikia.webdriver.bdd.integration;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import cucumber.runtime.model.CucumberFeature;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

public class FeatureProviderPrefixFilter implements FeatureProvider {
	private static final Logger logger = LoggerFactory.getLogger(FeatureProviderPrefixFilter.class);
	private final FeatureProvider provider;
	private final String prefix;

	public FeatureProviderPrefixFilter(FeatureProvider provider, String prefix) {
		this.provider = provider;
		this.prefix = prefix;
	}

	@Override
	public List<CucumberFeature> getFeatures() {
		return Lists.newArrayList(Iterables.filter(provider.getFeatures(), new CucumberFeaturePredicate()));
	}

	private class CucumberFeaturePredicate implements Predicate<CucumberFeature> {
		@Override
		public boolean apply(@Nullable CucumberFeature cucumberFeature) {
			if ( cucumberFeature != null ) {
				URI uri = URI.create(cucumberFeature.getUri());
				String[] split = uri.toString().split("/");
				String file = split[split.length-1];
				if ( file != null && file.toLowerCase().startsWith(prefix.toLowerCase()) ) {
					return true;
				}
			}
			return false;
		}
	}
}
