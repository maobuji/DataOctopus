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
