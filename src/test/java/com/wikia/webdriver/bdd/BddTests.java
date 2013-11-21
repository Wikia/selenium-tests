package com.wikia.webdriver.bdd;

import com.wikia.webdriver.bdd.integration.FeatureExecutor;
import com.wikia.webdriver.bdd.integration.FeatureExecutorImpl;
import com.wikia.webdriver.bdd.integration.FeatureProviderImpl;
import cucumber.runtime.io.ClasspathResourceLoader;
import cucumber.runtime.model.CucumberFeature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BddTests {
	@DataProvider(name = "BddFeatures")
	public Object[][] create() throws Throwable {
		ClassLoader classLoader = BddTests.class.getClassLoader();
		FeatureExecutor executor = new FeatureExecutorImpl(classLoader, "com.wikia.webdriver.bdd.steps");
		FeatureProviderImpl featureProvider = new FeatureProviderImpl(new ClasspathResourceLoader(classLoader));

		List<Object[]> featureList = new ArrayList<Object[]>();
		for (CucumberFeature feature: featureProvider.getFeatures()) {
			featureList.add(new Object[]{ feature.getGherkinFeature().getName(),feature, executor });
		}
		return featureList.toArray(new Object[featureList.size()][]);
	}

	@Test(dataProvider = "BddFeatures")
	public void testFeature(@SuppressWarnings("unused") String name, CucumberFeature feature, FeatureExecutor executor) {
		executor.run(feature);
	}
}
