package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {
    private String cVenCode;//供应商编码
    private String cVenName;//供应商名称
    private String cVCCode;//所属分类码
    private String cVenEmail;//邮箱
    private String cVenPhone;//电话
}
