package com.fan;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.fan.canal.CanalService;
import com.fan.kafka.MessageCenter;
import com.fan.transfer.MessageToJsonListTransfer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanmingming on 2017/8/30.
 */
public class App {

    public static void doSleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        System.out.println("开始抓取数据");

        while (true) {
            CanalService canalService = new CanalService();
            Message message = canalService.read();

            if (message == null || message.getId() == -1 || message.getEntries().size() == 0) {
                doSleep(1000);
                System.out.println("没有发现数据");
                continue;
            }

            MessageToJsonListTransfer messageToJsonListTransfer = new MessageToJsonListTransfer();
            List<String> lsJsonDate = new ArrayList<String>();
            try {
                lsJsonDate = messageToJsonListTransfer.transfer(message);
            } catch (Exception ex) {
                doSleep(1000);
                canalService.roback();
                ex.printStackTrace();
                continue;
            }

            try {
                MessageCenter.sendMessage(lsJsonDate);
            } catch (Exception ex) {
                doSleep(1000);
                canalService.roback();
                ex.printStackTrace();
                continue;
            }
            canalService.ack(message.getId());
        }
    }


}
