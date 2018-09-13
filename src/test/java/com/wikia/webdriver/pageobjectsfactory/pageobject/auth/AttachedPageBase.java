package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.WebDriverException;

public abstract class AttachedPageBase<T extends AttachedPageBase<T>> extends BasePageObject{

	private String siteSpecificPath;

	public AttachedPageBase(String siteSpecificPath) {
		this.siteSpecificPath = siteSpecificPath;
	}

	protected abstract T getThis();

	public T open() {
		open(getWikiUrl() + URLsContent.USER_LOGIN);
		return getThis();
	}

	public T open(String redirectUrl) {
		String signinUrl = urlBuilder.getWikiGlobalURL() + siteSpecificPath;
		try {
			getUrl(urlBuilder.appendQueryStringToURL(signinUrl,
					URLEncoder.encode(redirectUrl, StandardCharsets.UTF_8.name()))
			);
		} catch (UnsupportedEncodingException e) {
			Log.log("Opening attached page", "Unable to encode redirect", false);
			throw new WebDriverException("Unable to encode redirect", e);
		}
		Log.log("Opening attached page", "Special:UserLogin page opened", true);

		return getThis();
	}
}
