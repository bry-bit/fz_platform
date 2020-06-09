package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商档案
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tabulation_listing {
    //供应商编码
    private String field0001;
    //供应商名称
    private String field0002;
    //开标日期
    private String open_date;
    //结标日期
    private String close_date;
    //存货ID
    private String sublist_id;
}
