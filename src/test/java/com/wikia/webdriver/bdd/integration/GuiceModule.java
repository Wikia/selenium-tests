package com.wikia.webdriver.bdd.integration;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.wikia.webdriver.bdd.context.ScenarioContext;
import com.wikia.webdriver.bdd.context.ScenarioContextImpl;
import com.wikia.webdriver.bdd.context.TestingContext;
import com.wikia.webdriver.bdd.context.TestingContextImpl;

import java.util.ArrayList;
import java.util.List;

public class GuiceModule extends AbstractModule {
	private final List<SingletonProvider> providerList = new ArrayList<SingletonProvider>();

	public GuiceModule() {

		providerList.add(new SingletonProvider<TestingContext>(TestingContext.class) {
			@Override
			protected TestingContext build() {
				return new TestingContextImpl() {{ init(); }};
			}

			@Override
			protected void destroy(TestingContext cached) {
				cached.close();
			}
		});

		providerList.add(new SingletonProvider<ScenarioContext>(ScenarioContext.class) {
			@Override
			protected ScenarioContext build() {
				return new ScenarioContextImpl();
			}

			@Override
			protected void destroy(ScenarioContext cached) {
			}
		});

	}

	@Override
	protected void configure() {
		for ( SingletonProvider provider: providerList ){
			bind(provider.bindType).toProvider(provider);
		}
	}

	public void destroy() {
		for ( SingletonProvider provider: providerList ) {
			provider.destroy();
		}
	}

	private static abstract class SingletonProvider<T> implements Provider<T> {
		private T cached = null;
		protected final Class<T> bindType;

		private SingletonProvider(Class<T> bindType) {
			this.bindType = bindType;
		}

		@Override
		public T get() {
			if ( cached == null ) {
				cached = build();
			}
			return cached;
		}

		public final void destroy() {
			if ( cached != null ) {
				destroy(cached);
			}
		}

		protected abstract T build();
		protected abstract void destroy(T cached);
	}
}
