package com.wikia.webdriver.bdd.integration;

import cucumber.runtime.model.CucumberFeature;

import java.util.List;

/**
 * Feature locator.
 * @see FeatureProviderImpl
 */
public interface FeatureProvider {
	/**
	 * Provides features.
	 * @return List of features.
	 */
	List<CucumberFeature> getFeatures();
}
