package com.system.controller.QuotationReview;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.seeyon.ctp.services.FileUploadExporter;
import com.system.pojo.Purchase.Sub_quotation;
import com.system.pojo.Purchase.Tabulation_listing;
import com.system.pojo.QuotationReview.Formmain_0076;
import com.system.pojo.QuotationReview.Formson_0077;
import com.system.pojo.QuotationReview.SummaryPush;
import com.system.pojo.Synchro.Formmain0061;
import com.system.pojo.Synchro.Formson0062;
import com.system.service.QuotationReview.AuditPushContractService;
import com.system.util.JSONUtil;
import com.system.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.protocol.https.Handler;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AuditPushContract {
    @Autowired
    private AuditPushContractService service;

    JSONUtil jsonUtil = new JSONUtil();

    MapUtil mapUtil = new MapUtil();

    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;

    @RequestMapping("SummaryPush")
    @ResponseBody
    public Map<String, Object> SummaryPush() {
        try {
            List<SummaryPush> list = new ArrayList<>();

            int n = 1000;
            List<SummaryPush> temp = service.temp();
            List<SummaryPush> pushList = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                temp.get(i).setPermissionId(String.valueOf(i + 1));
                temp.get(i).setResType("0");
                temp.get(i).setSeq("0");
                temp.get(i).setPid("0");
                List<SummaryPush> temp1 = service.temp1(temp.get(i).getItem_name(), temp.get(i).getSupplier_name());
                for (int j = 0; j < temp1.size(); j++) {
                    temp1.get(j).setPermissionId(String.valueOf(n++));
                    temp1.get(j).setPid(String.valueOf(i + 1));
                    temp1.get(j).setResType(String.valueOf(j + 1));
                    temp1.get(j).setSeq(String.valueOf(j + 1));
                }
                pushList.addAll(temp1);
            }
            list.addAll(temp);
            list.addAll(pushList);

            return mapUtil.toMap("0", "获取数据成功！", list);
        } catch (Exception e) {
            e.printStackTrace();
            return mapUtil.toMap("1", "获取数据失败！", "");
        }
    }


    @RequestMapping("test")
    @ResponseBody
    public String test(@RequestBody String data) {
        System.out.println("data:" + data);
        try {
            //获取token
            CTPServiceClientManager clientManager = CTPServiceClientManager
                    .getInstance("http://oa.tjasset.com:19997");
            client = clientManager.getRestClient();
            client.authenticate(userName, password);
            final String token = client.get("token/" + userName + "/" + password,
                    String.class, "text/plain");

            List<SummaryPush> list = JSONArray.parseArray(data, SummaryPush.class);

            List<SummaryPush> heardList = new ArrayList<>();
            List<SummaryPush> footerList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getItem_code() != null) {
                    heardList.add(list.get(i));
                } else {
                    footerList.add(list.get(i));
                }
            }
//            System.out.println("heardList:" + heardList);
//            System.out.println("footerList:" + footerList);

            Formmain_0076 formmain0076 = new Formmain_0076();
            for (int i = 0; i < heardList.size(); i++) {
                formmain0076.setField0012(heardList.get(i).getForm_code());
                formmain0076.setField0013(heardList.get(i).getForm_name());
                formmain0076.setField0015(heardList.get(i).getItem_code());
                formmain0076.setField0016(heardList.get(i).getItem_name());
                formmain0076.setField0004(heardList.get(i).getSupplier_code());
                formmain0076.setField0005(heardList.get(i).getSupplier_name());
            }
//            System.out.println("formmain0076:" + formmain0076);

            List<Formson_0077> formson0077List = new ArrayList<>();
            for (int i = 0; i < footerList.size(); i++) {
                Formson_0077 formson0077 = new Formson_0077();
                formson0077.setField0006(String.valueOf(i+1));
                formson0077.setField0029(footerList.get(i).getLine());
                formson0077.setField0044("正常");
                formson0077.setField0008(footerList.get(i).getStock_code());
                formson0077.setField0009(footerList.get(i).getStock_name());
                formson0077.setField0034(footerList.get(i).getClass_code());
                formson0077.setField0035(footerList.get(i).getStock_sort());
                formson0077.setField0036(footerList.get(i).getStation_no());
                formson0077.setField0038(footerList.get(i).getNorms());
                formson0077.setField0039(footerList.get(i).getUnit());
                formson0077.setField0040(footerList.get(i).getBrand());
                formson0077.setField0041(footerList.get(i).getSupplier_name());
                formson0077.setField0042(footerList.get(i).getDelivery_date());
                formson0077.setField0010(footerList.get(i).getQuantity());
                formson0077.setField0018(footerList.get(i).getOffer());
                formson0077.setField0025(footerList.get(i).getField0025());
                formson0077.setField0020(footerList.get(i).getRemarks());
                formson0077List.add(formson0077);
            }
//            System.out.println("list:" + formson0077List);

            formmain0076.setData(formson0077List);
