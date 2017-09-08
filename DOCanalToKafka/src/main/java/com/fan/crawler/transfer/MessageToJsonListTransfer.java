package com.fan.crawler.transfer;

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
                transToJson(lsJsonData, schemaName, tableName, rowData, eventType);
            }


        }
        return lsJsonData;
    }

    public void transToJson(List<String> lsJsonData, String schemaName, String tableName, CanalEntry.RowData rowData, CanalEntry.EventType eventType) {

        // 如果是更新话，需要产生两条记录，先删除，再插入
        Map<String, Object> beforedataMap = new HashMap<String, Object>();
        Map<String, Object> afterdataMap = new HashMap<String, Object>();
        beforedataMap.put("_schemaName", schemaName);
        beforedataMap.put("_tableName", tableName);
        afterdataMap.put("_schemaName", schemaName);
        afterdataMap.put("_tableName", tableName);

        List<CanalEntry.Column> beforeRowDataColumns = rowData.getBeforeColumnsList();
        List<CanalEntry.Column> afterRowDataColumns = rowData.getAfterColumnsList();

        if (eventType == CanalEntry.EventType.INSERT) {
            afterdataMap.put("_opType", "index");
            for (CanalEntry.Column column : afterRowDataColumns) {
                // 如果是主键，则将数据加进去
                if (column.getIsKey()) {
                    afterdataMap.put("_key", column.getValue());
                }
                afterdataMap.put(column.getName(), column.getValue());
            }
            String jsonString = JSON.toJSONString(afterdataMap);
            lsJsonData.add(jsonString);
            return;
        }

        if (eventType == CanalEntry.EventType.DELETE) {
            beforedataMap.put("_opType", "delete");
            for (CanalEntry.Column column : beforeRowDataColumns) {
                // 如果是主键，则将数据加进去
                if (column.getIsKey()) {
                    beforedataMap.put("_key", column.getValue());
                }
                beforedataMap.put(column.getName(), column.getValue());
            }
            String jsonString = JSON.toJSONString(beforedataMap);
            lsJsonData.add(jsonString);
            return;
        }

        if (eventType == CanalEntry.EventType.UPDATE) {
            beforedataMap.put("_opType", "delete");
            for (CanalEntry.Column column : beforeRowDataColumns) {
                // 如果是主键，则将数据加进去
                if (column.getIsKey()) {
                    beforedataMap.put("_key", column.getValue());
                }
                beforedataMap.put(column.getName(), column.getValue());
            }
            String jsonString = JSON.toJSONString(beforedataMap);
            lsJsonData.add(jsonString);

            afterdataMap.put("_opType", "index");
            for (CanalEntry.Column column : afterRowDataColumns) {
                // 如果是主键，则将数据加进去
                if (column.getIsKey()) {
                    afterdataMap.put("_key", column.getValue());
                }
                afterdataMap.put(column.getName(), column.getValue());
            }
            String jsonString1 = JSON.toJSONString(afterdataMap);
            lsJsonData.add(jsonString1);
        }

    }

}
