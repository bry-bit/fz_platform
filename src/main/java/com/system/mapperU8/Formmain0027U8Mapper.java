package com.system.mapperU8;

import com.system.pojo.Synchro.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Formmain0027U8Mapper {
    List<Inventory> selectFormmain0027U8();

    Integer selectMaxcode();
}
