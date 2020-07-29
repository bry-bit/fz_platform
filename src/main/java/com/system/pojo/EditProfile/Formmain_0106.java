package com.system.pojo.EditProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商变更单实体类
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain_0106 {
    private String field0011;//申请人所在组织
    private String field0012;//原供应商档案编码
    private String field0013;//变更人部门
    private String field0014;//变更人
    private String field0015;//变更时间
    private String field0023;//原供应商简称
    private String field0026;//同组织下同简称供应商编码
    private String field0025;//判断供应商简称是否重复
    private String field0002;//原供应商名称
    private String field0019;//同组织下同名供应商编码
    private String field0017;//判断供应商名称是否重复
    private String field0003;//原所属分类码
    private String field0008;//原电子邮件
    private String field0009;//原电话号码
    private String field0021;//审批意见
    private String field0028;//原组织编码
    private String field0030;//原组织名称
    private String field0032;//现供应商名称
    private String field0033;//现供应商简称
    private String field0034;//供应商名称
    private String field0035;//现所属分类码
    private String field0036;//现电子邮件
    private String field0037;//现电话号码
    private String field0038;//版本号
    private String field0042;//流水号

}
