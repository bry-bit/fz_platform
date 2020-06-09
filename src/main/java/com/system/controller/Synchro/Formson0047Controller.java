package com.system.controller.Synchro;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.system.mapper.Synchro.Formmain0027Mapper;
import com.system.mapper.Synchro.Formmain0047TempMapper;
import com.system.mapper.Synchro.Formson0062Mapper;
import com.system.mapperU8.Formmain0027U8Mapper;
import com.system.pojo.Synchro.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Formson0047Controller {

    @Autowired
    private Formmain0027Mapper formmain0027Mapper;
    @Autowired
    private Formson0062Mapper formson0062Mapper;
    @Resource
    private Formmain0047TempMapper formmain0047TempMapper;
    @Autowired
    private Formmain0027U8Mapper formmain0027U8Mapper;
    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;
    Set<Formson0062> aa = new HashSet<>();
    Set<String> set1 = new HashSet<>();
    Set<String> set2 = new HashSet<>();

    //接受BOM导入数据

    @ResponseBody
    public Object updatePdrecode(@RequestBody String map) throws NullPointerException{
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance("http://oa.tjasset.com:19997");
        client = clientManager.getRestClient();
        client.authenticate(userName, password);
        final String token = client.get("token/" + userName + "/" + password,
                String.class, "text/plain");
        System.out.println("map=======" + map);
        System.out.println(formmain0047TempMapper.selectFormmain0047(map));
        List<Formson_0047_temp> formson_0047_temps = formmain0047TempMapper.selectFormmain0047(map);//当前项目下的对应行数据
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String data = getData(formmain0061);
        String format = simpleDateFormat.format(date);
        List<Formmain0027> list1 = formmain0027Mapper.selectformmain0027();///存货档案
        for (Formson_0047_temp formson_0047_temp : formson_0047_temps) {
            String ID = formson_0047_temp.getID();
            String field0038 = formson_0047_temp.getField0038();//存货分类
            String field0032 = formson_0047_temp.getField0032();//规格型号
            String field0034 = formson_0047_temp.getField0034();//品牌
            String field0001 = formmain0027Mapper.selectfield0001(field0038, field0032, field0034);
            if (field0001 != "") {
                formmain0047TempMapper.updatefield0012(field0001, ID);
               // set1.add(ID);//通过对比两个集合code不为空存到集合set1
            }
        }
        List<Formson_0047_temp> formson0047 = formmain0047TempMapper.selectEmptyfield0012(map);//根据ID找到存货编码为空的数据新增存货档案
        //      List< Inventory > u8list=formmain0027U8Mapper.selectFormmain0027U8();
        Integer code = formmain0027U8Mapper.selectMaxcode();
        for (Formson_0047_temp formson_0047_temp : formson0047) {
            String id=formson_0047_temp.getID();
            code=code+1;
            String code1=code.toString();
            System.out.println(code1);
            List<Formmain_0046_temp> formmain0046 = formmain0047TempMapper.selectformmain0046(map);
            Formmain0063 formmain0063 = new Formmain0063();
            formmain0063.setField0010(formmain0046.get(0).getField0005());//组织编码
            formmain0063.setField0011(formmain0046.get(0).getField0006());//组织名称
            formmain0063.setField0001(code1);//存货编码
            formmain0063.setField0003(formson_0047_temp.getField0013());//存货名称
            formmain0063.setField0004(formson_0047_temp.getField0032());//所属分类编码
            formmain0063.setField0024(formson_0047_temp.getField0032());//存货分类
            formmain0063.setField0026(formson_0047_temp.getField0032());//规格型号
            formmain0063.setField0027(formson_0047_temp.getField0034());//品牌
            formmain0063.setField0005(formson_0047_temp.getField0033());//计量单位组编码
            formmain0063.setField0006(formson_0047_temp.getField0033());//计量单位组类型
            formmain0063.setField0007(formson_0047_temp.getField0033());//主计量单位
            formmain0063.setField0008(formson_0047_temp.getField0033());//inv存货编码

            String data1 = getData1(formmain0063);
            Map datad1 = new HashMap() {
                {
                    put("templateCode", "chdaxz001");//模板号
                    put("senderLoginName", "fz-bry");//登录名
                    put("token", token);
                    put("subject", "存货档案新增申请--" + formson_0047_temp.getField0013() + "--" + format);//标题
                    put("param", "0");
                    put("transfertype", "xml");
                    put("data", data1);
                }
            };

            Long flowId = client.post("flow/chdaxz001", datad1, Long.class);
            System.out.println("flowId1=====" + flowId);
            formmain0047TempMapper.updatefield00121(id,code);//修改中间表推送成功存货编码

        }
        List<Formmain_0046_temp> formmain0046 = formmain0047TempMapper.selectformmain0046(map);//当前档案已同步完成
        List<Formson_0047_temp> formson00471 = formmain0047TempMapper.selectEmptyfield00121(map);//根据Id找到当前下的档案是否有空编码
        List<Formson_0047_temp> formson00472 = formmain0047TempMapper.selectEmptyfield00122(map);//找到不为空集合
        System.out.println(formson00471.size());
        if(formson00471.isEmpty()){
            //String inventoryResultStr = JSON.toJSONString(formmain0046);
           // System.out.println("inventoryResultStr========"+inventoryResultStr);
            //Formmain0061 formmain0061 = ObjectMapperUtil.toObject(inventoryResultStr,Formmain0061.class);
            String data = getData(map);
            System.out.println("data======="+data);
            Map datad = new HashMap() {
                {
                    put("templateCode", "xmcgqd001");//模板号
                    put("senderLoginName", "fz-bry");//登录名
                    put("token", token);
                    put("subject", "采购清单审批--"+formmain0046.get(0).getField0005()+"--"+format);//标题
                    put("param", "0");
                    put("transfertype","xml");
                    put("data", data);
                }
            };

            Long flowId1 = client.post("flow/xmcgqd001", datad, Long.class);
            System.out.println("MAP====="+map);
            System.out.println("flowId1====="+flowId1);
            //System.out.println("excelZhu==========="+formmain0061);
            try {
                Thread.sleep(10000);
                formmain0047TempMapper.Updatefiel0023(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


           // String gg=formmain0047TempMapper.selectfield0023(map);



          //  formmain0047TempMapper.Updatefiel0023(map);

            return "成功";

        }else {
            System.out.println("档案未同步成功");

        }
        return "成功";
    }

    //导入Excel数据推送采购清单
    private String getData(String map) {
        String hangzhuangtai ="正常";
        String danwei="个";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String data = "";
        List<Formson_0047_temp> formson00472 = formmain0047TempMapper.selectEmptyfield00122(map);
        List<Formmain_0046_temp> formmain0046 = formmain0047TempMapper.selectformmain0046(map);

            data = "<formExport version=\"2.0\"><summary id=\"-8064008484411848695\" name=\"formmain_0061\"/><definitions/><values>\r\n" +
                    "<column name=\"组织编码\"><value>" + formmain0046.get(0).getField0005() + "</value></column>"
                    + "<column name=\"组织名称\"><value>" + formmain0046.get(0).getField0006() + "</value></column>\r\n"
                    + "<column name=\"项目编码\"><value>" + formmain0046.get(0).getField0001() + "</value></column>"
                    + "<column name=\"项目名称\"><value>" + formmain0046.get(0).getField0003() + "</value></column>\r\n"
                    + "<column name=\"导入人员\"><value>" + formmain0046.get(0).getField0020() + "</value>"
                    + "</column><column name=\"导入人员部门\"><value>" + formmain0046.get(0).getField0021() + "</value></column>\r\n"
                    + "<column name=\"项目采购清单审批单流水号\"><value>" + map + "</value></column>"
                    + "</values><subForms><subForm>\r\n" +
                    "<definitions>";
        for (Formson_0047_temp formson_0047_temp :formson00472) {
            data += "<column id=\"field0008\" type=\"0\" name=\"行号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0018\" type=\"0\" name=\"行状态\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0012\" type=\"0\" name=\"存货编码\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0013\" type=\"0\" name=\"存货名称\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0032\" type=\"0\" name=\"规格型号\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0033\" type=\"0\" name=\"单位\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0034\" type=\"0\" name=\"品牌\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0035\" type=\"0\" name=\"供应商\" isNullable=\"false\" length=\"100\"/>\r\n" +
                    "<column id=\"field0036\" type=\"0\" name=\"期望交货日期\" isNullable=\"false\" length=\"100\"/>";
        }
        data = data + "</definitions><values>";
        for (Formson_0047_temp formson_0047_temp :formson00472) {

            data += "<row>\r\n"
                    + "<column name=\"行号\"><value>"+formson_0047_temp.getField0008()+"</value></column>"
                    + "<column name=\"行状态\"><value>"+hangzhuangtai+"</value></column>"
                    + "<column name=\"存货编码\"><value>"+formson_0047_temp.getField0012()+"</value></column>"
                    + "<column name=\"存货名称\"><value>"+formson_0047_temp.getField0013()+"</value></column>"
                    + "<column name=\"规格型号\"><value>"+formson_0047_temp.getField0032()+"</value></column>"
                    + "<column name=\"单位\"><value>"+danwei+"</value></column>"
                    + "<column name=\"品牌\"><value>"+formson_0047_temp.getField0034()+"</value></column>"
                    + "<column name=\"供应商\"><value>"+formson_0047_temp.getField0035()+"</value></column>"
                    + "<column name=\"期望交货日期\"><value>"+format+"</value></column>"
                    +"</row>";
        }
        data = data + "</values></subForm></subForms></formExport>";
        return data;
    }

    ///新增存货档案
    private String getData1(Formmain0063 formmain0063) {
        String data1 = "";
        String a = "0";
        String b = "套";
        data1 = "<formExport version=\"2.0\">"
                + "<summary id=\"-3411047740521758143\" name=\"formmain_0352\"/>"
                + "<definitions/><values>"
                + "<column name=\"组织编码\"><value>" + formmain0063.getField0010() + "</value>"
                + "</column><column  name=\"组织名称\"><value>" + formmain0063.getField0011() + "</value>"
                + "</column><column  name=\"存货编码\"><value>" + formmain0063.getField0001() + "</value>"
                + "</column><column name=\"存货名称\"><value>" + formmain0063.getField0018() + "</value>"
                + "</column><column  name=\"所属分类编码\"><value>" + formmain0063.getField0004() + "</value>"
                + "</column><column  name=\"存货分类\"><value>" + formmain0063.getField0024() + "</value>"
                + "</column><column  name=\"规格型号\"><value>" + formmain0063.getField0026() + "</value>"
                + "</column><column  name=\"品牌\"><value>" + formmain0063.getField0027() + "</value>"
                + "</column><column  name=\"计量单位组编码\"><value>" + formmain0063.getField0005() + "</value>"
                + "</column><column  name=\"计量单位组类型\"><value>" + a + "</value>"
                + "</column><column  name=\"主计量单位\"><value>" + b + "</value>"
                + "</column><column  name=\"inv存货编码\"><value>" + formmain0063.getField0008() + "</value>"
                + "</column></values></formExport>";

        return data1;

    }
}
