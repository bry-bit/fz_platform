package com.system.controller.QuotationReview;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Purchase_sublist;
import com.system.pojo.QuotationReview.Formmain_0079;
import com.system.pojo.QuotationReview.Formson_0080;
import com.system.service.QuotationReview.CallChangeService;
import com.system.util.JSONUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

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
            //获取token
            CTPServiceClientManager clientManager = CTPServiceClientManager
                    .getInstance("http://oa.tjasset.com:19997");
            client = clientManager.getRestClient();
            client.authenticate(userName, password);
            final String token = client.get("token/" + userName + "/" + password,
                    String.class, "text/plain");

            com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(data);

            //需要修改的位置
            String strData = String.valueOf(object.getString("data"));
            JSONObject DataObject = JSONObject.fromObject(strData);
            System.out.println(DataObject.get("relation_id"));

            //修改的数据
            String strChange = String.valueOf(object.getString("change"));
            JSONObject ChangeObject = JSONObject.fromObject(strChange);

            Formmain_0079 formmain_0079 = new Formmain_0079();
            List<Main_quotation> MainList = service.MasterTable(String.valueOf(DataObject.get("relation_id")));
            System.out.println(MainList);
            for (int i = 0; i < MainList.size(); i++) {
                formmain_0079.setField0023(MainList.get(i).getField0029());
                formmain_0079.setField0005(MainList.get(i).getForm_code());
                formmain_0079.setField0006(MainList.get(i).getForm_name());
                formmain_0079.setField0001(MainList.get(i).getItem_code());
                formmain_0079.setField0003(MainList.get(i).getItem_name());
                formmain_0079.setField0020(MainList.get(i).getName());
                formmain_0079.setField0021(MainList.get(i).getField0025());
                formmain_0079.setField0055(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
                formmain_0079.setField0047("XMCGBG" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "00005");
                System.out.println(formmain_0079);
            }

            List<Formson_0080> formson_0080List = new ArrayList<>();
            List<Purchase_sublist> subList = service.SubTable(String.valueOf(DataObject.get("relation_id")));
            for (int i = 0; i < subList.size(); i++) {
                Formson_0080 formson_0080 = new Formson_0080();
                formson_0080.setField0008(subList.get(i).getOrders());
                formson_0080.setField0010(subList.get(i).getLine());
                formson_0080.setField0018(subList.get(i).getEnable_status());
                formson_0080.setField0012(subList.get(i).getStock_code());
                formson_0080.setField0013(subList.get(i).getStock_name());
                formson_0080.setField0038(subList.get(i).getClass_code());
                formson_0080.setField0039(subList.get(i).getStock_sort());
                formson_0080.setField0041(subList.get(i).getStation_no());
                if (subList.get(i).getId() == DataObject.get("id") ||
                        subList.get(i).getId().equals(DataObject.get("id"))) {
                    formson_0080.setField0032(String.valueOf(ChangeObject.get("norms")));
                    formson_0080.setField0053("正常");
                } else {
                    formson_0080.setField0032(subList.get(i).getNorms());
                    formson_0080.setField0053(subList.get(i).getEnable_status());
                }
                formson_0080.setField0033(subList.get(i).getUnit());
                formson_0080.setField0034(subList.get(i).getBrand());
                formson_0080.setField0035(String.valueOf(DataObject.get("supplier_name")));
                formson_0080.setField0036(subList.get(i).getDelivery_date().split(" ")[0]);
                formson_0080.setField0014(subList.get(i).getQuantity());
//                subList.get(i).getQuantity()
                formson_0080List.add(formson_0080);
            }
            formmain_0079.setData(formson_0080List);

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String map = getData(formmain_0079);
            System.out.println("map:" + map);
            String format = simpleDateFormat.format(date);


            Map datad = new HashMap() {
                {
                    put("templateCode", "mskdjei");//模板号
                    put("senderLoginName", "fz-bry");//登录名
                    put("token", token);
                    put("subject", "项目采购清单变更审批单-测试(" + formmain_0079.getField0050() + " " + format + "）");//标题
                    put("param", "0");
                    put("transfertype", "json");
                    put("data", map);
                }
            };

            Long flowId1 = client.post("flow/mskdjei", datad, Long.class);
            System.out.println("flowId1:" + flowId1);

            //判断修改的理由状态
            if (ChangeObject.get("reason") == "更正错误" || ChangeObject.get("reason").equals("更正错误")) {

            } else {
                System.out.println("2");
            }

            return jsonUtil.toJson("0", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", e.toString(), "");
        }
    }

    //导入Excel数据推送采购清单
    private String getData(Formmain_0079 formmain0079) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String data = "";
