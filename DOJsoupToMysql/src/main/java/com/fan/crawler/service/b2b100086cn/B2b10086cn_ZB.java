package com.fan.crawler.service.b2b100086cn;

import com.fan.crawler.base.info.RawBidInfo;
import com.fan.crawler.base.service.CrawlerBase;
import com.fan.crawler.base.type.BidType;
import com.fan.crawler.base.util.DigestUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
public class B2b10086cn_ZB extends CrawlerBase implements Runnable {


    @Autowired
    private RawBidDAO bidDAO;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void run() {
        String station = "b2b.10086.cn";
        int max = 100000000;
        for (int page = 1; page <= max; page++) {
            System.out.println("第" + page + "页");
            Connection con = getJSoupConnection(page);
            Document doc = null;
            try {
                doc = con.post();
            } catch (IOException e) {
                e.printStackTrace();
                page--;
                continue;
            }

            Elements mycontext = doc.body().getElementsByTag("tbody").get(0).getElementsByTag("tr");
            int size = mycontext.size();

            if (size == 2) {
                System.out.println("B2b10086cn_ZB抓取结束");
            }


            for (int j = 2; j < size; j++) {


                RawBidInfo rawBidInfo = new RawBidInfo();

                rawBidInfo.setStation(station);

                Element ss = mycontext.get(j);
                String[] onclickString = ss.attr("onclick").split("'");

                // 获取公告ID
                String key = "";
                if (onclickString.length == 3) {
                    key = onclickString[1];
                }

                // 获取公告的原始地址
                rawBidInfo.setUrl(B2b10086cn_Content.getUrl(key));

                // 设置主键
                try {
                    rawBidInfo.setId(DigestUtil.getSHA256StrJava(rawBidInfo.getUrl()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                // 获取是否重复
                boolean isdup = bidDAO.existById(rawBidInfo.getId());

                // 如果重复，且为跳过，则continue；
                if (isdup && getDupStrategy() == 0) {
                    continue;
                }

                // 获取公告的标题
                if (ss.children().get(2).children().attr("title").equals("")) {
                    rawBidInfo.setTitle(ss.children().get(2).text());
                } else {
                    rawBidInfo.setTitle(ss.children().get(2).children().attr("title"));
                }

                // 获取公告时间
                try {
                    String strDate = ss.children().get(3).text();
                    rawBidInfo.setBidTime(sdf.parse(strDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 获取公告的内容
                rawBidInfo.setContent(B2b10086cn_Content.getContent(key));

                // 获取抓取时间
                rawBidInfo.setGetTime(new Date());

                // 如果重复则更新，如果不重复则插入
                if (isdup) {
                    bidDAO.update(rawBidInfo);
                } else {
                    bidDAO.insert(rawBidInfo);
                }
            }

        }

    }

    private static String postUrl = "https://b2b.10086.cn/b2b/main/listVendorNoticeResult.html?noticeBean.noticeType=2";
    private static String agentInfo = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";

    private Connection getJSoupConnection(int page) {
        Connection con = Jsoup.connect(postUrl).userAgent(agentInfo);
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

        con.data("page.noticeBean.sourceCH", "");
        con.data("noticeBean.source", "");
        con.data("noticeBean.title", "");
        con.data("noticeBean.startDate", "");
        con.data("noticeBean.endDate", "");
        con.data("page.currentPage", "" + page);
        con.data("page.perPageSize", "20");
        return con;
    }
}
