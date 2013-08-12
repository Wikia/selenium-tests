package com.wikia.webdriver.Common.Properties;

import java.io.File;

import com.wikia.webdriver.Common.Core.XMLFunctions;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class Credentials {

	public String userName;
	public String password;

	public String userName2;
	public String password2;

	public String userName3;
	public String password3;

	public String userName4;
	public String password4;

	public String userName5;
	public String password5;

	public String userName6;
	public String password6;

	public String userNameNonLatin;
	public String userNameNonLatinEncoded;
	public String passwordNonLatin;

	public String userNameWithUnderScore;
	public String passwordWithUnderScore;

	public String userNameWithBackwardSlash;
	public String userNameWithBackwardSlashEncoded;
	public String passwordWithBackwardSlash;

	public String userNameLong;
	public String passwordLong;

	public String email;
	public String emailPassword;
	public String emailQaart1;
	public String emailPasswordQaart1;
	public String emailQaart2;
	public String emailPasswordQaart2;
	public String emailQaart3;
	public String emailPasswordQaart3;
	public String emailQaart4;
	public String emailPasswordQaart4;

	public String userNameStaff;
	public String passwordStaff;

	public String userNameMonobook;
	public String passwordMonobook;

	public String userNameFB;
	public String passwordFB;
	public String emailFB;

	public String userNameBlocked;
	public String passwordBlocked;

	public String userNameForgottenPassword;
	public String userNameForgottenPassword2;

	public String geoEdgeUserName;
	public String geoEdgeUserPass;

	public String apiToken;

	public Credentials(File credentialsFile) {
		userName = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular.username");
		password = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular.password");
		userName2 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular2.username");
		password2 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular2.password");
		userName3 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular3.username");
		password3 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular3.password");
		userName4 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular4.username");
		password4 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular4.password");
		userName5 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular5.username");
		password5 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular5.password");
		userName6 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular6.username");
		password6 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.regular6.password");

		userNameNonLatin = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.nonLatin.username");
		userNameNonLatinEncoded = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.nonLatin.usernameenc");
		passwordNonLatin = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.nonLatin.password");

		userNameWithUnderScore = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.underscore.username");
		passwordWithUnderScore = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.underscore.password");

		userNameWithBackwardSlash = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.backwardslash.username");
		userNameWithBackwardSlashEncoded = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.backwardslash.usernameenc");
		passwordWithBackwardSlash = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.backwardslash.password");

		userNameLong = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.long.username");
		passwordLong = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.long.password");

		userNameStaff = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.wikiastaff.username");
		passwordStaff = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.wikiastaff.password");

		userNameMonobook = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.wikiamonobook.username");
		passwordMonobook = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.wikiamonobook.password");

		emailFB = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.facebook.email");
		passwordFB = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.facebook.password");
		userNameFB = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.facebook.username");

		email = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.generic.username");
		emailPassword = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.generic.password");

		emailQaart1 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia1.username");
		emailPasswordQaart1 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia1.password");
		emailQaart2 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia2.username");
		emailPasswordQaart2 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia2.password");
		emailQaart3 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia3.username");
		emailPasswordQaart3 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia3.password");
		emailQaart4 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia4.username");
		emailPasswordQaart4 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.email.qawikia4.password");

		userNameBlocked = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.tooManyLoginAttempts.username");
		passwordBlocked = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.tooManyLoginAttempts.password");

		userNameForgottenPassword = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.forgottenPassword.username1");
		userNameForgottenPassword2 = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.user.forgottenPassword.username2");

		geoEdgeUserName = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.AdsConfig.GeoEdgeCredentials.userName");
		geoEdgeUserPass = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.AdsConfig.GeoEdgeCredentials.password");

		apiToken = XMLFunctions.getXMLConfiguration(credentialsFile, "ci.api.token");
	}
}
