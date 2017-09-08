-- 创建数据库
drop DATABASE if exists `TENDERDB`;
CREATE DATABASE `TENDERDB` CHARACTER SET 'utf8'  COLLATE 'utf8_general_ci';

use TENDERDB;

CREATE TABLE T_BID (
  C_ID VARCHAR(32) NOT NULL COMMENT '主键',
  C_KEY VARCHAR(100)  NOT NULL COMMENT '唯一不重复的业务ID，不同来源的网站KEY可能不一样',
  C_STATION  VARCHAR(100) NOT NULL COMMENT '来源网站，与key做联合去重',
  C_NAME VARCHAR(50)  NOT NULL COMMENT '标题或摘要',
  C_URL VARCHAR(150)  NOT NULL COMMENT '原始地址的URL,用于后续访问',
  C_CONTEXT blob NULL COMMENT '原始网络数据',
  C_TYPE VARCHAR(10)  NULL COMMENT '类型:拟建项目(拟建)，招标预告（预告），公告（招标，邀标,询价,竞谈,单一,竞价，变更），招标结果(中标，成交，废标,流标),招标信用信息（合同,验收，违规,其他）',
  C_ORG_ROOT_NAME VARCHAR(50)   NULL COMMENT '招标公司顶级组织名称',
  C_ORG_NAME VARCHAR(50)   NULL COMMENT '招标公司名称',
  C_PROVINCE VARCHAR(10)  NULL COMMENT '招标省份',
  C_CITY VARCHAR(10)  NULL COMMENT '招标地市',
  C_BID_TIME timestamp  NULL COMMENT '业务时间',
  C_GET_TIME timestamp  NULL COMMENT '获取时间',
  PRIMARY KEY (C_ID) COMMENT '主键',
  UNIQUE INDEX `C_KEY_INDEX` (`C_KEY`, `C_STATION`) USING BTREE
);

