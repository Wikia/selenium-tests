package com.wikia.webdriver.common.core.annotations;

import com.wikia.webdriver.common.core.helpers.User;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Execute {

  User asUser() default User.ANONYMOUS;

  String onWikia() default "";

  String disableFlash() default "";

  String mockAds() default "";

  String disableCommunityPageSalesPitchDialog() default "";

  String language() default "";

  boolean trackingOptIn() default true;

  boolean trackingOptOut() default false;
}
