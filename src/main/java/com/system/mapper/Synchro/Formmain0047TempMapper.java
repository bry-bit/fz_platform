package com.system.mapper.Synchro;

import com.system.pojo.Synchro.Formmain_0046_temp;
import com.system.pojo.Synchro.Formson_0047_temp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0047TempMapper {

    List<Formson_0047_temp> selectFormmain0047(@Param("map") String map);

    void updatefield0012(@Param("field0001") String field0001, @Param("id") String id);

    List<Formson_0047_temp> selectEmptyfield0012(String map);

    List<Formmain_0046_temp> selectformmain0046(String map);

    void updatefield00121(String id, Integer code);

    List<Formson_0047_temp> selectEmptyfield00121(String map);

    List<Formson_0047_temp> selectEmptyfield00122(String map);

    void Updatefiel0023(String map);

    String selectfield0023(String map);

    void updatefield00231(@Param("map") String map, @Param("gg") String gg);
}
