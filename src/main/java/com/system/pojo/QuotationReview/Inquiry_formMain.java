package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inquiry_formMain {
    private String id;
    private String field0005;
    private String field0006;
    private String field0001;
    private String field0003;
    private String field0050;
    private String field0049;
    private String field0023;
    private String field0047;
}
