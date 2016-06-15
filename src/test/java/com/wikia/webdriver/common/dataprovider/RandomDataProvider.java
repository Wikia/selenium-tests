package com.wikia.webdriver.common.dataprovider;

import java.util.Date;

/**
 * Created by neptunooo on 15/06/16.
 */
public class RandomDataProvider {

    public static String getTimeStamp() {
        Date time = new Date();
        long timeCurrent = time.getTime();
        return String.valueOf(timeCurrent);
    }

}
