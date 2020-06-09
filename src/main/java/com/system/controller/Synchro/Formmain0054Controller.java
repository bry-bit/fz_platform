package com.system.controller.Synchro;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.system.mapper.Synchro.Formmain0054Mapper;
import com.system.pojo.Synchro.Formmain0061;
import com.system.pojo.Synchro.Formson0062;
import com.system.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Formmain0054Controller {
    @Autowired
    private Formmain0054Mapper formmain0054Mapper;
    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;

    @RequestMapping("/tuisongqinggou")
    @ResponseBody
    public Object tuisongqinggou(@RequestBody String map) {
        //获取token
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance("http://oa.tjasset.com:19997");
        client = clientManager.getRestClient();
        client.authenticate(userName, password);
        final String token = client.get("token/" + userName + "/" + password,
                String.class, "text/plain");

        Formmain0061 formmain0061 = ObjectMapperUtil.toObject(map, Formmain0061.class);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = getData(formmain0061);
        String format = simpleDateFormat.format(date);

        Map datad = new HashMap() {
            {
                put("templateCode", "xmqg01");//模板号
                put("senderLoginName", "fz-zyh");//登录名
                put("token", token);
                put("subject", "采购清单审批--" + formmain0061.getField0003() + "--" + format);//标题
                put("param", "0");
                put("transfertype", "xml");
                put("data", data);
            }
        };
        Long flowId1 = client.post("flow/xmqg01", datad, Long.class);


        return "";
    }

    @RequestMapping("/zancunqinggou")
    @ResponseBody
    public Object zancunqinggou(@RequestBody String map) {
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance("http://oa.tjasset.com:19997");
        client = clientManager.getRestClient();
        client.authenticate(userName, password);
        final String token = client.get("token/" + userName + "/" + password,
                String.class, "text/plain");

        Formmain0061 formmain0061 = ObjectMapperUtil.toObject(map, Formmain0061.class);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = getData(formmain0061);
        String format = simpleDateFormat.format(date);

        Map datad = new HashMap() {
            {
                put("templateCode", "xmqg01");//模板号
                put("senderLoginName", "fz-zyh");//登录名
                put("token", token);
                put("subject", "采购清单审批--" + formmain0061.getField0003() + "--" + format);//标题
                put("param", "1");
                put("transfertype", "xml");
                put("data", data);
            }
        };
        Long flowId1 = client.post("flow/xmqg01", datad, Long.class);


        return "";
    }

    //导入Excel数据推送采购清单
    private String getData(Formmain0061 formmain0061) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String data = "";
        data = "<formExport version=\"2.0\"><summary id=\"-8064008484411848695\" name=\"formmain_0061\"/><definitions/><values>\r\n" +
                "<column name=\"组织编码\"><value>" + formmain0061.getField0012() + "</value></column>"
                + "<column name=\"组织名称\"><value>" + formmain0061.getField0013() + "</value></column>\r\n"
                + "<column name=\"项目编码\"><value>" + formmain0061.getField0015() + "</value></column>"
                + "<column name=\"项目名称\"><value>" + formmain0061.getField0016() + "</value></column>\r\n"
                + "<column name=\"导入人员\"><value>" + formmain0061.getField0020() + "</value>"
                + "</column><column name=\"导入人员部门\"><value>" + formmain0061.getField0021() + "</value></column>\r\n"
                + "<column name=\"项目采购清单编码\"><value>" + formmain0061.getField0003() + "</value></column>"
                + "</values><subForms><subForm>\r\n" +
                "<definitions>";
        //List<Formson0169> list = object.getFormson_0169();
        for (Formson0062 formson0062 : formmain0061.getData()) {
            data += "<column id=\"field0008\" type=\"0\" name=\"行号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0018\" type=\"0\" name=\"存货分类\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0012\" type=\"0\" name=\"存货编码\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0013\" type=\"0\" name=\"存货名称\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0032\" type=\"0\" name=\"规格型号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0033\" type=\"0\" name=\"单位\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0034\" type=\"0\" name=\"品牌\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0035\" type=\"0\" name=\"供应商\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0036\" type=\"0\" name=\"期望交货日期\" isNullable=\"false\" length=\"100\"/>";
        }
        data = data + "</definitions><values>";
        for (Formson0062 formson0062 : formmain0061.getData()) {
            data += "<row>\r\n"
                    + "<column name=\"行号\"><value>" + formson0062.getField0029() + "</value></column>"
                    + "<column name=\"存货分类\"><value>" + formson0062.getField0032() + "</value></column>"
                    + "<column name=\"存货编码\"><value>" + formson0062.getField0008() + "</value></column>"
                    + "<column name=\"存货名称\"><value>" + formson0062.getField0009() + "</value></column>"
                    + "<column name=\"规格型号\"><value>" + formson0062.getField0034() + "</value></column>"
                    + "<column name=\"单位\"><value>" + formson0062.getField0035() + "</value></column>"
                    + "<column name=\"品牌\"><value>" + formson0062.getField0036() + "</value></column>"
                    + "<column name=\"供应商\"><value>" + formson0062.getField0037() + "</value></column>"
                    + "<column name=\"期望交货日期\"><value>" + format + "</value></column>"
                    + "</row>";
        }
        data = data + "</values></subForm></subForms></formExport>";
        return data;
    }

}
