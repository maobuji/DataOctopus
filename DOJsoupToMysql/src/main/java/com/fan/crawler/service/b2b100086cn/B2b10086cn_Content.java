package com.fan.crawler.service.b2b100086cn;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/8.
 */

public class B2b10086cn_Content {

    private static Logger logger = LoggerFactory.getLogger(B2b10086cn_Content.class);

    public static String getUrl(String key) {
        return postUrl + key;
    }

    public static String getContent(String key) throws IOException {
        Connection con = getJSoupConnection("");
        Document doc = con.post();
        Elements mycontext = doc.body().getElementsByTag("tbody");
        String content = mycontext.toString();
        return content;

    }

    private static String postUrl = "https://b2b.10086.cn/b2b/main/viewNoticeContent.html?noticeBean.id=";
    private static String agentInfo = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";

    private static Connection getJSoupConnection(String key) {
        Connection con = Jsoup.connect(postUrl + key).userAgent(agentInfo).timeout(5000);
        con.header("Accept", "*/*");
        con.header("Accept-Encoding", "gzip, deflate, br");
        con.header("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
        con.header("Cache-Control", "no-cache");
        con.header("Connection", "keep-alive");
        con.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.header("Pragma", "no-cache");
        con.header("saplb_*", "(J2EE204289820)204289850");
        con.header("JSESSIONID", "siQosjZW8_dE9K4OhIuv3YIAdEzvXQE6Ny0M_SAP7AP4a778gjJqgoIJ-n5rEZYJ");

        return con;
    }
}
