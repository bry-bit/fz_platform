package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inquiry_formSub {
    private String ID;
    private String formmain_id;
    private String field0008;
    private String field0010;
    private String field0018;
    private String field0012;
    private String field0013;
    private String field0032;
    private String field0033;
    private String field0034;
    private String field0014;
    private String sup_name;
    private String offer;
    private String bid_state;
    private String field0001;
    private String field0003;
    private String name;
}
