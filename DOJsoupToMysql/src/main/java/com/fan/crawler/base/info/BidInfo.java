package com.fan.crawler.base.info;

import com.fan.crawler.base.type.BidType;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/8.
 */
public class BidInfo {
    private String id;
    // 唯一不重复的业务ID，不同来源的网站KEY可能不一样
    private String key;
    //来源网站，与key做联合去重
    private String station;
    // 标题或摘要
    private String name;
    // 原始地址的URL,用于后续访问
    private String url;
    // 原始网页数据
    private String CONTEXT;
    // 项目类型
    private BidType type;
    // 招标公司顶级组织名称
    private String orgRootName;
    // 招标公司名称
    private String orgName;
    // 招标省份
    private String province;
    // 招标地市
    private String city;
    // 业务时间
    private Date bidTime;
    // 获取时间
    private Date getTime;
}
