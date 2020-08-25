package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商平台报价子表（报价审核表）
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sub_quotation {
    private String id;
    private String relation_id;
    private String orders;
    private String line;
    private String stock_code;
    private String stock_name;
    private String class_code;
    private String stock_sort;
    private String station_no;
    private String norms;
    private String unit;
    private String brand;
    private String supplier_name;
    private String supplier_code;
    private String delivery_date;
    private String quantity;
    private String offer;
    private String subtotal;
    private String offer_date;
    private String field0025;
    private String field0024;
    private String field0027;
    private String remarks;
    private String open_date;
    private String close_date;
    private String enable_status;
    private String select_or_not;
    private String bid_id;
    private String contract_id;
    private String upload_buyer;
    private String upload_supplier;
    private String bid_state;
    private String issuance_date;
    private String item_code;
    private String item_name;
    private String proposer;
    private String return_msg;
    private String field0042;//预算类别
}
