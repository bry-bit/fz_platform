package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Warehouse {
    /*U8仓库档案部分字段*/
    private String cWhcode;//仓库编码
    private String cWhName;//仓库名称
    private String cWhValueStyle;//计价方式
}
