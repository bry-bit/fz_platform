package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 采购清单子表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formson_0047_temp {
    private String ID;
    private String formmain_id;
    //序号
    private String field0008;
    //行号
    private String field0010;
    //行状态
    private String field0018;
    //存货编码
    private String field0012;
    //存货名称
    private String field0013;
    //规格型号
    private String field0032;
    //单位
    private String field0033;
    //品牌
    private String field0034;
    //供应商
    private String field0035;
    //期望交货日期
    private String field0036;
    //需求数量
    private String field0014;
    //仓库可用量
    private String field0015;
    //库存使用量
    private String field0030;
    //需采购数量
    private String field0016;
    //请购在途数量
    private String field0027;
    //已请购数量
    private String field0025;
    //允许提交请购数量
    private String field0028;
    //未请购数量
    private String field0026;
    //存货分类
    private String field0038;
}
