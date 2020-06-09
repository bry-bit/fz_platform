package com.system.pojo.Purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandSummary {
    //主表ID
    private String id;
    //项目编码
    private String item_code;
    //项目名称
    private String item_name;
    //组织编码
    private String form_code;
    //组织名称
    private String  form_name;
    //项目采购需求档案编码
    private String files_code;
    //申请人
    private String  proposer;
    //申请部门
    private String dept;
    //供应商编码
    private String field0001;
    //供应商名称
    private String field0002;
    //开标日期
    private String open_date;
    //结标日期
    private String close_date;
    //存货ID
    private String sublist_id;
}
