package com.fan;


import com.fan.dao.TenderDAO;
import com.fan.info.TenderInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;


public class App {

    public static void main(String[] args) throws IOException, SQLException {

        File f = new File("TenderImp.json");
        if (f.exists()) {
            f.delete();
        }

        TenderDAO tenderDAO = new TenderDAO();
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");

        String postUrl = "https://b2b.10086.cn/b2b/main/listVendorNoticeResult.html?noticeBean.noticeType=2";
        String agentInfo = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";

        int max = 8372;
        for (int i = 0; i < max; i++) {

            System.out.println("第" + i + "页");

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
            con.data("page.currentPage", "" + (i + 1));
            con.data("page.perPageSize", "20");
            Document doc = con.post();

            Elements mycontext = doc.body().getElementsByTag("tbody").get(0).getElementsByTag("tr");
            int size = mycontext.size();



            TenderInfo tenderInfo = new TenderInfo();

            for (int j = 2; j < size; j++) {
                Element ss = mycontext.get(j);
                String[] onclickString = ss.attr("onclick").split("'");
                if (onclickString.length == 3) {
                    tenderInfo.setTenderKey(onclickString[1]);
                }

                tenderInfo.setOrgName(ss.children().get(0).text());
                tenderInfo.setTenderType(ss.children().get(1).text());

                if (ss.children().get(2).children().attr("title").equals("")) {

                    tenderInfo.setTenderName(ss.children().get(2).text());

                } else {
                    tenderInfo.setTenderName(ss.children().get(2).children().attr("title"));

                }
                tenderInfo.setTenderDate(ss.children().get(3).text());

                String date = tenderInfo.getTenderDate();
                String[] dates = date.split("-");
                tenderInfo.setTenderYear(dates[0]);
                if (dates[1].length() == 1) {
                    tenderInfo.setTenderYearMonth(dates[0]+"0"+dates[1]);
                } else {
                    tenderInfo.setTenderYearMonth(dates[0]+dates[1]);
                }



                tenderDAO.update(tenderInfo);

//                String jsonTenderString = JSON.toJSONString(tenderInfo);
//
//                out.write(jsonImpIndexString);
//                out.write("\r\n");
//                out.write(jsonTenderString);
//                out.write("\r\n");
//                out.flush();

            }

        }
        out.close();
    }

}
