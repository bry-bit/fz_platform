package com.system.pojo.FileUpload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 人员、人员部门、组织
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Org_member {
    //导入人员
    private String name;
    //导入人员部门
    private String orgName;
    //组织编码
    private String field0001;
    //组织名称
    private String field0002;
}
