package com.system.service.impl.FileUpload;

import com.system.mapper.FileUpload.ExcelFileUploadMapper;
import com.system.pojo.FileUpload.File_leading;
import com.system.pojo.FileUpload.Formmain_0046_temp;
import com.system.pojo.FileUpload.Formson_0047_temp;
import com.system.pojo.FileUpload.Org_member;
import com.system.service.FileUpload.ExcelFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelFileUploadServiceImpl implements ExcelFileUploadService {
    @Autowired
    private ExcelFileUploadMapper mapper;

    @Override
    public void insert(File_leading fileLeading) {
        mapper.insert(fileLeading);
    }

    @Override
    public List<File_leading> select(File_leading fileLeading) {
        List<File_leading> list = mapper.select(fileLeading);
        return list;
    }

    @Override
    public List<Org_member> getAchieve(String ID) {
        List<Org_member> list = mapper.getAchieve(ID);
        return list;
    }

    @Override
    public void Formmain_0046(Formmain_0046_temp formmain0046Temp) {
        mapper.Formmain_0046(formmain0046Temp);
    }

    @Override
    public void Formson_0047(Formson_0047_temp formson0047Temp) {
        mapper.Formson_0047(formson0047Temp);
    }

    @Override
    public List<Formmain_0046_temp> showForma046(String fileName, String ID) {
        List<Formmain_0046_temp> list = mapper.showForma046(fileName, ID);
        return list;
    }

    @Override
    public List<Formson_0047_temp> showForms047(String fileName, String formmain_id, Integer page, Integer limit) {
        List<Formson_0047_temp> list = mapper.showForms047(fileName, formmain_id, page, limit);
        return list;
    }
}