//            System.out.println("formmain0076:" + formmain0076);

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:ss");
            String map = getData(formmain0076);
            System.out.println("map:" + map);
            String format = simpleDateFormat.format(date);

            Map datad = new HashMap() {
                {
                    put("templateCode", "xmcgzb001");//模板号
                    put("senderLoginName", "fz-zyh");//登录名
                    put("token", token);
                    put("subject", "采购合同中标审批单-（包荣岩" + format + "）");//标题
                    put("param", "0");
                    put("transfertype", "xml");
                    put("data", map);
                }
            };

            Long flowId1 = client.post("flow/xmcgzb001", datad, Long.class);
            System.out.println("flowId1:" + flowId1);
            String soureData = client.get("flow/data/" + flowId1, String.class);
            System.out.println(soureData);

            return jsonUtil.toJson("0", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", e.toString(), "");
        }
    }

    //导入Excel数据推送采购清单
    private String getData(Formmain_0076 formmain0076) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String data = "";
        data = "<formExport version=\"2.0\"><summary id=\"-8064008484411848695\" name=\"formmain_0076\"/><definitions/><values>\r\n" +
                "<column name=\"组织编码\"><value>" + formmain0076.getField0012() + "</value></column>"
                + "<column name=\"组织名称\"><value>" + formmain0076.getField0013() + "</value></column>\r\n"
                + "<column name=\"项目编码\"><value>" + formmain0076.getField0015() + "</value></column>"
                + "<column name=\"项目名称\"><value>" + formmain0076.getField0016() + "</value></column>\r\n"
                + "<column name=\"供应商编码\"><value>" + formmain0076.getField0004() + "</value>"
                + "</column><column name=\"供应商名称\"><value>" + formmain0076.getField0005() + "</value></column>\r\n"
                + "<column name=\"客户盖章合同附件\"><value>" + formmain0076.getField0031() + "</value></column>"
                + "</values><subForms><subForm>\r\n" +
                "<definitions>";
        for (Formson_0077 formson0077 : formmain0076.getData()) {
            data += "<column id=\"field0006\" type=\"0\" name=\"序号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0029\" type=\"0\" name=\"行号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0044\" type=\"0\" name=\"行状态\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0008\" type=\"0\" name=\"存货编码\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0009\" type=\"0\" name=\"存货名称\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0034\" type=\"0\" name=\"所属分类码\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0035\" type=\"0\" name=\"存货分类\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0036\" type=\"0\" name=\"工位号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0038\" type=\"0\" name=\"规格型号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0039\" type=\"0\" name=\"单位\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0040\" type=\"0\" name=\"品牌\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0041\" type=\"0\" name=\"供应商\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0042\" type=\"0\" name=\"期望交货日期\" isNullable=\"false\" length=\"100\"/>" +
                    "<column id=\"field0010\" type=\"0\" name=\"采购数量\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0018\" type=\"0\" name=\"单价\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0025\" type=\"0\" name=\"采购需求档案编码\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0020\" type=\"0\" name=\"备注\" isNullable=\"false\" length=\"100\"/>\r\n";
        }
        data = data + "</definitions><values>";
        for (Formson_0077 formson0077 : formmain0076.getData()) {
            data += "<row>\r\n"
                    + "<column name=\"序号\"><value>" + formson0077.getField0006() + "</value></column>"
                    + "<column name=\"行号\"><value>" + formson0077.getField0029() + "</value></column>"
                    + "<column name=\"行状态\"><value>" + formson0077.getField0044() + "</value></column>"
                    + "<column name=\"存货编码\"><value>" + formson0077.getField0008() + "</value></column>"
                    + "<column name=\"存货名称\"><value>" + formson0077.getField0009() + "</value></column>"
                    + "<column name=\"所属分类码\"><value>" + formson0077.getField0034() + "</value></column>"
                    + "<column name=\"存货分类\"><value>" + formson0077.getField0035() + "</value></column>"
                    + "<column name=\"规格型号\"><value>" + formson0077.getField0038() + "</value></column>"
                    + "<column name=\"单位\"><value>" + formson0077.getField0039() + "</value></column>"
                    + "<column name=\"品牌\"><value>" + formson0077.getField0040() + "</value></column>"
                    + "<column name=\"供应商\"><value>" + formson0077.getField0041() + "</value></column>"
                    + "<column name=\"期望交货日期\"><value>" + formson0077.getField0042() + "</value></column>"
                    + "<column name=\"采购数量\"><value>" + formson0077.getField0010() + "</value></column>"
                    + "<column name=\"单价\"><value>" + formson0077.getField0018() + "</value></column>"
                    + "<column name=\"采购需求档案编码\"><value>" + formson0077.getField0025() + "</value></column>"
                    + "<column name=\"工位号\"><value>" + formson0077.getField0036() + "</value></column>"
                    + "<column name=\"备注\"><value>" + formson0077.getField0020() + "</value></column>"
                    + "</row>";
        }
        data = data + "</values></subForm></subForms></formExport>";
        return data;
    }

    @RequestMapping("selStockName")
    @ResponseBody
    public String selStockName(String name) {
        try {
            List<Sub_quotation> list = service.selStockName(name);

            return jsonUtil.toJson("0", list, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }

    @RequestMapping("selSupName")
    @ResponseBody
    public String selSupName(String stock_id, String name) {
        try {
            List<Sub_quotation> list = service.selSupName(name, stock_id);

            return jsonUtil.toJson("0", list, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }

    @RequestMapping("WhetherOrNotSel")
    @ResponseBody
    public String WhetherOrNotSel(@RequestBody String data) {
        try {
            List<Sub_quotation> list = JSONArray.parseArray(data, Sub_quotation.class);
            if (list.get(0).getOffer() != null || list.get(0).getOffer() != ""){
                for (int i = 0; i <list.size() ; i++) {
                    Sub_quotation subQuotation = JSONObject.parseObject(
                            JSONObject.toJSONString(list.get(i)), Sub_quotation.class);
                    service.updateStateZhong(subQuotation);

                    service.updateStateWei(subQuotation);
                }

                return jsonUtil.toJson("0", "", "中标成功！", "");
            }else {
                return jsonUtil.toJson("2", "", "未填写报价！", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "中标失败！", "");
        }
    }
}