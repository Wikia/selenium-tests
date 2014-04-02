package com.wikia.webdriver.Common.Clicktracking.Events;

import com.wikia.webdriver.Common.Clicktracking.EventParameter;
import com.wikia.webdriver.Common.Clicktracking.ExpectedEvent;

public class EventsSearchResultPage {

	private static EventParameter[] lista = new EventParameter[]{EventParameter.action, EventParameter.category};
	private static String[] lista2 = new String[]{"asd", "Asd"};

	public static ExpectedEvent pushToTop = new ExpectedEvent(lista, lista2);

}
