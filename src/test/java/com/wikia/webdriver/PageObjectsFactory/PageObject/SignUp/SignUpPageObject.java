package com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class SignUpPageObject extends WikiBasePageObject {

	public SignUpPageObject(WebDriver driver, String wikiURL) {
		super(driver);
		driver.get(wikiURL + URLsContent.specialUserSignup);
		yearSelect = new Select(birthYearField);
		daySelect = new Select(birthDayField);
		monthSelect = new Select(birthMonthField);
	}

	@FindBy(css = "#WikiaSignupForm input[name='username']")
	private WebElement userNameField;
	@FindBy(css = "input[name='email']")
	private WebElement emailField;
	@FindBy(css = "#WikiaSignupForm input[name='password']")
	private WebElement passwordField;
	@FindBy(css = "select[name='birthmonth']")
	private WebElement birthMonthField;
	@FindBy(css = "select[name='birthday']")
	private WebElement birthDayField;
	@FindBy(css = "select[name='birthyear']")
	private WebElement birthYearField;
	@FindBy(css = "#wpCaptchaWord")
	private WebElement captchaField;
	@FindBy(css = "#wpCaptchaId")
	private WebElement blurryWordHidden;
	@FindBy(css = "input.big")
	private WebElement signupButton;

	private By errorMsgBy = By.className("error-msg");

	private Select yearSelect;
	private Select daySelect;
	private Select monthSelect;

	public void typeUserName(String userName) {
		userNameField.sendKeys(userName);
		userNameField.sendKeys(Keys.TAB);
	}

	public void typeEmail(String email) {
		emailField.sendKeys(email);
		emailField.sendKeys(Keys.TAB);
	}

	public void typePassword(String password) {
		passwordField.sendKeys(password);
		passwordField.sendKeys(Keys.TAB);
	}

	public void verifyTooYoungMessage() {
		String message = birthYearField
				.findElement(parentBy)
				.findElement(errorMsgBy)
				.getText();
		Assertion.assertEquals(message, PageContent.signUpTooYoungMessage);
	}

	public void verifySubmitButtonDisabled() {
		Assertion.assertEquals("true", signupButton.getAttribute("disabled"));
	}

	public void enterBirthDate(String month, String day, String year) {
		try {
			monthSelect.selectByVisibleText(month);
			Thread.sleep(150);
			daySelect.selectByVisibleText(day);
			Thread.sleep(150);
			yearSelect.selectByVisibleText(year);
			Thread.sleep(150);
			daySelect.selectByVisibleText(day);
			Thread.sleep(150);
			yearSelect.selectByVisibleText(year);
			Thread.sleep(150);
			monthSelect.selectByVisibleText(month);
			PageObjectLogging.log("enterBirthDate ", "Birth date selected", true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void typeCaptcha(File captchaFile) {
		typeCaptcha(getWordFromCaptcha(captchaFile));
	}

	public void typeCaptcha(String captchaWord) {
		captchaField.sendKeys(captchaWord);
	}

	public AlmostTherePageObject submit(String email, String password) {
		MailFunctions.deleteAllMails(email, password);
		return submit();
	}

	public AlmostTherePageObject submit() {
		scrollAndClick(signupButton);
		return new AlmostTherePageObject(driver);
	}

	public void verifyCaptchaInvalidMessage() {
		String message = captchaField
				.findElement(parentBy)
				.findElement(parentBy)
				.findElement(parentBy)
				.findElement(errorMsgBy)
				.getText();
		Assertion.assertEquals(message, PageContent.signUpInvalidCaptchaMessage);
	}

	public void verifyUserExistsMessage() {
		String message = userNameField
				.findElement(parentBy)
				.findElement(errorMsgBy)
				.getText();
		Assertion.assertEquals(message, PageContent.signUpUserExistsMessage);
	}

	private String getWordFromCaptcha(File captchaFile) {
		try {
			String captchaId = getAttributeValue(blurryWordHidden, "value");
			String urlAd = Global.DOMAIN+ "wiki/Special:Captcha/image?wpCaptchaId="+ captchaId;
			URL url = new URL(urlAd);

			String md5 = md5(url.openStream());
			if (md5 == null) {
				throw new Exception("Provided captcha url doesn't exists");
			}

			BufferedReader in = new BufferedReader(new FileReader(captchaFile));
			String strLine;
			while ((strLine = in.readLine()) != null) {
				String[] field = strLine.split(" ");
				if (field[1].equals(md5)) {
					in.close();
					PageObjectLogging.log("getWordFromCaptcha", "Captcha word decoded", true);
					return field[0];
				}
			}
			in.close();
			PageObjectLogging.log("getWordFromCaptcha", "Captcha word not decoded", false);
			return null;
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static String md5(InputStream is) {
		try {
			String output;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[8192];
			int read = 0;
			try {
				while ((read = is.read(buffer)) > 0) {
					digest.update(buffer, 0, read);
				}
				byte[] md5sum = digest.digest();
				BigInteger bigInt = new BigInteger(1, md5sum);
				output = String.format("%0" + (md5sum.length << 1) + "x", bigInt);
			} finally {
				is.close();
			} return output;
		} catch(NoSuchAlgorithmException e) {
			PageObjectLogging.log("md5", e.toString(), false);
			throw new RuntimeException(e);
		} catch(IOException e) {
			PageObjectLogging.log("md5", e.toString(), false);
			throw new RuntimeException(e);
		}
	}
}
