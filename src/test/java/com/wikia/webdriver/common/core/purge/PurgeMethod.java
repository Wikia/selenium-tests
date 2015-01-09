/**
 *
 */
package com.wikia.webdriver.common.core.purge;

import org.apache.commons.httpclient.HttpMethodBase;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class PurgeMethod extends HttpMethodBase {

	public PurgeMethod() {
		super();
		setFollowRedirects(true);
	}

	public PurgeMethod(String url) {
		super(url);
		setFollowRedirects(true);
	}

	@Override
	public String getName() {
		return "PURGE";
	}
}
