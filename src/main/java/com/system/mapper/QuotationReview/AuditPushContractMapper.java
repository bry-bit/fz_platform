package com.system.mapper.QuotationReview;

import com.system.pojo.Purchase.Sub_quotation;
import com.system.pojo.QuotationReview.SummaryPush;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditPushContractMapper {
    List<SummaryPush> temp();

    List<SummaryPush> temp1(@Param("item_name") String item_name, @Param("supplier_name") String supplier_name);

    List<Sub_quotation> selStockName(@Param("name") String name);

    List<Sub_quotation> selSupName(@Param("name") String name, @Param("id") String id);

    void updateStateZhong(Sub_quotation subQuotation);

    void updateStateWei(Sub_quotation subQuotation);
}
