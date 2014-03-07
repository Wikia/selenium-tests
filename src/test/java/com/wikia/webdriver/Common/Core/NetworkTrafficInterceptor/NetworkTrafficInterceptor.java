package com.wikia.webdriver.Common.Core.NetworkTrafficInterceptor;


import java.util.HashMap;
import java.util.Map;
import org.browsermob.core.har.Har;
import org.browsermob.core.har.HarEntry;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.http.BrowserMobHttpRequest;
import org.browsermob.proxy.http.RequestInterceptor;
import org.openqa.selenium.Proxy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class NetworkTrafficInterceptor extends ProxyServer {

	private Har har;
	private final int Max = 8888;
	private final int Min = 4444;
	private final int portNumber;

	public NetworkTrafficInterceptor() {
		super();
		portNumber = Min + (int)(Math.random() * ((Max - Min) + 1));
	}

	public Proxy startSeleniumProxyServer() {
		try {
			setPort(portNumber);
			start();
			return seleniumProxy();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void startIntercepting(String networkTrafficDumpName) {
		this.har = newHar(networkTrafficDumpName);
	}

	public boolean searchRequestUrlInHar(String needle) {
		har = getHar();
		for (HarEntry entry : har.getLog().getEntries()) {
			if (entry.getRequest().getUrl().contains(needle)) {
				return true;
			}
		}
		return false;
	}

	public void changeHeader(final String headerName, final String newValue) {
		addRequestInterceptor(new RequestInterceptor() {
			@Override
			public void process(BrowserMobHttpRequest request) {
				request.getMethod().removeHeaders(headerName);
				try {
					request.getMethod().addHeader(
						headerName,
						newValue
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setProxyServer(String IP) {
		Map<String, String> options = new HashMap<>();
		options.put("httpProxy", IP);
		setOptions(options);
	}

	@Override
	public void stop() {
		try {
			super.stop();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
