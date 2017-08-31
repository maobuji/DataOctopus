package com.fan.info;

import com.alibaba.fastjson.annotation.JSONField;

public class ESImpIndex {

    private EXImpColumn eXImpColumn = new EXImpColumn();

    @JSONField(name = "index")
    public EXImpColumn geteXImpColumn() {
        return eXImpColumn;
    }

    
    public void seteXImpColumn(EXImpColumn eXImpColumn) {
        this.eXImpColumn = eXImpColumn;
    }

    public class EXImpColumn {
        private String index;

        private String type;

        private String id;

        @JSONField(name = "_index")
        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        @JSONField(name = "_type")
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @JSONField(name = "_id")
        public String getId() {
            return id;
        }

    }

}
