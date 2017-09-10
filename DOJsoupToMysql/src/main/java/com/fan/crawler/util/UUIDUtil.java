package com.fan.crawler.util;

import java.util.UUID;

/**
 * Created by zhang on 2017/9/10.
 */
public class UUIDUtil {

    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
