package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OA仓库档案
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0028 {
    private String field0001;//仓库编码
    private String field0003;//仓库名称
    private String field0005;//组织编码
    private String field0006;//组织名称
}
