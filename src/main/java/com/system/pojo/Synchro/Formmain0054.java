package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0054 {
    private String field0019;//申请人所在组织
    private String field0001;//项目请购单编码
    private String field0021;//申请部门
    private String field0020;//申请人
    private String field0022;//申请时间
    private String field0012;//组织编码
    private String field0013;//组织名称
    private String field0015;//项目编码
    private String field0016;//项目名称
}
