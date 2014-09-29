package com.wikia.webdriver.Common.ContentPatterns;

/*
 * @author: Łukasz Nowak
 * @ownership: Mobile Web
 */

public class PalantirContent {
	
	private String success;
	private String responseCode;
	private String message;
	
	public static final int PALANTIR_MAP = 3;
	public static final String PONTO_MSG_SUCCESS = "success";
	public static final String PONTO_MSG_RESPONSECODE = "responseCode";
	public static final String PONTO_MSG_MESSAGE= "message";
	public static final String PONTOMSG_PLAYER_SUCCESS = "Player location set successfully";
	public static final String PONTOMSG_PLAYER_OUTOFBOUNDARIES = "Player location must be inside map boundaries";
	public static final String PONTOMSG_WRONG_PARAM = "Wrong parameters types";
	public static final String PONTOMSG_REMOVEPLAYER = "Player location removed from map successfully";
	public static final String PONTOMSG_MAPPOS_SUCCESS = "Map position updated successfully";
	public static final String PONTOMSG_WRONG_ZOOM = "Invalid zoom level value";
	public static final String PONTOMSG_WRONG_PARAMETER = "Wrong parameters types";
	
	public static final String PONTO_SETPLAYER = 
			"var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke("
			+ "	'im.pontoCommunicationAPI', "
			+ "	'setPlayerCurrentLocation', "
			+ "	{lat: arguments[0], lng: arguments[1], zoom: arguments[2], centerMap: arguments[3]}, "
			+ "	callback," 
			+ "	function (response) {}, "
			+ "	true"
			+ ");"; 
	
	public static final String PONTO_REMOVEPLAYER = 
			"var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke("
			+ "	'im.pontoCommunicationAPI', "
			+ "	'removePlayerLocation', "
			+ "	null, "
			+ "	callback,"
			+ "	function (response) {}, "
			+ "	true"
			+ ");";
	
	public static final String PONTO_UPDATEPOSITION = 
			"var callback = arguments[arguments.length - 1];"
			+ "Ponto.invoke("
			+ "	'im.pontoCommunicationAPI', "
			+ "	'updateMapPosition',"
			+ "	{lat: arguments[0], lng: arguments[1], zoom:arguments[2]},"
			+ "	callback,"
			+ "	function (response) {}, "
			+ "	true"
			+ ");";
	
        public PalantirContent(String success, String responseCode, String message) {
            this.success = success;
            this.responseCode = responseCode;
            this.message = message;
        }
	
	public String getSuccess() {
		return this.success;
	}
	
	public String getResponseCode() {
		return this.responseCode;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
