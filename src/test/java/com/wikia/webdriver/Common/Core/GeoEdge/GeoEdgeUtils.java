package com.wikia.webdriver.Common.Core.GeoEdge;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Bogna 'bognix' Knychala
 */
public class GeoEdgeUtils {

	private HashMap countriesConfiguration = new HashMap();
	private String configFilePath;
	private Document doc;

	public GeoEdgeUtils(String configFilePath) {
		this.configFilePath = configFilePath;

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(configFilePath);
		} catch (ParserConfigurationException | SAXException | IOException ex) {
			throw new RuntimeException(ex);
		}

		setIPsForCountries();
	}

    public String createBaseFromCredentials() {
        String credentials = getGeoEdgeUserName() + ":" + getGeoEdgePassword();
        byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
        final String encodedString = new String(encodedCredentials);
        return encodedString;
    }

	public String getIPForCountry(String countryCode) {
		return (String) countriesConfiguration.get(countryCode);
	}

	private String getGeoEdgeUserName() {
		Element geoEdgeCredentials = (Element) doc.getElementsByTagName("GeoEdgeCredentials").item(0);
		return geoEdgeCredentials.getElementsByTagName("userName").item(0).getTextContent();
	}

	private String getGeoEdgePassword() {
		Element geoEdgeCredentials = (Element) doc.getElementsByTagName("GeoEdgeCredentials").item(0);
		return geoEdgeCredentials.getElementsByTagName("password").item(0).getTextContent();
	}

	private void setIPsForCountries() {

		NodeList listOfCountries = doc.getElementsByTagName("Country");

		for (int s = 0; s < listOfCountries.getLength(); s++) {
			Element oneCountry = (Element) listOfCountries.item(s);
			countriesConfiguration.put(
				oneCountry.getElementsByTagName("Name").item(0).getTextContent(),
				oneCountry.getElementsByTagName("IP").item(0).getTextContent()
			);
		}
	}
}
