package com.fan.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fanmingming on 2017/8/31.
 */
public class MessageToJsonListTransfer {

    public List<String> transfer(Message message) throws Exception {

        List<String> lsJsonData = new ArrayList<String>();

        List<CanalEntry.Entry> entrys = message.getEntries();
        for (CanalEntry.Entry entry : entrys) {

            // 过滤掉事物
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {

                System.out.println("trans:" + entry.getEntryType().toString() + " m=" + entry.getSerializedSize());
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s,version : %s,entrytype : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType, entry.getHeader().getExecuteTime(), entry.getEntryType().toString()));

            String schemaName = entry.getHeader().getSchemaName();
            String tableName = entry.getHeader().getTableName();

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    transToJson(lsJsonData,schemaName,tableName,rowData.getBeforeColumnsList(),eventType);
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    transToJson(lsJsonData,schemaName,tableName,rowData.getAfterColumnsList(),eventType);

                } else {
//                    System.out.println("-------> before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------> after");
                    transToJson(lsJsonData,schemaName,tableName,rowData.getAfterColumnsList(),eventType);
                }
            }


        }
        return lsJsonData;
    }

    public void transToJson(List<String> lsJsonData, String schemaName, String tableName, List<CanalEntry.Column> columns, CanalEntry.EventType eventType) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("schemaName", schemaName);
        dataMap.put("tableName", tableName);

        if (eventType == CanalEntry.EventType.DELETE) {
            dataMap.put("opType", "delete");
        } else if (eventType == CanalEntry.EventType.INSERT) {
            dataMap.put("opType", "insert");
        } else {
            dataMap.put("opType", "update");
        }

        for (CanalEntry.Column column : columns) {
            System.out.println(column.getSqlType());
            dataMap.put(column.getName(), column.getValue());
        }

        String jsonString=JSON.toJSONString(dataMap);
        System.out.println(jsonString);
        lsJsonData.add(jsonString);

    }

}
