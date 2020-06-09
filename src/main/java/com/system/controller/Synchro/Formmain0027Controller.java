package com.system.controller.Synchro;

import com.system.mapper.Synchro.Formmain0027Mapper;
import com.system.mapperU8.Formmain0027U8Mapper;
import com.system.pojo.Synchro.Formmain0027;
import com.system.pojo.Synchro.Formmain0027U8;
import com.system.pojo.Synchro.Inventory;
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
public class Formmain0027Controller {
    private String app_key = "opae31b125fd61d09ee";
    private String app_secret = "39ad49a6705245c6b2ac8306a77a63b2";
    private String from_account = "zhaoyinhe";
    private String to_account = "test_zhaoyinhe";
    // private String token="55d47de1bcc2459d9892ebf3d7d81fed";

    Set<com.system.pojo.Synchro.Formmain0027> aa = new HashSet<>();
    Set<String> set1 = new HashSet<>();
    Set<String> set2 = new HashSet<>();
    // System.out.println("id==="+id);
    @Autowired
    private Formmain0027Mapper formmain0027Mapper;
    @Autowired
    private Formmain0027U8Mapper formmain0027U8Mapper;
    @Autowired
    private HttpClientService httpClientService;

    @RequestMapping("test1")
    @ResponseBody
    //通过比对得到U8中未存在的存货档案
    public String Addinvertory() throws IOException {
        TestController testController = new TestController();
        String s = testController.add();
        String at = JSONObject.fromObject(s).getString("token");
        // System.out.println("token==="+at);
        String id = JSONObject.fromObject(at).getString("id");
        String result = "";
        String result1 = "";
        String result2 = "";
//        String ss = HttpRequest.sendGet("https://api.yonyouup.com/system/tradeid", "from_account=" + from_account
//                + "&app_key=" + app_key + "&token=" + id);
//        String gg = JSONObject.fromObject(ss).getString("trade");
//        // System.out.println("token==="+at);
//        String tradeid = JSONObject.fromObject(gg).getString("id");
        System.out.println("id===" + id);
        String st = HttpRequest.sendGet("https://api.yonyouup.com/api/inventory/batch_get", "from_account=" + from_account
                + "&app_key=" + app_key + "&to_account=" + to_account + "&token=" + id);
        System.out.println(st);
        String inventory = JSONObject.fromObject(st).getString("inventory");
        JSONArray jsonArray = JSONArray.fromObject(inventory);

//        String转List，用到 JSONObject 的静态方法 parseArray
        List<Formmain0027> list1 = formmain0027Mapper.selectformmain0027();
        System.out.println("list1========" + list1);
        List<Inventory> u8list = formmain0027U8Mapper.selectFormmain0027U8();
        System.out.println("u8list======" + u8list);
        for (Formmain0027 formmain0027 : list1) {//OAlist集合
            // System.out.println("U8存货编码====" + formmain0027U8.getCode());
            System.out.println("OA存货编码=====" + formmain0027.getField0001());
            for (Inventory inventory1 : u8list) {
                String code = formmain0027Mapper.selectList(inventory1.getCInvCode());
                if (code != "") {
                    set1.add(code);//通过对比两个集合code不为空存到集合set1

                }
            }
            set2.add(formmain0027.getField0001());//将每次遍历的OA集合code存到set2集合

        }
        set2.removeAll(set1);//从集合set2中去除set1的重复值，这样就能得到OA中与U8中不同的档案编码
        System.out.println("差集是==== " + set2); //差集是 [a, b]
        if (set2.isEmpty()) {
            return "无新增档案";
        } else {
            for (Formmain0027 formmain0027 : list1) {
                String[] array = new String[set2.size()];
                set2.toArray(array);
                System.out.println("转换==== " + set2.toArray(array)); //差集是 [a, b]
                for (int i = 0; i < array.length; i++) {
                    if (formmain0027.getField0001().equals(array[i])) {
                        System.out.println("转换==== " + array[i]);
                        aa.add(formmain0027);
                    }

                }
            }
            for (Formmain0027 formmain0027 : aa) {
                String ss = HttpRequest.sendGet("https://api.yonyouup.com/system/tradeid", "from_account=" + from_account
                        + "&app_key=" + app_key + "&token=" + id);
                String gg = JSONObject.fromObject(ss).getString("trade");
                // System.out.println("token==="+at);
                String tradeid = JSONObject.fromObject(gg).getString("id");
                System.out.println("tradeid==============" + tradeid);
                Formmain0027U8 formmain0064u9 = new Formmain0027U8();
                formmain0064u9.setCode(formmain0027.getField0001());
                formmain0064u9.setName(formmain0027.getField0003());
                formmain0064u9.setSort_code(formmain0027.getField0004());
                formmain0064u9.setUnitgroup_code(formmain0027.getField0005());
                formmain0064u9.setUnitgroup_type(formmain0027.getField0006());
                formmain0064u9.setMain_measure(formmain0027.getField0007());
                formmain0064u9.setSale_flag(formmain0027.getField0017());
                formmain0064u9.setSelfmake_flag(formmain0027.getField0022());
                String url = "https://api.yonyouup.com/api/inventory/add?from_account=" + from_account
                        + "&to_account=" + to_account + "&app_key=" + app_key + "&token=" + id
                        + "&tradeid=" + tradeid;
                String parm = "{\"inventory\":{\"code\":\"" + formmain0064u9.getCode() + "\",\"name\":\"" + formmain0064u9.getName() + "\",\"sort_code\":\"" + formmain0064u9.getSort_code() + "\"," +
                        "\"main_measure\":\"" + formmain0064u9.getMain_measure() + "\",\"sale_flag\":\"" + formmain0064u9.getSale_flag() + "\",\"unitgroup_code\":\"" + formmain0064u9.getUnitgroup_code() + "\",\"unitgroup_type\":\"" + formmain0064u9.getUnitgroup_type() + "\",\"selfmake_flag\":\"" + formmain0064u9.getSelfmake_flag() + "\"" +
                        ",\"entry\":[{\"invcode\":\"" + formmain0064u9.getCode() + "\"}]}}";
                JSONObject jsonObject = JSONObject.fromObject(parm);
                String post = HttpRequest.post(jsonObject, url);
                String url1 = JSONObject.fromObject(post).getString("url");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String tt = httpClientService.doGet(url1);
                System.out.println("tt========" + tt);
                String errmsg = JSONObject.fromObject(tt).getString("errmsg");
                System.out.println("post===============" + errmsg);
//                String errmsg = JSONObject.fromObject(post).getString("errmsg");
                System.out.println("errmsg===============" + errmsg);
                if (errmsg.equals("")) {
                    String code = JSONObject.fromObject(post).getString("id");
                    formmain0027Mapper.updateFormmain0027Field0001(formmain0027.getField0001(), code);

                } else {
                    formmain0027Mapper.updateFormmain0027Syn(formmain0027.getField0001(), errmsg);

                }


            }
        }
        return "成功";

    }


}
