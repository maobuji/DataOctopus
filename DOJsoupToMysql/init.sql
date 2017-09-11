-- 创建数据库
drop DATABASE if exists `BIDDB`;
CREATE DATABASE `BIDDB` CHARACTER SET 'utf8'  COLLATE 'utf8_general_ci';

use BIDDB;
DROP TABLE IF EXISTS T_BID;
CREATE TABLE T_BID (
  C_ID VARCHAR(150) NOT NULL COMMENT '网站网址+_+分类类型+_+类型下的唯一序号构成',
  C_URL VARCHAR(255)  NOT NULL COMMENT '原始地址的URL,用于后续访问',
  C_NAME VARCHAR(255)  NOT NULL COMMENT '标题或摘要',
  C_CONTENT TEXT NULL COMMENT '原始招标信息',
  C_BID_TIME timestamp  NULL COMMENT '招标时间',
  C_GET_TIME timestamp  NULL COMMENT '获取时间',
  PRIMARY KEY (C_ID) COMMENT '主键'
);
/**
  C_TYPE VARCHAR(10)  NULL COMMENT '类型:拟建项目(拟建),招标预告（预告）,公告（招标,比选,邀标,询价,竞谈,单一,竞价,变更）,招标结果(中标,成交,废标,流标),招标信用信息（合同,验收，违规,其他）',
  C_PUR_ORG VARCHAR(50)   NULL COMMENT '采购方',
  C_PUR_ROOT_ORG VARCHAR(50)   NULL COMMENT '采购方顶级组织名称',
  C_PROVINCE VARCHAR(10)  NULL COMMENT '招标省份',
  C_CITY VARCHAR(10)  NULL COMMENT '招标地市',
*/
