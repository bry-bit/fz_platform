package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0030 {
    private String field0001;//供应商编码
    private String field0002;//供应商名称
    private String field0003;//所属分类码
    private String field0008;//电子邮件
    private String field0009;//电话号码
}
