package com.wikia.webdriver.common.remote.operations;


import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.remote.operations.http.NoAuthOperation;
import lombok.Getter;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

public class GetSiteId {

  public GetSiteId(String wikiUrl) {
    this.wikiUrl = wikiUrl;
    System.out.println(this.wikiUrl);
    this.setSiteId();
    System.out.println("Site id is: " + this.getSiteId());
  }

  @Getter
  private String siteId;
  private String wikiUrl;

  private void setSiteId() {
    NoAuthOperation request = new NoAuthOperation();
    String response = request.execute(this.wikiUrl + URLsContent.SPECIAL_VERSION, new JSONObject());
    this.siteId = response;
  }
}
