package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 采购中标审批单主表列
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain_0076 {
    //组织编码
    private String field0012;
    //组织名称
    private String field0013;
    //项目编码
    private String field0015;
    //项目名称
    private String field0016;
    //采购合同中标审批单流水号
    private String field0001;
    //客户盖章合同附件
    private String field0031;
    //供应商编码
    private String field0004;
    //供应商名称
    private String field0005;
    //总计
    private String field0022;
    List<Formson_0077> data;
}
