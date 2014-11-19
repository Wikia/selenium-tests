package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsAmazonObject extends AdsBaseObject {

	@FindBy(css="body")
	private WebElement body;
	@FindBy(css="div[id*=_gpt][data-gpt-page-params*=amzn]")
	private List<WebElement> slotsWithAmazonParams;

	private final String amazonScriptOnPage = "head > script[src*='amazon']";
	private final String amazonScript = "amazon-adsystem.com/e/dtb/bid";

	public AdsAmazonObject(
		WebDriver driver,
		String testedPage,
		NetworkTrafficInterceptor networkTrafficInterceptor
	) {
		super(driver, testedPage, networkTrafficInterceptor);
	}

	public void verifyAmazonScriptIncluded() {
		if (isScriptPresentInElement(body, amazonScript)) {
			PageObjectLogging.log("AmazonScriptFound", "Script from Amazon found", true);
		} else {
			throw new NoSuchElementException("Amazon script not found on page");
		}
	}

	public void verifyCallToAmazonIssued() {
		if (networkTrafficInterceptor.searchRequestUrlInHar(amazonScript)) {
			PageObjectLogging.log("RequestToAmazonIssued", "Request to amazon issued", true);
		} else {
			throw new NoSuchElementException("Request to Amazon not issued");
		}
	}

	public void verifyResponseFromAmazonPresent() {
		if (checkIfElementOnPage(amazonScriptOnPage)) {
			PageObjectLogging.log("ScriptFromAmazonPresent", "Script returned by Amazon present", true);
		} else {
			throw new NoSuchElementException("Script from Amazon not found");
		}
	}

	public void verifyGPTParams() {
		if (slotsWithAmazonParams.size() > 0) {
			PageObjectLogging.log("AmazonAdFound", "Slot with amazon ad found", true);
		} else {
			throw new NoSuchElementException("Amazon Ad not found on page");
		}
	}
}
