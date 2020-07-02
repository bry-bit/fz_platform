package com.system.service.QuotationReview;

import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Purchase_sublist;

import java.util.List;

public interface CallChangeService {
    List<Main_quotation> MasterTable(String id);

    List<Purchase_sublist> SubTable(String relation_id);
}
