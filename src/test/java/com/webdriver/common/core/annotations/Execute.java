package com.webdriver.common.core.annotations;

import com.webdriver.common.core.helpers.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Execute {

  User asUser() default User.ANONYMOUS;

  String onWikia() default "";

  String disableFlash() default "";

  String mockAds() default "";

  String disableCommunityPageSalesPitchDialog() default "";
}
