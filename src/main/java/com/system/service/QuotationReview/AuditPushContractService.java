package com.system.service.QuotationReview;

import com.system.pojo.Purchase.Sub_quotation;
import com.system.pojo.QuotationReview.SummaryPush;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditPushContractService {
    List<SummaryPush> temp();

    List<SummaryPush> temp1(@Param("item_name") String item_name, @Param("supplier_name") String supplier_name);

    List<Sub_quotation> selStockName(@Param("name") String name);

    List<Sub_quotation> selSupName(@Param("name") String name,@Param("id") String id);

    void updateStateZhong(Sub_quotation subQuotation);

    void updateStateWei(Sub_quotation subQuotation);

    String selHTDA(String id);

    void updateMesg(@Param("bid_id") String bid_id, @Param("contract_id") String contract_id, @Param("id") String id);
}
