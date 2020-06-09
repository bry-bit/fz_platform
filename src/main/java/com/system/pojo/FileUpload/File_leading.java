package com.system.pojo.FileUpload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 导入文件所需实体类
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class File_leading {
    //文件名称
    private String fileName;
    //生成采购清单状态
    private String produceStatus;
    //生成请购单状态
    private String requisitionStatus;
    //模板编号
    private String identifier;
    //上传时间
    private String uploadTime;
    //上传文件的ID
    private String fid;
}
