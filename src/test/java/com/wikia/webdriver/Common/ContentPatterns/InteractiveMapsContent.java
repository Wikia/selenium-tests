package com.wikia.webdriver.Common.ContentPatterns;


public class InteractiveMapsContent {

	public static final String pinDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
			+ " Morbi eget porta eros, a dapibus nulla. Cras quis vulputate mauris, vel aliquam quam. "
			+ "Praesent lobortis massa a malesuada cursus. Ut ac bibendum metus. Aliquam vitae justo nunc. "
			+ "Nam metus nulla, lobortis id purus ultrices, pulvinar pellentesque odio. Nunc id ligula "
			+ "tellus. Duis elementum, arcu et eleifend placerat, mauris est rhoncus turpis, "
			+ "vitae dapibus augue enim vel lectus. Pellentesque consectetur gravida mi in "
			+ "lobortis. Aliquam amet";
	public static final String embedMapArticleName = "embedMap";
	public static final String embedMapOutOfWikia = "embedMapOutOfWikia";
	public static final int pinTypeIndex = 0;
	public static final int selectedMapIndex = 0;
	public static final int selectedTemplateIndex = 0;
	public static final String associatedArticleName = "Slid";
	public static String templateName;
	public static final String templateNameToSearchShouldBeFound = "test";
	public static final String templateNameToSearchShouldNotBeFound = "1234567890123123123123213";
	public static final String learnMoreLink = "http://maps.wikia.com";
	public static final String mapName = "testingMapName";
	public static final String pinTypeName = "testingPinType";
	public static final String pinName = "testingPinName";
	
	//Palantir app content
	public static final String PONTO_MSG_SUCCESS = "success";
	public static final String PONTO_MSG_RESPONSECODE = "responseCode";
	public static final String PONTO_MSG_MESSAGE= "message";
	
	public static final String PONTO_SETPLAYER = "var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke('im.pontoCommunicationAPI','setPlayerCurrentLocation', "
			+ "{lat: arguments[0], lng: arguments[1], zoom: arguments[2], centerMap: arguments[3]}, "
			+ "callback," 
			+ "function (response) {}, true);"; 
	
	public static final String PONTO_REMOVEPLAYER = "var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke('im.pontoCommunicationAPI','removePlayerLocation',"
			+ "null, callback"
			+ "function (response) {console.log(response)}, true);";
	
	public static final String PONTO_UPDATEPOSITION = "var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke('im.pontoCommunicationAPI','updateMapPosition',"
			+ "{lat: arguments[0], lng: arguments[1], zoom:arguments[2]},"
			+ "callback,"
			+ "function (response) {}, true);";
	
}
