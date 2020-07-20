package com.system.mapper.send;

import com.system.pojo.send.ContractSon;

import java.util.List;

public interface ContractSonMapper {

    int insert(ContractSon record);

    int insertSelective(ContractSon record);

    List<ContractSon> selectSonAll();
}