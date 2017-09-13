package com.fan.crawler;

import com.fan.crawler.base.service.CrawlerBase;
import com.fan.crawler.service.b2b100086cn.B2b10086cn_JG;
import com.fan.crawler.service.b2b100086cn.B2b10086cn_ZB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/9/9.
 */
@Component
public class TaskPool implements ApplicationContextAware, InitializingBean, DisposableBean {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // 任务启动没有采用定期调度的原因是：目前任务还不支持反复调度，后续再优化
        // 任务没有采用注解发现的原因是：希望批量配置，而不是到每个类里边去配置

        // 寻找任务并启动执行
        List<Class> tasks = new ArrayList<Class>();
        tasks.add(B2b10086cn_ZB.class);
        tasks.add(B2b10086cn_JG.class);

        for (Class s : tasks) {
            Object task=applicationContext.getBean(s);

            CrawlerBase crawlerBase=(CrawlerBase)task;
            crawlerBase.setDupStrategy(0);

            Runnable runnable = (Runnable) task;
            Thread t=new Thread(runnable);
            t.setName(s.getSimpleName()+"-Thread");
            t.setDaemon(true);
            t.start();
        }


    }

    @Override
    public void destroy() throws Exception {

    }


}
