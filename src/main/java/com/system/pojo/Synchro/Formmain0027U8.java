package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * U8的存货档案的接口实体类
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0027U8 {
    private String code;//存货编码
    private String name;//存货名称
    private String sort_code;//所属分类码
    private String unitgroup_code;//计量单位组编码
    private String unitgroup_type;//计量单位组类型
    private String main_measure;//主计量单位
    private String invcode;//存货编码
    private String bequipment;//是否备件
    private String purchase_flag;//是否采购
    private String bPlanInv;//是否计划品
    private String sale_flag;//是否内销
    private String prod_consu_flag;//是否生产耗用
    private String bexpsale;//是否外销
    private String bProxyForeign;//是否委外
    private String in_making_flag;//是否在制
    private String selfmake_flag;//是否自制
}
