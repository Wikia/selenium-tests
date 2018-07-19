package com.wikia.webdriver.common.core.url;

import static com.wikia.webdriver.common.core.configuration.Configuration.getEnvType;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

public class BaseUrlBuilder {

  public static final String HTTP_PREFIX = "http://";
  public static final String HTTPS_PREFIX = "https://";
  protected String env;
  protected EnvType envType;

  public BaseUrlBuilder(String env) {
    this.env = env;
    this.envType = getEnvType(this.env);
  }

  /**
   * It actually adds string to url, so the path might consist a query
   *
   * @param url  wiki's url.
   * @param path path to be added to the url
   **/
  protected String addPathToUrl(String url, String path) {

    String outputUrl = (!path.startsWith("/")) ? String.format("%s/%s", url, path)
                                               : String.format("%s%s", url, path);

    String qs = Configuration.getQS();
    if (StringUtils.isNotBlank(qs)) {
      outputUrl = appendQueryStringToURL(outputUrl, qs);
    }
    return outputUrl;
  }

  public String appendQueryStringToURL(String url, String qs) {
    String separator = url.contains("?") ? "&" : "?";

    String[] filteredUrl = url.split("#");
    if (filteredUrl.length > 1) {
      return filteredUrl[0] + separator + qs + "#" + filteredUrl[1];
    } else {
      return url + separator + qs;
    }
  }

  public String globallyEnableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl,
                                       String.format("InstantGlobals.%s=[XX]", instantGlobal)
    );
  }

  public String globallyDisableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl,
                                       String.format("InstantGlobals.%s=[ZZ]", instantGlobal)
    );
  }

  public String getCacheBusterQuery(String pageName) {
    return pageName.equals("") || pageName.equals("/") ? "" : "?cb=" + DateTime.now().getMillis();
  }

  public String getQueryParams(String pageName, String[] queryParams) {
    String query = this.getCacheBusterQuery(pageName);

    if (!query.equals("")) {
      for (String queryParam : queryParams) {
        query = query + "&" + queryParam;
      }
    }

    return query;
  }
}
