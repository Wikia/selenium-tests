package com.wikia.webdriver.common.contentpatterns;

import java.io.File;

public class PathsContent {

	public static String harDirPath = System.getProperty("user.dir") + File.separator + "logs"+ File.separator+ "netExport_harFiles"+ File.separator;
	public static String fireBugPath = "src"+File.separator+"test"+File.separator+"resources"+File.separator+"Firebug"+File.separator+"firebug-1.7.2.xpi";
	public static String netExportPath = "src"+File.separator+"test"+File.separator+"resources"+File.separator+"Firebug"+File.separator+"netExport-0.8b13.xpi";
}
