package com.system.service.impl.EditProfile;

import com.system.mapper.EditProfile.BasicArchivesMapper;
import com.system.pojo.EditProfile.Formmain_0030;
import com.system.service.EditProfile.BasicArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicArchivesServiceImpl implements BasicArchivesService {
    @Autowired
    private BasicArchivesMapper mapper;

    @Override
    public List<Formmain_0030> FileQuery(String field0002) {
        List<Formmain_0030> list = mapper.FileQuery(field0002);
        return list;
    }
}
