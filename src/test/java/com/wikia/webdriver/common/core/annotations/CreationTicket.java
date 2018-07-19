package com.wikia.webdriver.common.core.annotations;

import java.lang.annotation.*;

/**
 * This annotation serves as a placeholder to mark creation ticket ID issues.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface CreationTicket {

  String ticketID();

  String comment() default "";
}
