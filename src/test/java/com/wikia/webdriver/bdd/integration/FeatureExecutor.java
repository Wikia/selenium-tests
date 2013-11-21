package com.wikia.webdriver.bdd.integration;

import cucumber.runtime.model.CucumberFeature;

public interface FeatureExecutor {
	void run(CucumberFeature cucumberFeature);
}
