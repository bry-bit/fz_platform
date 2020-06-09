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

    int backSubquotation(Sub_quotation subQuotation);

    List<Sub_quotation> selSubQuoHistory(@Param("id")String id
            ,@Param("yemian")String yemian
                                        ,@Param("stock_code")String stock_code,
                                         @Param("stock_name")String stock_name,
                                         @Param("stock_sort")String stock_sort,
                                         @Param("norms")String norms,
                                         @Param("unit")String unit,
                                         @Param("brand")String brand,
                                         @Param("proName")String proName);

    void updateBackSubquotation(Sub_quotation subQuotation);
}
