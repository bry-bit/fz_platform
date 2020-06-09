package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商报价历史查询表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Historical_query {
    private String id;
    private String relation_id;
    private String bef_offer;
    private String bef_offer_date;
}
