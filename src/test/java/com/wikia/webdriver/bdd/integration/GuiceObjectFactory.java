package com.wikia.webdriver.bdd.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wikia.webdriver.bdd.context.TestingContext;
import com.wikia.webdriver.bdd.context.TestingContextImpl;
import cucumber.runtime.java.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class GuiceObjectFactory implements ObjectFactory {
	private Injector injector;
	private TestingContext testingContext;
	private final Map<Class, Object> cache = new HashMap<Class, Object>();

	@Override
	public void start() {
		testingContext = new TestingContextImpl() {{ init(); }};
		injector = Guice.createInjector(new GuiceModule(testingContext));
	}

	@Override
	public void stop() {
		testingContext.close();
		cache.clear();
	}

	@Override
	public void addClass(Class<?> aClass) {
	}

	@Override
	public <T> T getInstance(Class<T> type) {
		if ( !cache.containsKey(type) ) {
			cache.put(type, injector.getInstance(type));
		}
		return type.cast(cache.get(type));
	}
}
