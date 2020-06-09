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
public class Purchase_primary {
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
    //发布状态
    private String post_status;
}
