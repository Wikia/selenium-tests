package com.wikia.webdriver.bdd.integration;

import cucumber.api.CucumberOptions;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CucumberTestImpl implements ITest {
	private final Class clazz;

	public CucumberTestImpl(Class clazz) {
		 this.clazz = clazz;
	}

	@Override
	public String getTestName() {
		return "Scenarios";
	}

	@Test
	public void execute() throws IOException {
		ClassLoader classLoader = clazz.getClassLoader();

		//noinspection unchecked
		RuntimeOptionsFactory runtimeOptionsFactory =
				new RuntimeOptionsFactory(clazz, new Class[]{CucumberOptions.class});
		RuntimeOptions runtimeOptions = runtimeOptionsFactory.create();

		ResourceLoader resourceLoader = new MultiLoader(classLoader);
		ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
		Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);

		runtime.writeStepdefsJson();
		runtime.run();

		if (!runtime.getErrors().isEmpty()) {
			Assert.fail("At least one scenario failed.");
		}
	}
}
