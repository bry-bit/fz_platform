package com.system.controller.QuotationReview;

import com.seeyon.client.CTPRestClient;
import com.system.service.QuotationReview.CallChangeService;
import com.system.util.JSONUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通过修改规格，进行清单变更
 */
@Controller
public class CallChange {

    JSONUtil jsonUtil = new JSONUtil();

    @Autowired
    private CallChangeService service;

    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;

    @RequestMapping("ListChangeOrder")
    @ResponseBody
    public String ListChangeOrder(@RequestBody String data) {
        try {
            System.out.println(data);
            com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(data);

            //需要修改的位置
            String strData = String.valueOf(object.getString("data"));
            JSONObject DataObject = JSONObject.fromObject(strData);
            //需求档案编码
            System.out.println(DataObject.get("field0025"));

            //修改的数据
            String strChange = String.valueOf(object.getString("change"));
            JSONObject ChangeObject = JSONObject.fromObject(strChange);


            return jsonUtil.toJson("0", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", e.toString(), "");
        }
    }

}
