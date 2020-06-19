package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商平台报价主表（报价审核表）
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main_quotation {
    private String id;
    private String item_code;
    private String item_name;
    private String form_code;
    private String form_name;
    private String proposer;
    private String dept;
    private String create_date;
    private String total;
    private String name;
    private String supplier_name;
    private String bid_state;
    private Integer page;
    private Integer limit;
    private String files_code;
}
