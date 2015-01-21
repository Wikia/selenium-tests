package com.wikia.webdriver.common.core;

import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;

public class XMLFunctions {

	private XMLFunctions() {

	}

	/**
	 * method used to get credentials from configuration xml
	 *
	 * @param file
	 * @param key
	 * @return
	 * @author Karol Kujawiak
	 */
	public static String getXMLConfiguration(File file, String key) {
		try {
			XMLConfiguration xml = new XMLConfiguration(file);
			return xml.getString(key);
		} catch (Exception e) {
			return e.toString();
		}
	}
}
