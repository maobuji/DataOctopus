package com.fan.dao;


import com.fan.info.TenderInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TenderDAO {

    private Connection conn;

    private static String updateSql = "update tendernotice set orgName=?,tenderName=?,tenderDate=?,tenderType=?,tenderYear=?,tenderYearMonth=? where tenderKey=? ";
    private static String insertSql = "insert into tendernotice(tenderKey,orgName,tenderName,tenderDate,tenderType,tenderYear,tenderYearMonth) values(?,?,?,?,?,?,?)";

    public boolean update(TenderInfo tenderInfo) throws SQLException {
        if (conn == null) {
            conn = getConn();
        }

        if (conn == null) {
            return false;
        }


        PreparedStatement updatePs = conn.prepareStatement(updateSql);
        int index = 1;
        updatePs.setString(index++, tenderInfo.getOrgName());
        updatePs.setString(index++, tenderInfo.getTenderName());
        updatePs.setString(index++, tenderInfo.getTenderDate());
        updatePs.setString(index++, tenderInfo.getTenderType());
        updatePs.setString(index++, tenderInfo.getTenderYear());
        updatePs.setString(index++, tenderInfo.getTenderYearMonth());

        updatePs.setString(index++, tenderInfo.getTenderKey());
        // 先更新
        int i = updatePs.executeUpdate();
        if (i > 0) {

            return true;
        }

        //  更新不到则插入
        PreparedStatement insertPs = conn.prepareStatement(insertSql);
        index = 1;
        insertPs.setString(index++, tenderInfo.getTenderKey());
        insertPs.setString(index++, tenderInfo.getOrgName());
        insertPs.setString(index++, tenderInfo.getTenderName());
        insertPs.setString(index++, tenderInfo.getTenderDate());
        insertPs.setString(index++, tenderInfo.getTenderType());
        insertPs.setString(index++, tenderInfo.getTenderYear());
        insertPs.setString(index++, tenderInfo.getTenderYearMonth());
        insertPs.executeUpdate();

        return true;
    }


    public Connection getConn() {
        Connection myconn = null;
        String url = "jdbc:mysql://localhost:3306/TENDERDB?"
                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8&useSSL=false";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            myconn = DriverManager.getConnection(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("连接数据库失败！");
        }


        return myconn;
    }

}
