package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formson0062;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formson0062Mapper {

    String selectListExcel(@Param("field0024") String field00024, @Param("field0026") String field0026
            , @Param("field0027") String field0027);

    List<Formson0062> selectformson0062();
}
