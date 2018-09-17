package com.wikia.webdriver.common.remote;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.remote.operations.http.GetRemoteOperation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import org.openqa.selenium.WebDriverException;

public class SiteId {

  @Getter
  private String siteId;
  private String wikiUrl;

  public SiteId(String wikiUrl) {
    this.wikiUrl = wikiUrl;
    this.extractSiteIdFromSpecialVersion();
  }

  private void extractSiteIdFromSpecialVersion() {
    GetRemoteOperation request = new GetRemoteOperation(null);
    String url = this.wikiUrl + URLsContent.WIKI_DIR + URLsContent.SPECIAL_VERSION;
    String response = request.execute(url);
    Pattern p = Pattern.compile(".*city_id: (\\d+).*", Pattern.DOTALL);
    Matcher m = p.matcher(response);
    if (m.find()) {
      this.siteId = m.group(1);
    } else {
      throw new WebDriverException("Can't extract site id from url: " + url);
    }
  }
}
