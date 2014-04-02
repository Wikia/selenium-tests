package com.wikia.webdriver.Common.Clicktracking;

import org.mvel2.util.Make.Map;

public class ExpectedEvent extends Map<EventParameter[], String[]> {

	public ExpectedEvent(EventParameter[] lista, String[] lista2) {
		for (int i = 0; i < lista2.length; i++) {
			this.put(lista[i], lista2[i]);
		}
	}

}
