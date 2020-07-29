package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formmain0027;
import com.system.pojo.Synchro.Formson_0047_temp;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0027Mapper {
    void delete2();

    void delNan(Formmain0027 userInfo);

    void addNan(Formmain0027 userInfo);

    void addNan1(Formmain0027 userInfo);

    List<Formmain0027> selectAll();

    List<Formmain0027> selectformmain0027();

    String selectList(String code);

    void updateFormmain0027Field0001(String field0001, String code);

    void updateFormmain0027Syn(String field0001, String errmsg);

    String selectListExcel(String field0004);

    String selectfield0001(@Param("field0038") String field0038, @Param("field0032") String field0032, @Param("field0034") String field0034);

    List<Formson_0047_temp> selectFormmain0047(String map);
}
