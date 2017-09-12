package com.fan.crawler.base.service;

/**
 * Created by Administrator on 2017/9/12.
 */
public class CrawlerBase {

    // 遇到多少条重复记录，则停止爬取
    private int dupStopNum = 10000000;

    private int dupNum = 0;

    // 0:忽略  1:覆盖
    private int dupStrategy = 0;

    public int getDupStopNum() {
        return dupStopNum;
    }

    public void setDupStopNum(int dupStopNum) {
        this.dupStopNum = dupStopNum;
    }

    public int getDupNum() {
        return dupNum;
    }

    public void setDupNum(int dupNum) {
        this.dupNum = dupNum;
    }

    public int getDupStrategy() {
        return dupStrategy;
    }

    public void setDupStrategy(int dupStrategy) {
        this.dupStrategy = dupStrategy;
    }
}
