package com.wikia.webdriver.Common.Core.GeoEdge;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface GeoEdgeProxy {
    String country() default "";
}
