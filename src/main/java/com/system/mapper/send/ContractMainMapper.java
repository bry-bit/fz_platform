package com.system.mapper.send;


import com.system.pojo.send.ContractMain;

import java.util.List;

public interface ContractMainMapper {

    int insert(ContractMain record);

    int insertSelective(ContractMain record);

    List<ContractMain> selectAll();
}