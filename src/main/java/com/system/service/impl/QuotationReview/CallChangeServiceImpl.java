package com.system.service.impl.QuotationReview;

import com.system.mapper.QuotationReview.CallChangeMapper;
import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Purchase_sublist;
import com.system.service.QuotationReview.CallChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallChangeServiceImpl implements CallChangeService {
    @Autowired
    private CallChangeMapper mapper;


    @Override
    public List<Main_quotation> MasterTable(String id) {
        List<Main_quotation> list = mapper.MasterTable(id);
        return list;
    }

    @Override
    public List<Purchase_sublist> SubTable(String relation_id) {
        List<Purchase_sublist> list = mapper.SubTable(relation_id);
        return list;
    }
}
