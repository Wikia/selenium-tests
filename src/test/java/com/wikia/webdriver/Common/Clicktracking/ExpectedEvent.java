package com.wikia.webdriver.Common.Clicktracking;

import java.util.HashMap;

public class ExpectedEvent extends HashMap<EventParameter, String> {

	public ExpectedEvent(EventParameter[] keys, String[] values) {
		for (int i = 0; i < values.length; i++) {
			this.put(keys[i], values[i]);
		}
	}

}
