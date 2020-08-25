package com.system.service.Purchase;

import com.system.pojo.Purchase.DemandSummary;
import com.system.pojo.Purchase.Tabulation_listing;
import com.system.pojo.Purchase.Purchase_primary;
import com.system.pojo.Purchase.Purchase_sublist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequirementsService {
    List<Purchase_primary> fileMainTable(@Param("name") String name);

    List<Purchase_primary> fileMainTables(@Param("name") String name, @Param("page") Integer page, @Param("limit") Integer limit);

    List<Purchase_sublist> fileSubTable(Purchase_sublist purchaseSublist);

    List<Tabulation_listing> SupTable(@Param("page") Integer page, @Param("limit") Integer limit);

    List<Tabulation_listing> SupTables();

    void insertTable(Tabulation_listing formmain0030);

    List<Tabulation_listing> selectTable(@Param("sublist_id") String sublist_id, @Param("field0001") String field0001);

    List<Tabulation_listing> findSupplier(@Param("field0002") String field0002);

    void updatePrimary(Purchase_primary primary);

    void updateSublist(Purchase_sublist sublist);

    List<DemandSummary> queryP_T(@Param("id") String id);

    void updateTime(Tabulation_listing formmain0030);

    void timeUpdatePurchaseStatus();

    void delSup(Tabulation_listing listing);

    List<Purchase_sublist> getSubList(List<Purchase_sublist> list);
}
