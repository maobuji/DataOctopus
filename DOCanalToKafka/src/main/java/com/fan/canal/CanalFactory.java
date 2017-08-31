package com.fan.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;

import java.net.InetSocketAddress;

/**
 * Created by zhang on 2017/8/30.
 */
public class CanalFactory {


    private static CanalConnector connector = null;

    public static CanalConnector getCanalConnector() {

        if (connector == null) {
            try {
                connector = CanalConnectors
                        .newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "", "");
                connector.connect();
                connector.subscribe(".*\\..*");
                connector.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                connector = null;
            }
        }
        return connector;
    }

    public static void release() {
        connector.disconnect();
        connector = null;
    }
}
