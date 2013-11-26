package com.wikia.webdriver.bdd.integration;

import com.google.common.collect.Lists;
import cucumber.runtime.*;
import cucumber.runtime.Runtime;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.java.JavaBackend;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.snippets.SummaryPrinter;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes features against step defs in given packages.
 * Step definitions will be located using injected classloader.
 */
public class FeatureExecutorImpl implements FeatureExecutor {
	/**
	 * ClassLoader for to load step definitions.
	 */
	private final ClassLoader classLoader;
	/**
	 * Package with step definitions
	 */
	private final String stepPackage;

	public FeatureExecutorImpl(ClassLoader classLoader, String stepPackage) {
		this.classLoader = classLoader;
		this.stepPackage = stepPackage;
	}

	/**
	 * Executes provided feature against found step definitions. Asserts that there where no failures.
	 * @param cucumberFeature Feature to execute
	 */
	@Override
	public void run(CucumberFeature cucumberFeature) {
		RuntimeOptions runtimeOptions = new RuntimeOptions(new Env("cucumber-jvm", System.getProperties()), buildParameters());

		ResourceLoader resourceLoader = new MultiLoader(classLoader);
		ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
		cucumber.runtime.Runtime runtime = new Runtime(
				resourceLoader,
				classLoader,
				Lists.newArrayList((Backend) new JavaBackend(new GuiceObjectFactory(), classFinder)),
				runtimeOptions);

		Formatter formatter = runtimeOptions.formatter(classLoader);
		Reporter reporter = runtimeOptions.reporter(classLoader);

		try {
			runtime.writeStepdefsJson();
		} catch (IOException e) {
			Assert.fail("Failed to write step defs.", e);
		}
		cucumberFeature.run(formatter, reporter, runtime);

		formatter.done();
		formatter.close();
		new SummaryPrinter(System.out).print(runtime);

		if (runtime.exitStatus() != 0) {
			Assert.fail("At least one scenario failed.");
		}
	}

	/**
	 * Returns a set of parameters in cli form for Runtime
	 * @return Argv style param list.
	 */
	protected String[] buildParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.add("--format");
		parameters.add("pretty");

		parameters.add("--glue");
		parameters.add(MultiLoader.CLASSPATH_SCHEME + stepPackage);

		return parameters.toArray(new String[parameters.size()]);
	}
}

