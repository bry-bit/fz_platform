package com.system.pojo.send;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 采购合同档案子表
 */
@Data
@EqualsAndHashCode(of = "contractSonId")
public class ContractSon{

    //id(对应OA的ID)
    private String contractSonId;

    //对应OA的formmain_id
    private String contractMainId;

    //存货名称
    private String invName;

    //存货编码
    private String invCode;

    //规格型号
    private String invModel;

    //单位
    private String invUnit;

    //品牌
    private String invBrand;

    //期望交货日期
    private String hopeTime;

    //采购数量
    private Integer purchaseNum;

    //已发数量
    private Integer alreadyNum;

    //未发数量
    private Integer notNum;

    //本次数量
    private Integer thisNum;

    //采购审核状态(建议放在历史表)
    private String approveStatus;

    //采购审核累计通过数量（完成状态以这个为准）
    private Integer approveNum;

    //供应商发货状态（未完成，完成
    private String sendStatus;

}