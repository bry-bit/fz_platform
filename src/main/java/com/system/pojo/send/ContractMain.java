package com.system.pojo.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 采购合同档案主表
 */

@Data
@EqualsAndHashCode(of = "contractMainId")
public class ContractMain {

    //组织编码
    private String orgCode;

    //组织名称
    private String orgName;

    //项目编码
    private String projectCode;

    //项目名称
    private String projectName;

    //项目采购合同编码
    private String contractCode;

    //U8采购合同编码
    private String uOrderCode;

    //供应商编码
    private String supplierCode;

    //供应商名称
    private String supplierName;

    //id
    private String contractMainId;

    //开始时间
    private String createTime;

    //采购业务类型
    private String buyType;

    //合同状态
    private String sendStatus;

}