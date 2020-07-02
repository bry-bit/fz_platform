package com.system.pojo.QuotationReview;//

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;//
import lombok.Data;//
import lombok.experimental.Accessors;//

/**
 * 采购清单变更单子表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formson_0080 {
    private String field0008;//序号
    private String field0010;//行号
    private String field0018;//行状态
    private String field0012;//存货编码
    private String field0013;//存货名称
    private String field0038;//所属分类码
    private String field0039;//存货分类
    private String field0041;//工位号
    private String field0045;//工位名称
    private String field0032;//规格型号
    private String field0033;//单位
    private String field0034;//品牌
    private String field0035;//供应商
    private String field0036;//期望交货日期
    private String field0014;//需求数量
    private String field0015;//仓库可用量
    private String field0030;//库存使用量
    private String field0016;//需采购数量
    private String field0027;//请购在途数量
    private String field0025;//已请购数量
    private String field0028;//允许提交请购数量
    private String field0026;//未请购数量
    private String field0053;//变更后行状态

}
