package com.wikia.webdriver.pageobjectsfactory.pageobject.wam;

/**
 * Created by Ludwik Kaźmierczak
 */
/**
 * @desc Wikia wam tabs (hubs)
 */
public enum WamTab {
	ALL(0, ""), TV(1, "TV"),VIDEO_GAMES(2, "GAMES"), BOOKS(3, "BOOKS"),
	COMICS(4, "COMICS"), MUSIC(6, "MUSIC"),  LIFESTYLE(5, "LIFESTYLE"),  MOVIES(7, "MOVIES");

	private final int verticalId;

	private final String expectedHeader;

	private WamTab(int id, String expectedHeader) {
		verticalId = id;
		this.expectedHeader = expectedHeader;
	}

	public int getId() {
		return verticalId;
	}

	public String getExpectedHeaderName() {
		return verticalId>0 ? String.format("TOP GAINERS: %s", expectedHeader) : "TOP GAINERS";
	}

	private String getIdAsString() {
		return Integer.toString(verticalId);
	}

	/**
	 * @desc Checks if passed value is in enum
	 * @param value mostly a select-box option
	 * @return true if enum has the value; false otherwise
	 */
	public static Boolean contains(String value) {
		for (WamTab vids : WamTab.values()) {
			if (vids.getIdAsString().equals(value)) {
				return true;
			}
		}

		return false;
	}
}

