package com.system.service.impl.Purchase;

import com.system.mapper.Purchase.RequirementsMapper;
import com.system.pojo.Purchase.DemandSummary;
import com.system.pojo.Purchase.Tabulation_listing;
import com.system.pojo.Purchase.Purchase_primary;
import com.system.pojo.Purchase.Purchase_sublist;
import com.system.service.Purchase.RequirementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementsServiceImpl implements RequirementsService {
    @Autowired
    private RequirementsMapper mapper;

    @Override
    public List<Purchase_primary> fileMainTable(String name) {
        List<Purchase_primary> list = mapper.fileMainTable(name);
        return list;
    }

    @Override
    public List<Purchase_primary> fileMainTables(String name, Integer page, Integer limit) {
        List<Purchase_primary> list = mapper.fileMainTables(name, page, limit);
        return list;
    }

    @Override
    public List<Purchase_sublist> fileSubTable(Purchase_sublist purchaseSublist) {
        List<Purchase_sublist> list = mapper.fileSubTable(purchaseSublist);
        return list;
    }


    @Override
    public List<Tabulation_listing> SupTable(Integer page, Integer limit) {
        List<Tabulation_listing> list = mapper.SupTable(page, limit);
        return list;
    }

    @Override
    public List<Tabulation_listing> SupTables() {
        List<Tabulation_listing> list = mapper.SupTables();
        return list;
    }

    @Override
    public void insertTable(Tabulation_listing formmain0030) {
        mapper.insertTable(formmain0030);
    }

    @Override
    public List<Tabulation_listing> selectTable(String sublist_id, String field0001) {
        List<Tabulation_listing> list = mapper.selectTable(sublist_id, field0001);
        return list;
    }

    @Override
    public List<Tabulation_listing> findSupplier(String field0002) {
        List<Tabulation_listing> list = mapper.findSupplier(field0002);
        return list;
    }

    @Override
    public void updatePrimary(Purchase_primary primary) {
        mapper.updatePrimary(primary);
    }

    @Override
    public void updateSublist(Purchase_sublist sublist) {
        mapper.updateSublist(sublist);
    }

    @Override
    public List<DemandSummary> queryP_T(String id) {
        List<DemandSummary> list = mapper.queryP_T(id);
        return list;
    }

    @Override
    public void updateTime(Tabulation_listing formmain0030) {
        mapper.updateTime(formmain0030);
    }

    /**
     * 定时修改采购需求状态
     */
    @Override
    public void timeUpdatePurchaseStatus() {
        List<Purchase_primary> noPurchaseList = mapper.getNoPurchaseList();
        if (noPurchaseList.size() == 0) {
            return;
        }
        for (Purchase_primary purchase_primary : noPurchaseList) {
            int ySublist = mapper.getYSublist(purchase_primary);
            if (ySublist == 0) {
                //子表状态都为2
                int i = mapper.updatePurchasePrimaryStatus(purchase_primary);
                if (i == 0) {
                    System.out.println("-----------------采购状态修改失败-----------------");
                }
            }
        }
    }

    @Override
    public void delSup(Tabulation_listing listing) {
        mapper.delSup(listing);
    }

    @Override
    public List<Purchase_sublist> getSubList(List<Purchase_sublist> list) {
        List<Purchase_sublist> list1 = mapper.getSubList(list);
        return list1;
    }
}
