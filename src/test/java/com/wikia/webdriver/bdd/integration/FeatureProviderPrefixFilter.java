package com.wikia.webdriver.bdd.integration;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import cucumber.runtime.model.CucumberFeature;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.List;

/**
 * Filters features by filename prefix.
 */
public class FeatureProviderPrefixFilter implements FeatureProvider {
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
		/**
		 * Checks if Feature filename starts with given prefix
		 * @param cucumberFeature feature to filter by prefix.
		 * @return true if filename of this feature starts with prefix
		 */
		@Override
		public boolean apply(@Nullable CucumberFeature cucumberFeature) {
			if ( cucumberFeature != null ) {
				URI uri = URI.create(cucumberFeature.getUri());
				String[] split = uri.toString().split("/");
				if ( split.length >= 1 ) {
					String file = split[split.length-1];
					if ( file != null && file.toLowerCase().startsWith(prefix.toLowerCase()) ) {
						return true;
					}
				}
			}
			return false;
		}
	}
}
