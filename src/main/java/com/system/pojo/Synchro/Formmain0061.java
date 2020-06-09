package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0061 {
    private String field0012;//组织编码
    private String field0013;//组织名称
    private String field0015;//项目编码
    private String field0016;//项目名称
    private String field0020;//导入人员
    private String field0021;//导入人员部门
    private String field0003;//项目采购清单编码
    private List<Formson0062> data;
}
