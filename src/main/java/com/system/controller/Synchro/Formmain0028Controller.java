package com.system.controller.Synchro;

import com.system.mapper.Synchro.Formmain0028Mapper;
import com.system.mapperU8.Formmain0028U8Mapper;
import com.system.pojo.Synchro.Formmain0027U8;
import com.system.pojo.Synchro.Formmain0028;
import com.system.pojo.Synchro.Warehouse;
import com.system.util.HttpClientService;
import com.system.util.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class Formmain0028Controller {
    private String app_key = "opae31b125fd61d09ee";
    private String app_secret = "39ad49a6705245c6b2ac8306a77a63b2";
    private String from_account = "zhaoyinhe";
    private String to_account = "test_zhaoyinhe";
    private String sync = "1";
    // private String token="55d47de1bcc2459d9892ebf3d7d81fed";

    Set<Formmain0028> aa = new HashSet<>();
    Set<String> set1 = new HashSet<>();
    Set<String> set2 = new HashSet<>();
    @Autowired
    private Formmain0028Mapper formmain0028Mapper;
    @Autowired
    private Formmain0028U8Mapper formmain0028U8Mapper;
    @Autowired
    private HttpClientService httpClientService;

    @RequestMapping("test3")
    @ResponseBody
    public String warehouse() throws IOException {
        System.out.println("==========执行新增仓库档案");
        String result = "";
        String result1 = "";
        String result2 = "";

        TestController testController = new TestController();
        String s = testController.add();
        String at = JSONObject.fromObject(s).getString("token");
        // System.out.println("token==="+at);
        String id = JSONObject.fromObject(at).getString("id");
        System.out.println("id================" + id);

        String st = HttpRequest.sendGet("https://api.yonyouup.com/api/warehouse/batch_get", "from_account=" + from_account
                + "&app_key=" + app_key + "&to_account=" + to_account + "&token=" + id);
        System.out.println("st==============" + st);

        String warehouse = JSONObject.fromObject(st).getString("warehouse");
        JSONArray jsonArray = JSONArray.fromObject(warehouse);

        List<Formmain0028> list1 = formmain0028Mapper.selectformmain0028();//OA
        System.out.println("list1========" + list1);List<Warehouse> u8list=formmain0028U8Mapper.selectFormmain0028U8();
        System.out.println("u8list========" + u8list);

        //OA是四个，U8两个
        for (Formmain0028 formmain0028 : list1) {
            System.out.println("获取OA编码：" + formmain0028);
            for (Warehouse warehouse1 : u8list) {
               // System.out.println("1111111"+warehouse1.getcWhcode());
                String code = formmain0028Mapper.selectList(warehouse1.getCWhcode());
                //System.out.println("66====="+code);
                if (code != "") {
                    set1.add(code);

                }
            }
            set2.add(formmain0028.getField0001());
        }
       // System.out.println("set2===="+set2);
       // System.out.println("set===="+set1);
        set2.removeAll(set1);
        //判断集合是否为空,为空则OA-U8档案同步不需要新增，否则执行新增


        System.out.println("差集是==== " + set2); //差集是 [a, b]
        if(set2.isEmpty()){
            return "无新增档案";
        }else {
        for (Formmain0028 formmain0028 : list1) {
            String[] array = new String[set2.size()];
            set2.toArray(array);
            System.out.println("转换==== " + set2.toArray(array)); //差集是 [a, b]
            for (int i = 0; i < array.length; i++) {
                if (formmain0028.getField0001().equals(array[i])) {
                    System.out.println("转换==== " + array[i]);
                    aa.add(formmain0028);
                }

            }
        }

        for (Formmain0028 formmain0028 : aa) {

            //String id1 = JSONObject.fromObject(at).getString("id");
            String ss = HttpRequest.sendGet("https://api.yonyouup.com/system/tradeid", "from_account=" + from_account
                    + "&app_key=" + app_key + "&token=" + id);
            String gg = JSONObject.fromObject(ss).getString("trade");
            // System.out.println("token==="+at);
            String tradeid = JSONObject.fromObject(gg).getString("id");
            System.out.println("tradeid==============" + tradeid);
            Formmain0027U8 formmain0064u9 = new Formmain0027U8();
            formmain0064u9.setCode(formmain0028.getField0001());
            formmain0064u9.setName(formmain0028.getField0003());
            System.out.println("for======" + formmain0064u9);
            String url = "https://api.yonyouup.com/api/warehouse/add?from_account=" + from_account
                    + "&to_account=" + to_account + "&app_key=" + app_key + "&token=" + id + "&sync=" + sync
                    + "&tradeid=" + tradeid;

            String parm = "{\"warehouse\":{\"code\":\"" + formmain0064u9.getCode() + "\",\"name\":\"" + formmain0064u9.getName() + "\"}}";

            JSONObject jsonObject = JSONObject.fromObject(parm);
            String post = HttpRequest.post(jsonObject, url);
            System.out.println("post===============" + post);
            String errmsg = JSONObject.fromObject(post).getString("errmsg");
            System.out.println("errmsg===============" + errmsg);
            if (errmsg.equals("")) {
                String code = JSONObject.fromObject(post).getString("id");
                formmain0028Mapper.updateFormmain0028Field0001(formmain0028.getField0001(), code);

            } else {
                formmain0028Mapper.updateFormmain0028Syn(formmain0028.getField0001(), errmsg);

            }

        }

    }
        return "成功";
    }

}
