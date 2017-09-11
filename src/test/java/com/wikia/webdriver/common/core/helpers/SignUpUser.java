package com.wikia.webdriver.common.core.helpers;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@AllArgsConstructor
@Value
public class SignUpUser {
  private String username;
  private String email;
  private String password;
  private LocalDate birthday;
}
