package com.wikia.webdriver.common.driverprovider.mobileprovider;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import java.util.*;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileDriversRegistry {

	private Map<String, List<String>> devicesRegistry = new HashMap();
	private String mobilePlatform;
	private String pathToMobileConfig;

	public MobileDriversRegistry(String platform, String pathToMobileConfig) {
		this.mobilePlatform = platform;
		this.pathToMobileConfig = pathToMobileConfig;
		setDevicesBasedOnConfig();
	}

	//@TODO make more fancy mechanism to pick device
	// ideally replace with some sort of in-house service like device-wall or selenium grid
	public String getDeviceForAndroidVersion(String version) {
		if (devicesRegistry.containsKey(version)) {
			return devicesRegistry.get(version).get(0);
		} else {
			String knownVersions = "";
			Iterator itr = devicesRegistry.keySet().iterator();
			while (itr.hasNext()) {
				knownVersions += itr.next().toString() + ", ";
			}
			throw new RuntimeException(
				String.format(
					"We don't have any device for provided version.\n" +
						"Please make sure device with provided version is present in mobile-config.xml file.\n" +
						"Provided version: %s, Known versions: %s",
					version,
					knownVersions
				)
			);
		}
	}

	private void setDevicesBasedOnConfig() {
		XMLConfiguration xml;
		try {
			xml = new XMLConfiguration(pathToMobileConfig);
		} catch (ConfigurationException ex) {
			throw new RuntimeException(ex);
		}
		//Get node in XML file for provided platform
		ConfigurationNode platformNode = (ConfigurationNode) xml.getRoot().getChildren(
			mobilePlatform.toLowerCase()
		).get(0);
		List devicesForPlatform = platformNode.getChildren();
		for (int i = 0; i < devicesForPlatform.size(); i++) {
			ConfigurationNode node = (ConfigurationNode) devicesForPlatform.get(i);
			String version = getAttributeValue("platform-version", node);
			String uuid = getAttributeValue("uuid", node);
			appendToDevicesList(version, uuid);
		}
	}

	private String getAttributeValue(String attributeName, ConfigurationNode node) {
		for (int i = 0; i < node.getAttributeCount(); i++) {
			if (node.getAttribute(i).getName().equalsIgnoreCase(attributeName)) {
				return (String) node.getAttribute(i).getValue();
			}
		}
		throw new RuntimeException(
			String.format(
				"Provided attribute doesn't exist in the node. Node name: %s, Attribute name: %s",
				node.getName(),
				attributeName
			)
		);
	}

	private void appendToDevicesList(String version, String uuid) {
		List<String> devicesForVersion;
		if (devicesRegistry.containsKey(version)) {
			devicesForVersion = devicesRegistry.get(version);
		} else {
			devicesForVersion = new ArrayList<>();
		}
		devicesForVersion.add(uuid);
		devicesRegistry.put(version, devicesForVersion);
	}
}
