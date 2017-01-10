package com.wikia.webdriver.common.remote.operations;


import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.remote.operations.http.NoAuthOperation;
import lombok.Getter;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteId {

  public SiteId(String wikiUrl) {
    this.wikiUrl = wikiUrl;
    this.extractSiteIdFromSpecialVersion();
  }

  @Getter
  private String siteId;
  private String wikiUrl;

  private void extractSiteIdFromSpecialVersion() {
    NoAuthOperation request = new NoAuthOperation();
    String response = request.execute(this.wikiUrl + URLsContent.SPECIAL_VERSION, new JSONObject());
    Pattern p = Pattern.compile(".*city_id: (\\d+).*", Pattern.DOTALL);
    Matcher m = p.matcher(response);
    if (m.find()) {
      this.siteId = m.group(1);
    }
    else {
      this.siteId = "";
    }
  }
}
