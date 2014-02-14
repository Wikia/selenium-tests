package com.wikia.webdriver.bdd.matchers;

import com.jayway.jsonassert.JsonAssert;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Iterator;
import java.util.Set;

public class CollectionMatchers {
	public static Matcher allShould(Matcher matcher) {
		return new AllShouldMatcher(matcher);
	}

	public static Matcher inDescendingOrder() {
		return new InDescendingOrder();
	}

	public static <E> Matcher allValuesShouldBeInSet(Set<E> values) {
		return new AllInSet<E>(values);
	}

	public static Matcher allValuesShouldBeGreaterThanOrEqualTo(int value) {
		return CollectionMatchers.allShould(Matchers.greaterThanOrEqualTo(value));
	}

	public static Matcher allValuesShouldBeEqualTo(String value) {
		return CollectionMatchers.allShould(Matchers.equalTo(value));
	}

	public static Matcher allElementsShouldContainField(String fieldName) {
		return CollectionMatchers.allShould(JsonAssert.mapContainingKey(Matchers.equalTo(fieldName)));
	}

	public static Matcher allElementsShouldContainNotEmptyField() {
		return CollectionMatchers.allShould(Matchers.notNullValue());
	}

	public static Matcher allElementsShouldContainLimitedLengthField(int limit) {
		return CollectionMatchers.allShould( new StringLengthMatcher( limit ) );
	}

	public static class StringLengthMatcher extends BaseMatcher{
		private final int Limit;

		public StringLengthMatcher(int Limit)
		{
			this.Limit = Limit;
		}

		@Override
		public boolean matches( Object o ) {
			return (o instanceof String )   &&  ( (String) o ).length() <= Limit;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("String length should be less or equal to:" + Limit );
		}

	}

	public static class AllShouldMatcher extends BaseMatcher {
		private final Matcher matcher;

		public AllShouldMatcher(Matcher matcher) {
			this.matcher = matcher;
		}

		@Override
		public boolean matches(Object o) {
			if ( o instanceof Iterable ) {
				Iterable iterable = (Iterable) o;
				for( Object element: iterable ) {
					if ( !matcher.matches(element) ) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("All elements should follow:" + matcher );
		}
	}

	public static class InDescendingOrder extends BaseMatcher<Iterable<Comparable>> {
		@Override
		public boolean matches(Object o) {
			Iterator<Comparable> iterator = ((Iterable<Comparable>) o).iterator();
			if ( ! iterator.hasNext() ) {
				return true;
			}
			Comparable prev = iterator.next();
			while ( iterator.hasNext() ) {
				Comparable cur = iterator.next();
				if ( cur.compareTo(prev) > 0 ) {
					return false;
				}
				prev = cur;
			}
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("values needs to be in descending order.");
		}
	}

	public static class AllInSet<T> extends BaseMatcher {
		private final Set<T> allowedElements;

		public AllInSet(Set<T> allowedElements) {
			this.allowedElements = allowedElements;
		}

		@Override
		public boolean matches(Object o) {
			Iterable<T> iterable = (Iterable<T>) o;

			for( T element :iterable ) {
				if ( !allowedElements.contains(element) ) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("All values needs to be in set");
		}
	}
}
