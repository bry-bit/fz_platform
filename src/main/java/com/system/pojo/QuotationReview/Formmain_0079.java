package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 采购清单变更单主表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain_0079 {
    private String field0048;
    private String field0049;
    private String field0050;
    private String field0051;
    private String field0047;
    private String field0023;
    private String field0005;
    private String field0006;
    private String field0001;
    private String field0003;
    private String field0020;
    private String field0021;
    private String field0055;
    private String field0057;
    List<Formson_0080> data;
}
