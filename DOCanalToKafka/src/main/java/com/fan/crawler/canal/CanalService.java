package com.fan.crawler.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;

/**
 * Created by fanmingming on 2017/8/31.
 */
public class CanalService {

    public Message read() {
        CanalConnector connector = CanalFactory.getCanalConnector();
        if (connector == null) {
            return null;
        }
        int batchSize = 10;
        Message message = null;
        try {
            message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return message;

    }


    public void ack(long batchId) {
        CanalConnector connector = CanalFactory.getCanalConnector();
        connector.ack(batchId);
    }

    public void roback() {
        CanalConnector connector = CanalFactory.getCanalConnector();
        connector.rollback();
    }
}
