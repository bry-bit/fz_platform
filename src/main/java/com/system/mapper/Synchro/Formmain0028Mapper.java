package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formmain0028;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0028Mapper {
    List<Formmain0028> selectformmain0028();

    String selectList(String code);

    void updateFormmain0028Syn(String field0001, String errmsg);

    void updateFormmain0028Field0001(String field0001, String code);
}
