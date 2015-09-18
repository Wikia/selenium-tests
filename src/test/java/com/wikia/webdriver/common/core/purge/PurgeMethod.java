package com.wikia.webdriver.common.core.purge;

import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

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
