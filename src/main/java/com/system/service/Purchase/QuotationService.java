package com.system.service.Purchase;

import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Sub_quotation;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuotationService {
    void insertMain_quotation(Main_quotation mainQuotation);

    void insertSub_quotation(Sub_quotation subQuotation);

    List<Main_quotation> whetherOrNotId(@Param("id") String id);

    List<Sub_quotation> selSubQuo(Sub_quotation subQuotation);

    void updateSubQuo(Sub_quotation subQuotation);

    String backSubquotation(List<Sub_quotation> subQuotation);

    String selSubQuoHistory(String id,String yemian,String a,String b,String c,String d,String e,String f,String proName);

    void updateBackSubquotation(Sub_quotation subQuotation);
}
