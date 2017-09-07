package com.fan.info;

public class TenderInfo {

    // 招标组织
    private String orgName;

    // 招标类型
    private String tenderType;

    // 招标名称
    private String tenderName;

    // 招标日期
    private String tenderDate;

    // 招标ID
    private String tenderKey;

    private String tenderYear;

    public String getTenderYearMonth() {
        return tenderYearMonth;
    }

    public void setTenderYearMonth(String tenderYearMonth) {
        this.tenderYearMonth = tenderYearMonth;
    }

    private String tenderYearMonth;

    public String getTenderYear() {
        return tenderYear;
    }

    public void setTenderYear(String tenderYear) {
        this.tenderYear = tenderYear;
    }

    public String getTenderKey() {
        return tenderKey;
    }

    public void setTenderKey(String tenderKey) {
        this.tenderKey = tenderKey;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTenderType() {
        return tenderType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(String tenderDate) {
        this.tenderDate = tenderDate;
    }

}
