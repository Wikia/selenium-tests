package com.wikia.webdriver.Common.Clicktracking;

/**
 * @author Jacek 'Mech' Wozniak
 * @author Michal 'justnpT' Nowierski
 * @author Rodrigo 'RodriGomez' Molinero
 */
public class ClickTrackingScripts {


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

	private static String selenium_getEventsFunction =
			  "function selenium_getEvents() {"
			+ "	   var events = localStorage.getItem('seleniumEvents');"
			+ "	   events = events ? JSON.parse( events ) : [];"
			+ "	   return events;"
			+ "};";

	private static String selenium_addEventFunction =
			  "function selenium_addEvent(args) {"
			+ "	   var events = selenium_getEvents();"
			+ "	   events.push( args ); "
			+ "	   localStorage.setItem('seleniumEvents', JSON.stringify(events));"
			+ "};";


	public static String trackerInstallation =

				enableWikiaTracker
			+	enableSeleniumTracker
			+ 	selenium_getEventsFunction
			+ 	selenium_addEventFunction;

	private static String windowSelenium_getEventsFunction =
			  "window.selenium_getEvent = function(){"
			+ "    var events = localStorage.getItem('seleniumEvents');"
			+ "    events = events ? JSON.parse( events ) : [];"
			+ "    return events;"
			+ "};";

	private static String windowSelenium_popEventsFunction =
			  "window.selenium_popEvent = function(){ "
			+ "	   var events = window.selenium_getEvent();"
			+ "    var result = events.pop();"
			+ "    localStorage.setItem('seleniumEvents', JSON.stringify(events));"
			+ "    return result;"
			+ "};";

	public static String eventsCaptureInstallation =

				windowSelenium_getEventsFunction
			+	windowSelenium_popEventsFunction;

			String trackingEngine = "document.cookie='log_level=3';"
			+ "document.cookie='log_group=Wikia.Tracker';"
+ "window.seleniumOriginalTrack = Wikia.Tracker.track;"
+ "Wikia.Tracker.track = function() {"
+ "    var args = arguments; "
+ "    selenium_addEvent(JSON.stringify(args)); "
+ "    console.log('intercepted the call to Tracker.track() ' + JSON.stringify(args) + 'added using selenium_addEvent');"
+ "    window.seleniumOriginalTrack.apply(null, args); "
+ "};"
+ "function selenium_getEvents() {"
+ "var events = localStorage.getItem('seleniumEvents');"
+ "events = events ? JSON.parse( events ) : [];"
+ "return events;"
+ "};"
+ "function selenium_addEvent(args) {"
+ "var events = selenium_getEvents();"
+ "events.push( args ); "
+ "localStorage.setItem('seleniumEvents', JSON.stringify(events));"
+ "};"
;



}
