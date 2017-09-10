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
    private String context;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public BidType getType() {
        return type;
    }

    public void setType(BidType type) {
        this.type = type;
    }

    public String getOrgRootName() {
        return orgRootName;
    }

    public void setOrgRootName(String orgRootName) {
        this.orgRootName = orgRootName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }
}
