package com.wikia.webdriver.common.driverprovider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ludwik Kaźmierczak on 2015-04-09. <p/> Annotate test method if you want to disable
 * Flash plugin for webdriver. This would eliminate the problem which the driver quits before
 * the plugin quits during tear down.
 * If the driver quits first, then it would causes a long delay in run time.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface DisableFlash {

}
