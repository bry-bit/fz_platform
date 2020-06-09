package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formmain0030;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0030Mapper {
    List<Formmain0030> selectformmain0030();

    String selectList(String code);

    void updateFormmain0027Field0001(String field0001, String code);

    void updateFormmain0027Syn(String field0001, String errmsg);
}
