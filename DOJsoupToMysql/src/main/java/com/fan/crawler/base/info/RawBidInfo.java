package com.fan.crawler.base.info;

import com.fan.crawler.base.type.BidType;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/8.
 */
public class RawBidInfo {
    // 唯一不重复的业务ID.由网站网址+_+原始类型+_+类型下的唯一序号组成
    private String id;
    // 原始地址的URL,用于后续访问
    private String url;
    // 数据来源网站
    private String station;
    // 标题或摘要
    private String title;
    // 原始网页数据
    private String content;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
