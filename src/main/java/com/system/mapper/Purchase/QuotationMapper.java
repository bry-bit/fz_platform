package com.system.mapper.Purchase;

import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Sub_quotation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationMapper {
    void insertMain_quotation(Main_quotation mainQuotation);

    void insertSub_quotation(Sub_quotation subQuotation);

    List<Main_quotation> whetherOrNotId(@Param("id") String id);

    List<Sub_quotation> selSubQuo(Sub_quotation subQuotation);

    void updateSubQuo(Sub_quotation subQuotation);

    void updateUpload(Sub_quotation subQuotation);

    int backSubquotation(Sub_quotation subQuotation);

    List<Sub_quotation> selSubQuoHistory(@Param("yemian") String yemian
            , @Param("stock_name") String stock_name
            , @Param("norms") String norms);

    void updateBackSubquotation(Sub_quotation subQuotation);

    List<Main_quotation> selMainNotQuoted(Main_quotation mainQuotation);

    List<Main_quotation> selMainLiat(Main_quotation mainQuotation);
}
