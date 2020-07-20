package com.system.mapper.send;

import com.system.pojo.send.ContractSon;
import com.system.pojo.send.FormSon0032;

import java.math.BigDecimal;
import java.util.List;

public interface FormSon0032Mapper {

    int deleteByPrimaryKey(BigDecimal id);


    int insert(FormSon0032 record);


    int insertSelective(FormSon0032 record);


    FormSon0032 selectByPrimaryKey(BigDecimal id);


    int updateByPrimaryKeySelective(FormSon0032 record);


    int updateByPrimaryKey(FormSon0032 record);

    List<ContractSon> selectAll();
}