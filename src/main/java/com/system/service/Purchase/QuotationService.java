package com.system.service.Purchase;

import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Sub_quotation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuotationService {
    void insertMain_quotation(Main_quotation mainQuotation);

    void insertSub_quotation(Sub_quotation subQuotation);

    List<Main_quotation> whetherOrNotId(@Param("id") String id);

    List<Sub_quotation> selSubQuo(Sub_quotation subQuotation);

    void updateSubQuo(Sub_quotation subQuotation);

    void updateUpload(Sub_quotation subQuotation);

    String backSubquotation(List<Sub_quotation> subQuotation, String msg);

    String selSubQuoHistory(String yemian, String a, String b);

    void updateBackSubquotation(Sub_quotation subQuotation);

    List<Main_quotation> selMainNotQuoted(Main_quotation mainQuotation);

    List<Main_quotation> selMainLiat(Main_quotation mainQuotation);
}
