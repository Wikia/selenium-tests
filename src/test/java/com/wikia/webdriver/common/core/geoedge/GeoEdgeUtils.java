package com.wikia.webdriver.common.core.geoedge;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriverException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wikia.webdriver.common.core.configuration.Configuration;

/**
 * @author Bogna 'bognix' Knychala
 */
public class GeoEdgeUtils {

  private Map countriesConfiguration = new HashMap();
  private Document doc;

  public GeoEdgeUtils() {
    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      doc = docBuilder.parse(Configuration.getCredentialsFilePath());
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      throw new WebDriverException(ex);
    }

    setIPsForCountries();
  }

  public String getIPForCountry(String countryCode) {
    return (String) countriesConfiguration.get(countryCode);
  }

  private void setIPsForCountries() {

    NodeList listOfCountries = doc.getElementsByTagName("Country");

    for (int s = 0; s < listOfCountries.getLength(); s++) {
      Element oneCountry = (Element) listOfCountries.item(s);
      countriesConfiguration.put(oneCountry.getElementsByTagName("Name").item(0).getTextContent(),
          oneCountry.getElementsByTagName("IP").item(0).getTextContent());
    }
  }
}
