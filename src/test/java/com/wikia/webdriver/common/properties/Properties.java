package com.wikia.webdriver.common.properties;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.core.XMLFunctions;

public class Properties {

	public static String userName;
	public static String password;

	public static String userName2;
	public static String password2;

	public static String userName3;
	public static String password3;

	public static String userName4;
	public static String password4;

	public static String userName5;
	public static String password5;

	public static String userName6;
	public static String password6;

	public static String userName7;
	public static String password7;

	public static String userName8;
	public static String password8;

	public static String userName9;
	public static String password9;

	public static String userName10;
	public static String password10;

	public static String userName11;
	public static String password11;

	public static String userName12;
	public static String password12;

	public static String userName13;
	public static String password13;

	public static String userNameNonLatin;
	public static String userNameNonLatinEncoded;
	public static String passwordNonLatin;

	public static String userNameWithUnderScore;
	public static String passwordWithUnderScore;

	public static String userNameWithBackwardSlash;
	public static String userNameWithBackwardSlashEncoded;
	public static String passwordWithBackwardSlash;

	public static String userNameLong;
	public static String passwordLong;

	public static String email;
	public static String emailPassword;
	public static String emailQaart1;
	public static String emailPasswordQaart1;
	public static String emailQaart2;
	public static String emailPasswordQaart2;
	public static String emailQaart3;
	public static String emailPasswordQaart3;
	public static String emailQaart4;
	public static String emailPasswordQaart4;

	public static String userNameStaff;
	public static String passwordStaff;

	public static String userNameStaff2;
	public static String passwordStaff2;

	public static String userNameMonobook;
	public static String passwordMonobook;

	public static String userNameFB;
	public static String passwordFB;
	public static String emailFB;

	public static String userNameBlocked;
	public static String passwordBlocked;

	public static String userNameForgottenPassword;
	public static String userNameForgottenPassword2;

	public static String geoEdgeUserName;
	public static String geoEdgeUserPass;

	public static String apiToken;

	private static void setVariables() {
		userName = setUserName(XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.username"));
		password = setPassword(XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.password"));
		userName2 = setUserName(XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.username"));
		password2 = setPassword(XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.password"));
//		userName = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.username");
//		password = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.password");
//		userName2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.username");
//		password2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.password");
		userName3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular3.username");
		password3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular3.password");
		userName4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular4.username");
		password4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular4.password");
		userName5 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular5.username");
		password5 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular5.password");
		userName6 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular6.username");
		password6 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular6.password");
		userName7 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular7.username");
		password7 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular7.password");
		userName8 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular8.username");
		password8 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular8.password");
		userName9 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular9.username");
		password9 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular9.password");
		userName10 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular10.username");
		password10 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular10.password");
		userName11 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular11.username");
		password11 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular11.password");
		userName12 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular12.username");
		password12 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular12.password");
		userName13 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular13.username");
		password13 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular13.password");

		userNameNonLatin = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.username");
		userNameNonLatinEncoded = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.usernameenc");
		passwordNonLatin = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.nonLatin.password");

		userNameWithUnderScore = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.underscore.username");
		passwordWithUnderScore = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.underscore.password");

		userNameWithBackwardSlash = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.username");
		userNameWithBackwardSlashEncoded = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.usernameenc");
		passwordWithBackwardSlash = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.backwardslash.password");

		userNameLong = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.long.username");
		passwordLong = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.long.password");

		userNameStaff = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff.username");
		passwordStaff = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff.password");

		userNameStaff2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff2.username");
		passwordStaff2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiastaff2.password");

		userNameMonobook = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiamonobook.username");
		passwordMonobook = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.wikiamonobook.password");

		emailFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.email");
		passwordFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.password");
		userNameFB = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.facebook.username");

		email = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.generic.username");
		emailPassword = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.generic.password");

		emailQaart1 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia1.username");
		emailPasswordQaart1 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia1.password");
		emailQaart2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia2.username");
		emailPasswordQaart2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia2.password");
		emailQaart3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia3.username");
		emailPasswordQaart3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia3.password");
		emailQaart4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia4.username");
		emailPasswordQaart4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.email.qawikia4.password");

		userNameBlocked = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.tooManyLoginAttempts.username");
		passwordBlocked = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.tooManyLoginAttempts.password");

		userNameForgottenPassword = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.forgottenPassword.username1");
		userNameForgottenPassword2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.forgottenPassword.username2");

		geoEdgeUserName = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.userName");
		geoEdgeUserPass = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.AdsConfig.GeoEdgeCredentials.password");

		apiToken = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.api.token");
	}

	public static void setProperties() {
		Global.RUN_BY_MAVEN = "true".equals(System.getProperty("run_mvn"));
		if (Global.RUN_BY_MAVEN) {
			getPropertiesFromPom();
		} else {
			PropertiesSetter.setPropertiesManually();
		}
		setVariables();
	}

	private static void getPropertiesFromPom() {
		Global.BROWSER = System.getProperty("browser");
		Global.CONFIG_FILE = new File(System.getProperty("config"));
		Global.CAPTCHA_FILE = new File(System.getProperty("captcha"));
		Global.DOMAIN = System.getProperty("base-address");
		Global.LIVE_DOMAIN = System.getProperty("live-domain");
		Global.ENV = System.getProperty("env");
		Global.QS = System.getProperty("qs");
//		Global.LOG_VERBOSE = (Global.BROWSER.equals("IE")) ? 1 : 2;

		try {
			if (Global.DOMAIN.contains("dev")) {
				Global.LOGIN_BY_COOKIE = false;
			} else {
				Global.LOGIN_BY_COOKIE = true;
			}
		} catch (NullPointerException ex) {

		}
		Global.LOG_ENABLED = true;
	}

	private static void getWikiVersion() {
		WebDriver versionDriver = new HtmlUnitDriver(true);
		versionDriver.get(Global.DOMAIN + "wiki/Special:Version");
		WebElement versionTable = versionDriver.findElement(By.xpath("//td[contains(text(), 'Code')]"));
		Global.WIKI_VERSION = versionTable.getText();
		versionDriver.close();
	}

	private static String setUserName(String key) {
		return key;
	}

	private static String setPassword(String key){
		return key;
	}
}
