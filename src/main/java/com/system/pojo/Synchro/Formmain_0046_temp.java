package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 采购清单主表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain_0046_temp implements Serializable {
    private String id;
    //组织编码
    private String field0005;
    //组织名称
    private String field0006;
    //项目编号
    private String field0001;
    //项目名称
    private String field0003;
    //导入人员
    private String field0020;
    //导入人员部门
    private String field0021;
    //项目采购清单编码
    private String field0023;
    //设备名称
    private String field0024;
    //设备编码
    private String field0025;
    //工位号
    private String field0026;
}
