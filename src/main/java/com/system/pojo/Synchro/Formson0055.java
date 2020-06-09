package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formson0055 {
    private String field0006;//序号
    private String field0029;//行号
    private String field0008;//存货编码
    private String field0009;//存货名称
    private String field0024;//需求数量
    private String field0027;//仓库可用数量
    private String field0025;//库存使用数量
    private String field0010;//本次请购数量
}
