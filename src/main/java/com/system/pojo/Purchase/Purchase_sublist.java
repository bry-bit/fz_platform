package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 采购需求表（供应商平台）
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase_sublist {
    //ID
    private String id;
    //关联ID
    private String relation_id;
    //序号
    private String orders;
    //行号
    private String line;
    //存货编码
    private String stock_code;
    //存货名称
    private String stock_name;
    //存货分类
    private String stock_sort;
    //品牌
    private String brand;
    //单位
    private String unit;
    //数量
    private String quantity;
    //期望交货日期
    private String delivery_date;
    //开标日期
    private String open_date;
    //结标日期
    private String close_date;
    //备注
    private String  remarks;
    //创建日期
    private String create_date;
    //是否选择
    private String select_or_not;
    //发布状态
    private String post_status;
    //启用状态
    private String enable_status;
    //所属分类码
    private String class_code;
    //规格
    private String norms;
    //工位号
    private String station_no;
    //供应商
    private String field0002;
    //已选供应商数量
    private String sum;
    //预算类别
    private String field0042;
}
