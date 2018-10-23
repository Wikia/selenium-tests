package com.wikia.webdriver.common.core.helpers;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class GoogleUser {

  private String email;
  private String password;
}
