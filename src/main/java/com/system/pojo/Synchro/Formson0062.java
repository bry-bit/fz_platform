package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formson0062 {
    private String id;//ID
    private String field0029;//序号
    private String field0032;//存货分类
    private String field0008;//存货编码
    private String field0009;//存货名称
    private String field0034;//规格型号
    private String field0035;//单位
    private String field0036;//品牌
    private String field0037;//供应商
    private String field0038;//期货交货日期
    private String field0033;//工位号

}
