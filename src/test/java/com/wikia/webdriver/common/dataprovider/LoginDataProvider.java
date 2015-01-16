package com.wikia.webdriver.common.dataprovider;

import com.wikia.webdriver.common.properties.Properties;
import org.testng.annotations.DataProvider;

/**
 * @author Bogna 'bognix' Knychala
 */
public class LoginDataProvider {

	@DataProvider
	public static final Object[][] getUserCredentials() {
		return new Object[][]{
			{
				Properties.userName, Properties.password, Properties.userName
			}, {
			Properties.userName2, Properties.password2, Properties.userName2
		}, {
			Properties.userNameWithUnderScore,
			Properties.passwordWithUnderScore,
			Properties.userNameWithUnderScore
		}, {
			Properties.userNameNonLatin, Properties.passwordNonLatin,
			Properties.userNameNonLatinEncoded
		}, {
			Properties.userNameWithBackwardSlash,
			Properties.passwordWithBackwardSlash,
			Properties.userNameWithBackwardSlashEncoded
		}, {
			Properties.userNameLong, Properties.passwordLong,
			Properties.userNameLong
		}, {
			Properties.userNameStaff, Properties.passwordStaff,
			Properties.userNameStaff
		},
		};
	}

	@DataProvider
	public static final Object[][] getUserCredentialsForCNW() {
		return new Object[][]{
			{
				Properties.userNameWithUnderScore,
				Properties.userNameWithUnderScore, Properties.passwordWithUnderScore
			}, {
			Properties.userNameWithBackwardSlash,
			Properties.userNameWithBackwardSlashEncoded,
			Properties.passwordWithBackwardSlash
		}, {
			Properties.userNameLong,
			Properties.userNameLong, Properties.passwordLong
		}
		};
	}
}
