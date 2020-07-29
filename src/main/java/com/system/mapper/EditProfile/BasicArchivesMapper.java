package com.system.mapper.EditProfile;

import com.system.pojo.EditProfile.Formmain_0030;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicArchivesMapper {
    List<Formmain_0030> FileQuery(String field0002);
}
