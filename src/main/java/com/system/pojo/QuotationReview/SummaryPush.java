package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 汇总所有的中标情况下根据项目名称和供应商名称进行分类
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryPush {
    //项目编码
    private String item_code;
    //项目名称
    private String item_name;
    //组织编码
    private String form_code;
    //组织名称
    private String form_name;
    //序号
    private String orders;
    //行号
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
    private String pid;
    private String permissionId;
    private String seq;
    private String resType;
}
