package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OA存货档案表
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0027 {
    private String id;//ID
    private String field0010;//组织编码
    private String field0011;//组织名称
    private String field0001;//存货编码
    private String field0003;//存货名称
    private String field0004;//所属分类编码
    private String field0005;//计量单位组编码
    private String field0006;//计量单位组类型
    private String field0007;//主计量单位
    private String field0008;//inv存货编码
    private String field0013;//是否备件
    private String field0014;//是否采购
    private String field0016;//是否计划品
    private String field0017;//是否内销
    private String field0018;//是否生产耗用
    private String field0019;//是否外销
    private String field0020;//是否委外
    private String field0021;//是否在制
    private String field0022;//是否自制
    private String field0026;//规格型号
    private String field0027;//品牌
    private String field0024;//存货分类
    private String field0033;//报错信息
    private String field0035;//二级分类码
    private String field0036;//二级分类名称
}
