package com.wikia.webdriver.Common.Properties;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.XMLFunctions;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class NewProperties {

	public static String userName;
	public static String password;

	public static String userName2;
	public static String password2;

	public static String userName3;
	public static String password3;

	public static String userName4;
	public static String password4;

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

	public static void setVariables() {
		userName = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.username");
		password = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular.password");
		userName2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.username");
		password2 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular2.password");
		userName3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular3.username");
		password3 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular3.password");
		userName4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular4.username");
		password4 = XMLFunctions.getXMLConfiguration(Global.CONFIG_FILE, "ci.user.regular4.password");

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
}
