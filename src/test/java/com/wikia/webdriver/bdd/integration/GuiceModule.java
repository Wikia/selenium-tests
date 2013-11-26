package com.wikia.webdriver.bdd.integration;

import com.google.inject.AbstractModule;
import com.wikia.webdriver.bdd.context.TestingContext;

public class GuiceModule extends AbstractModule {
	private final TestingContext testingContext;

	public GuiceModule(TestingContext testingContext) {
		this.testingContext = testingContext;
	}

	@Override
	protected void configure() {
		bind(TestingContext.class).toInstance(testingContext);
	}
}
