package com.wikia.webdriver.common.core.annotations;

import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface InBrowser {

  String browserSize() default "";

  Browser browser() default Browser.DEFAULT;

  Emulator emulator() default Emulator.DEFAULT;
}
