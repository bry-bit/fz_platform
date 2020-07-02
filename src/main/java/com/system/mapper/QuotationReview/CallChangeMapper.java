package com.system.mapper.QuotationReview;

import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Purchase_sublist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallChangeMapper {
    List<Main_quotation> MasterTable(String id);

    List<Purchase_sublist> SubTable(String relation_id);
}
