package com.wikia.webdriver.common.dataprovider;

import java.util.Date;

public class RandomDataProvider {

    public static String getTimeStamp() {
        Date time = new Date();
        long timeCurrent = time.getTime();
        return String.valueOf(timeCurrent);
    }

}
