package com.system.service.impl.Purchase;

import com.system.mapper.Purchase.QuotationMapper;
import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Sub_quotation;
import com.system.service.Purchase.QuotationService;
import com.system.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {
    @Autowired
    private QuotationMapper mapper;

    @Override
    public void insertMain_quotation(Main_quotation mainQuotation) {
        mapper.insertMain_quotation(mainQuotation);
    }

    @Override
    public void insertSub_quotation(Sub_quotation subQuotation) {
        mapper.insertSub_quotation(subQuotation);
    }

    @Override
    public List<Main_quotation> whetherOrNotId(String id) {
        List<Main_quotation> list = mapper.whetherOrNotId(id);
        return list;
    }

    @Override
    public List<Sub_quotation> selSubQuo(Sub_quotation subQuotation) {
        List<Sub_quotation> list = mapper.selSubQuo(subQuotation);
        return list;
    }

    @Override
    public void updateSubQuo(Sub_quotation subQuotation) {
        mapper.updateSubQuo(subQuotation);
    }

    @Override
    public void updateUpload(Sub_quotation subQuotation) {
        mapper.updateUpload(subQuotation);
    }


    @Override
    @Transactional
    public String backSubquotation(List<Sub_quotation> subQuotations, String msg) {
        JSONUtil jsonUtil = new JSONUtil();
        for (Sub_quotation subQuotation : subQuotations) {
            subQuotation.setBid_state("1");
            subQuotation.setOffer(subQuotation.getOffer());
            subQuotation.setIssuance_date(subQuotation.getIssuance_date());
            subQuotation.setOffer_date("");
            subQuotation.setReturn_msg(msg);
            mapper.insertSub_quotation(subQuotation);
        }
        return jsonUtil.toJson("0", "", "请求成功！", "");
    }

    @Override
    public String selSubQuoHistory(String yemian, String a, String b) {
        JSONUtil jsonUtil = new JSONUtil();
        List<Sub_quotation> sub_quotations = mapper.selSubQuoHistory(yemian, a, b);
        return jsonUtil.toJson("0", sub_quotations, "请求成功！", "");
    }

    @Override
    public void updateBackSubquotation(Sub_quotation subQuotation) {
        mapper.updateBackSubquotation(subQuotation);
    }
}