//        data = "<formExport version=\"2.0\"><summary id=\"-8064008484411848697\" name=\"formmain_0079\"/><definitions/><values>" +
//                "<column name=\"项目采购清单变更审批单流水号\"><value>" + formmain0079.getField0047() + "</value></column>"
//                + "<column name=\"项目采购清单编码\"><value>" + formmain0079.getField0023() + "</value></column>"
//                + "<column name=\"组织编码\"><value>" + formmain0079.getField0005() + "</value></column>"
//                + "<column name=\"组织名称\"><value>" + formmain0079.getField0006() + "</value></column>"
//                + "<column name=\"项目编码\"><value>" + formmain0079.getField0001() + "</value></column>"
//                + "<column name=\"项目名称\"><value>" + formmain0079.getField0003() + "</value></column>"
//                + "<column name=\"导入人员\"><value>" + formmain0079.getField0020() + "</value></column>"
//                + "<column name=\"导入人员部门\"><value>" + formmain0079.getField0021() + "</value>"
//                + "</column><column name=\"导入时间\"><value>" + formmain0079.getField0055() + "</value></column>"
//                + "</values><subForms><subForm>" +
//                "<definitions>";
//        for (Formson_0080 formson0080 : formmain0079.getData()) {
//            data += "<column id=\"field0010\" type=\"0\" name=\"行号\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0018\" type=\"0\" name=\"行状态\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0012\" type=\"0\" name=\"存货编码\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0013\" type=\"0\" name=\"存货名称\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0038\" type=\"0\" name=\"所属分类码\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0039\" type=\"0\" name=\"存货分类\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0041\" type=\"0\" name=\"工位号\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0032\" type=\"0\" name=\"规格型号\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0033\" type=\"0\" name=\"单位\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0034\" type=\"0\" name=\"品牌\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0035\" type=\"0\" name=\"供应商\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0036\" type=\"0\" name=\"期望交货日期\" isNullable=\"false\" length=\"100\"/>" +
//                    "<column id=\"field0014\" type=\"0\" name=\"需求数量\" isNullable=\"false\" length=\"100\"/>\r\n" +
//                    "<column id=\"field0053\" type=\"0\" name=\"变更后行状态\" isNullable=\"false\" length=\"100\"/>\r\n";
//        }
//        data = data + "</definitions><values></values></subForm></subForms></formExport>";

//        for (Formson_0080 formson0080 : formmain0079.getData()) {
//            data += "<row>\r\n"
//                    + "<column name=\"行号\"><value>" + formson0080.getField0010() + "</value></column>"
//                    + "<column name=\"行状态\"><value>" + formson0080.getField0018() + "</value></column>"
//                    + "<column name=\"存货编码\"><value>" + formson0080.getField0012() + "</value></column>"
//                    + "<column name=\"存货名称\"><value>" + formson0080.getField0013() + "</value></column>"
//                    + "<column name=\"所属分类码\"><value>" + formson0080.getField0038() + "</value></column>"
//                    + "<column name=\"存货分类\"><value>" + formson0080.getField0039() + "</value></column>"
//                    + "<column name=\"工位号\"><value>" + formson0080.getField0041() + "</value></column>"
//                    + "<column name=\"规格型号\"><value>" + formson0080.getField0032() + "</value></column>"
//                    + "<column name=\"单位\"><value>" + formson0080.getField0033() + "</value></column>"
//                    + "<column name=\"品牌\"><value>" + formson0080.getField0034() + "</value></column>"
//                    + "<column name=\"供应商\"><value>" + formson0080.getField0035() + "</value></column>"
//                    + "<column name=\"期望交货日期\"><value>" + formson0080.getField0036() + "</value></column>"
//                    + "<column name=\"需求数量\"><value>" + formson0080.getField0014() + "</value></column>"
//                    + "<column name=\"变更后行状态\"><value>" + formson0080.getField0053() + "</value></column>"
//                    + "</row>";
//        }
//        data = data + "</values></subForm></subForms></formExport>";
        return data;
    }

}
