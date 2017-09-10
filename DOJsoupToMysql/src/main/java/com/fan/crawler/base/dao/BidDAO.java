package com.fan.crawler.base.dao;

import com.fan.crawler.base.info.BidInfo;

/**
 * Created by Administrator on 2017/9/8.
 */
public interface BidDAO {

    public int insert(BidInfo bidInfo);

    public int update(BidInfo bidInfo);


}
