package com.wikia.webdriver.bdd;

import com.google.common.collect.Lists;
import com.wikia.webdriver.bdd.integration.GuiceObjectFactory;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.Env;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.java.JavaBackend;

import java.io.IOException;

public class Main {
	public static void main(String[] argv) throws Throwable {
		run(argv, Thread.currentThread().getContextClassLoader());
	}

	public static void run(String[] argv, ClassLoader classLoader) throws IOException {
		RuntimeOptions runtimeOptions = new RuntimeOptions(new Env("cucumber-jvm"), argv);

		ResourceLoader resourceLoader = new MultiLoader(classLoader);
		ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
		cucumber.runtime.Runtime runtime = new Runtime(
				resourceLoader,
				classLoader,
				Lists.newArrayList(new JavaBackend(new GuiceObjectFactory(), classFinder)),
				runtimeOptions);
		runtime.writeStepdefsJson();
		runtime.run();
		System.exit(runtime.exitStatus());
	}
}
