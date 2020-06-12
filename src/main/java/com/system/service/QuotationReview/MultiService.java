package com.system.service.QuotationReview;

import com.system.pojo.QuotationReview.Inquiry_formSub;

import java.util.List;

public interface MultiService {
    List<Inquiry_formSub> inquiry(Inquiry_formSub inquiryFormSub);

    void insertSup(Inquiry_formSub inquiryFormSub);
}
