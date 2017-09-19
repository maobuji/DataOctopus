package com.fan.crawler.service.b2b100086cn;

import com.fan.crawler.base.dao.RawBidDAO;
import com.fan.crawler.base.info.RawBidInfo;
import com.fan.crawler.base.service.CrawlerBase;
import com.fan.crawler.base.util.DigestUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/9/8.
 */
@Component
public class B2b10086cn_DYLYCGXXGG extends B2b10086cn_ServiceBase {

    @Override
    protected String getServiceType() {
        return "B2b10086cn_DYLYCGXXGG";
    }

    private static String postUrl = "https://b2b.10086.cn/b2b/main/listVendorNoticeResult.html?noticeBean.noticeType=1";

    @Override
    protected String getPostUrl() {
        return postUrl;
    }
}
