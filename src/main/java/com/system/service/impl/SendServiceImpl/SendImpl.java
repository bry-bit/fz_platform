package com.system.service.impl.SendServiceImpl;

import com.google.common.collect.Lists;
import com.system.mapper.send.ContractMainMapper;
import com.system.mapper.send.ContractSonMapper;
import com.system.mapper.send.FormSon0032Mapper;
import com.system.mapper.send.Formmain0031Mapper;
import com.system.pojo.send.ContractMain;
import com.system.pojo.send.ContractSon;
import com.system.service.sendService.SendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SendImpl implements SendService {

    @Resource
    Formmain0031Mapper formmain0031Mapper;
    @Resource
    ContractMainMapper contractMainMapper;
    @Resource
    FormSon0032Mapper formSon0032Mapper;
    @Resource
    ContractSonMapper contractSonMapper;
    /**
     * 获取两个集合不同的元素
     *
     * @param configDoList
     * @param measureToActivityList
     * @return
     */
    private <T> List<T> getListCompare(List<T> configDoList, List<T> measureToActivityList) {
        List<T> ContractMains = Lists.newArrayList();
        List<T> maxList = configDoList;
        List<T> minList = measureToActivityList;
        if (measureToActivityList.size() > configDoList.size()) {
            maxList = measureToActivityList;
            minList = configDoList;
        }
        HashMap<T, Integer> map = new HashMap<>(maxList.size());
        //targetActivity
        for (T ContractMain : maxList) {
            map.put(ContractMain, 1);

        }
        //sourceActivity
        for (T ContractMain : minList) {
            // 相同的
            if (map.get(ContractMain) != null) {
                map.put(ContractMain, 2);
                continue;
            }
            // 不相等的
            ContractMains.add(ContractMain);
        }
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ContractMains.add(entry.getKey());
            }
        }
        return ContractMains;
    }

    /**
     * 同步数据
     */
    @Override
    public void autoSycContract() {
        //查询oa主表
        List<ContractMain> contractMains = formmain0031Mapper.selectAll();
        List<ContractMain> contractMains1 = contractMainMapper.selectAll();

        //主表不同的集合插入
        List<ContractMain> listCompare = getListCompare(contractMains, contractMains1);
        for (ContractMain contractMain : listCompare) {
            contractMainMapper.insertSelective(contractMain);
        }
        //查询oa子表
        List<ContractSon> formSon0032s = formSon0032Mapper.selectAll();
        List<ContractSon> contractSons = contractSonMapper.selectSonAll();
        List<ContractSon> listCompare2 = getListCompare(formSon0032s, contractSons);
        for (ContractSon contractSon : listCompare2) {
            contractSonMapper.insertSelective(contractSon);
        }
    }
}
