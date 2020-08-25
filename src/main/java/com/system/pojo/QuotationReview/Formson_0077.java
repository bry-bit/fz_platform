package com.system.pojo.QuotationReview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 采购中标审批单子表列
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formson_0077 {
    //序号
    private String field0006;
    //行号
    private String field0029;
    //行状态
    private String field0044;
    //存货编码
    private String field0008;
    //存货名称
    private String field0009;
    //所属分类码
    private String field0034;
    //存货分类
    private String field0035;
    //工位号
    private String field0036;
    //工位名称
    private String field0037;
    //规格型号
    private String field0038;
    //单位
    private String field0039;
    //品牌
    private String field0040;
    //供应商
    private String field0041;
    //期望交货日期
    private String field0042;
    //采购数量
    private String field0010;
    //单价
    private String field0018;
    //小计
    private String field0019;
    //采购需求档案编码
    private String field0025;
    //请购单编码
    private String field0024;
    //采购清单编码
    private String field0027;
    //备注
    private String field0020;
    //预算类别
    private String field0046;
}
