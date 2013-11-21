package com.wikia.webdriver.bdd.integration;

import cucumber.runtime.FeatureBuilder;
import cucumber.runtime.io.Resource;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.model.CucumberFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Searches for feature files, parses them and returns in form of CucumberFeature objects.
 * Uses resourceLoader to locate features.
 */
public class FeatureProviderImpl implements FeatureProvider {
	private final ResourceLoader resourceLoader;
	private String path = "";
	private String suffix = ".feature";

	public FeatureProviderImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<CucumberFeature> getFeatures() {
		Iterable<Resource> resources = resourceLoader.resources(getPath(), getSuffix());
		List<CucumberFeature> features = new ArrayList<CucumberFeature>();
		FeatureBuilder featureBuilder = new FeatureBuilder(features);
		for( Resource resource: resources ) {
			featureBuilder.parse(resource, new ArrayList<Object>());
		}
		return features;
	}

	public String getPath() {
		return path;
	}

	@SuppressWarnings("unused")
	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffix() {
		return suffix;
	}

	@SuppressWarnings("unused")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
