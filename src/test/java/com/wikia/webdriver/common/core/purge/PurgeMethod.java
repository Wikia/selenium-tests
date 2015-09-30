package com.wikia.webdriver.common.core.purge;

import java.net.URI;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class PurgeMethod extends HttpRequestBase {

  public PurgeMethod(final String uri) {
    super();
    setURI(URI.create(uri));
  }

  @Override
  public String getMethod() {
    return "PURGE";
  }
}
