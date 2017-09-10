package com.fan.crawler.base.job;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2017/9/8.
 */
public class JobPool implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {

        Thread t = new Thread();

    }

    @Override
    public void destroy() throws Exception {

    }


}
