package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * U8存货档案字段的数据库实体类
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory {
    private String cInvCode;//存货编码
    private String cInvccode;//存货分类
    private String cinvstd;//规格型号
    private String caddress;//品牌
}
