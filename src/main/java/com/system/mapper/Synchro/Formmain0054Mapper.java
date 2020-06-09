package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formmain0054;
import com.system.pojo.Synchro.Formson0055;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0054Mapper {

    List<Formmain0054> selectAll();

    void addNan1(Formmain0054 formmain0054);

    void addNan(Formson0055 formson0055);
}
