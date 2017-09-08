package com.fan.crawler.base.type;

/**
 * Created by Administrator on 2017/9/8.
 */
public enum BidType {

    // 拟建项目
    NIJIAN("拟建"),
    // 招标预告
    YUGAO("预告"),
    // 公告
    ZHAOBIAO("招标"), YAOBIAO("邀标"), XUNJIA("询价"), JINGTAN("竞谈"), DANYI("单一"), JINGJIA("竞价"), BIANGENG("变更"),
    // 招标结果
    ZHONGBIAO("中标"), CHENGJIAO("成交"), FEIBIAO("废标"), LIUBIAO("流标"),
    //招标信用信息
    HETONG("合同"), YANSHOU("验收"), WEIGUI("违规"), QITA("其他");

    private String value;

    BidType(String value) {
        this.value = value;
    }
}
