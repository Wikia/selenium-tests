package com.wikia.webdriver.Common.ContentPatterns;

/*
 * @author: Łukasz Nowak
 */

public class PalantirContent {

	public PalantirContent() {
		success = null; 
		responseCode = null;
		message = null;
	}
	
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
			+ "null, callback,"
			+ "function (response) {console.log(response)}, true);";
	
	public static final String PONTO_UPDATEPOSITION = "var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke('im.pontoCommunicationAPI','updateMapPosition',"
			+ "{lat: arguments[0], lng: arguments[1], zoom:arguments[2]},"
			+ "callback,"
			+ "function (response) {}, true);";
	
	public static final String PONTOMSG_PLAYER_SUCCESS = "Player location set successfully";
	public static final String PONTOMSG_PLAYER_OUTOFBOUNDARIES = "Player location must be inside map boundaries";
	public static final String PONTOMSG_WRONG_PARAM = "Wrong parameters types";
	public static final String PONTOMSG_REMOVEPLAYER = "Player location removed from map successfully";
	public static final String PONTOMSG_MAPPOS_SUCCESS = "Map position updated successfully";
	public static final String PONTOMSG_WRONG_ZOOM = "Invalid zoom level value";
	public static final String PONTOMSG_WRONG_PARAMETER = "Wrong parameters types";
	
	public String success;
	public String responseCode;
	public String message;
}
