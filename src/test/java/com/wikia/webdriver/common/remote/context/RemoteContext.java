package com.wikia.webdriver.common.remote.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class RemoteContext {

  @NonNull
  private final String siteId;
}
