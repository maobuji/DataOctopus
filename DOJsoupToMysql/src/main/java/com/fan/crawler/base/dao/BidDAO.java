package com.fan.crawler.base.dao;

import com.fan.crawler.base.info.BidInfo;

/**
 * Created by Administrator on 2017/9/8.
 */
public interface BidDAO {

    public int update(BidInfo bidInfo);

    public int save(BidInfo bidInfo);
}
