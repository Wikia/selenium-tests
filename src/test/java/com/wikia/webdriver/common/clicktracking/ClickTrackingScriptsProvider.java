package com.wikia.webdriver.common.clicktracking;

/**
 * @author Jacek 'Mech' Wozniak
 * @author Michal 'justnpT' Nowierski
 * @author Rodrigo 'RodriGomez' Molinero
 */
public class ClickTrackingScriptsProvider {

	private String tracker;
	private String log_level;

	/**
	 * @param log_level normally log_level = 3
	 * @param log_group normally log_group = Wikia.Tracker
	 */
	public ClickTrackingScriptsProvider(String log_level, String tracker) {
		this.tracker = tracker;
		this.log_level = log_level;
	}

	private static String enableWikiaTracker =
		  "document.cookie='log_level=3';"
		+ "document.cookie='log_group=Wikia.Tracker';";

	private static String enableSeleniumTracker =
		  "window.seleniumOriginalTrack = Wikia.Tracker.track;"
		+ "Wikia.Tracker.track = function() {"
		+ "	   var args = arguments; "
		+ "	   selenium_addEvent(JSON.stringify(args)); "
		+ "	   console.log('intercepted the call to Tracker.track() ' + JSON.stringify(args));"
		+ "    window.seleniumOriginalTrack.apply(null, args); "
		+ "};";

	private static String seleniumGetEventsFunction =
		  "function selenium_getEvents() {"
		+ "	   var events = localStorage.getItem('seleniumEvents');"
		+ "	   events = events ? JSON.parse( events ) : [];"
		+ "	   return events;"
		+ "};";

	private static String seleniumAddEventFunction =
		  "function selenium_addEvent(args) {"
		+ "	   var events = selenium_getEvents();"
		+ "	   events.push( args ); "
		+ "	   localStorage.setItem('seleniumEvents', JSON.stringify(events));"
		+ "};";


	public static String trackerInstallation =
			enableWikiaTracker
		+	enableSeleniumTracker
		+ 	seleniumGetEventsFunction
		+ 	seleniumAddEventFunction;

	private static String windowSeleniumGetEventsFunction =
		  "window.selenium_getEvent = function(){"
		+ "    var events = localStorage.getItem('seleniumEvents');"
		+ "    events = events ? JSON.parse( events ) : [];"
		+ "    return events;"
		+ "};";

	private static String windowSeleniumPopEventsFunction =
		  "window.selenium_popEvent = function(){ "
		+ "	   var events = window.selenium_getEvent();"
		+ "    var result = events.pop();"
		+ "    localStorage.setItem('seleniumEvents', JSON.stringify(events));"
		+ "    return result;"
		+ "};";

	public static String eventsCaptureInstallation =
			windowSeleniumGetEventsFunction
		+	windowSeleniumPopEventsFunction;
}
