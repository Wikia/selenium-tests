package com.wikia.webdriver.common.driverprovider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bogna 'bognix' Knychala
 */
public class UserAgentsRegistry {

  private Map<String, String> userAgentRegistry;

  public UserAgentsRegistry() {
    userAgentRegistry = new HashMap();
    userAgentRegistry.put(
        "sony_tvs",
        "Mozilla/5.0(X11; sony_tvs; Linux x86_64; rv:24.0)"
        + "Gecko/20100101 Firefox/24.0"
    );
    userAgentRegistry.put(
        "iPhone",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) "
        +"AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
    );
  }

  public String getUserAgent(String userAgent) {
    return userAgentRegistry.get(userAgent);
  }
}
