package com.fan.crawler.service.b2b100086cn;

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
import com.fan.crawler.base.dao.RawBidDAO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/9/8.
 */
@Component
public class B2b10086cn_CGGG extends B2b10086cn_ServiceBase {
    private static String postUrl = "https://b2b.10086.cn/b2b/main/listVendorNoticeResult.html?noticeBean.noticeType=2";

    @Override
    protected String getServiceType() {
        return "B2b10086cn_CGGG";
    }

    @Override
    protected String getPostUrl() {
        return postUrl;
    }
}
