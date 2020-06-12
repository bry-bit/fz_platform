package com.system.controller.QuotationReview;

import com.system.pojo.QuotationReview.Inquiry_formSub;
import com.system.service.QuotationReview.MultiService;
import com.system.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Multi {
    @Autowired
    private MultiService service;

    JSONUtil jsonUtil = new JSONUtil();

    @RequestMapping("showMulti")
    @ResponseBody
    public String showMulti(String name) {
        try {
            Inquiry_formSub formSub = new Inquiry_formSub();
            formSub.setName(name);

            List<Inquiry_formSub> list = service.inquiry(formSub);
            return jsonUtil.toJson("0", list, "", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "", "");
        }
    }
}
