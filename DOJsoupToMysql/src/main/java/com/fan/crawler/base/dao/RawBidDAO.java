package com.fan.crawler.base.dao;

import com.fan.crawler.base.info.RawBidInfo;

/**
 * Created by Administrator on 2017/9/8.
 */
public interface RawBidDAO {

    public int insert(RawBidInfo rawBidInfo);

    public int update(RawBidInfo rawBidInfo);

    public boolean existById(String id);
}
