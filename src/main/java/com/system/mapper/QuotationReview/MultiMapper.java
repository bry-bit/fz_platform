package com.system.mapper.QuotationReview;

import com.system.pojo.QuotationReview.Inquiry_formSub;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiMapper {
    List<Inquiry_formSub> inquiry(Inquiry_formSub inquiryFormSub);

    void insertSup(Inquiry_formSub inquiryFormSub);
}
