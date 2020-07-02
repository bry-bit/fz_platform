package com.system.service.impl.QuotationReview;

import com.system.mapper.QuotationReview.AuditPushContractMapper;
import com.system.pojo.Purchase.Sub_quotation;
import com.system.pojo.QuotationReview.SummaryPush;
import com.system.service.QuotationReview.AuditPushContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditPushContractServiceImpl implements AuditPushContractService {
    @Autowired
    private AuditPushContractMapper mapper;


    @Override
    public List<SummaryPush> temp() {
        List<SummaryPush> list = mapper.temp();
        return list;
    }

    @Override
    public List<SummaryPush> temp1(String item_name, String supplier_name) {
        List<SummaryPush> list = mapper.temp1(item_name, supplier_name);
        return list;
    }

    @Override
    public List<Sub_quotation> selStockName(String name) {
        List<Sub_quotation> list = mapper.selStockName(name);
        return list;
    }

    @Override
    public List<Sub_quotation> selSupName(String name, String id) {
        List<Sub_quotation> list = mapper.selSupName(name, id);
        return list;
    }

    @Override
    public void updateStateZhong(Sub_quotation subQuotation) {
        mapper.updateStateZhong(subQuotation);
    }

    @Override
    public void updateStateWei(Sub_quotation subQuotation) {
        mapper.updateStateWei(subQuotation);
    }

    @Override
    public String selHTDA(String id) {
        return mapper.selHTDA(id);
    }

    @Override
    public void updateMesg(String bid_id, String contract_id, String id) {
        mapper.updateMesg(bid_id, contract_id, id);
    }

}
