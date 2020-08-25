package com.system.mapper.FileUpload;

import com.system.pojo.FileUpload.File_leading;
import com.system.pojo.FileUpload.Formmain_0046_temp;
import com.system.pojo.FileUpload.Formson_0047_temp;
import com.system.pojo.FileUpload.Org_member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelFileUploadMapper {

    void insert(File_leading fileLeading);

    List<File_leading> select(File_leading fileLeading);

    List<Org_member> getAchieve(@Param("ID") String ID);

    void Formmain_0046(Formmain_0046_temp formmain0046Temp);

    void Formson_0047(Formson_0047_temp formson0047Temp);

    List<Formmain_0046_temp> showForma046(@Param("fileName") String fileName, @Param("ID") String ID);

    List<Formson_0047_temp> showForms047(@Param("fileName") String fileName
            , @Param("formmain_id") String formmain_id, @Param("page") Integer page, @Param("limit") Integer limit);
}
