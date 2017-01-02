package com.wikia.webdriver.common.remote.operations;


import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.remote.operations.http.NoAuthOperation;
import lombok.Getter;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetSiteId {

  public GetSiteId(String wikiUrl) {
    this.wikiUrl = wikiUrl;
    System.out.println(this.wikiUrl);
    this.setSiteId();
  }

  @Getter
  private String siteId;
  private String wikiUrl;

  private void setSiteId() {
    NoAuthOperation request = new NoAuthOperation();
    String response = request.execute(this.wikiUrl + URLsContent.SPECIAL_VERSION, new JSONObject());
    Pattern p = Pattern.compile(".*city_id: (\\d+).*", Pattern.DOTALL);
    Matcher m = p.matcher(response);
    System.out.println(m.groupCount());
    if (m.find())
      this.siteId = m.group(1);
    else
      this.siteId = "";
  }
}
