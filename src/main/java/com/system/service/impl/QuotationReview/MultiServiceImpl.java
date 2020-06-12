package com.system.service.impl.QuotationReview;

import com.system.mapper.QuotationReview.MultiMapper;
import com.system.pojo.QuotationReview.Inquiry_formSub;
import com.system.service.QuotationReview.MultiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiServiceImpl implements MultiService {
    @Autowired
    private MultiMapper mapper;


    @Override
    public List<Inquiry_formSub> inquiry(Inquiry_formSub inquiryFormSub) {
        List<Inquiry_formSub> list = mapper.inquiry(inquiryFormSub);
        return list;
    }

    @Override
    public void insertSup(Inquiry_formSub inquiryFormSub) {
        mapper.insertSup(inquiryFormSub);
    }
}
