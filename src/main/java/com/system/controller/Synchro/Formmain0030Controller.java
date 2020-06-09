package com.system.controller.Synchro;

import com.system.mapper.Synchro.Formmain0030Mapper;
import com.system.mapperU8.Formmain0030U8Mapper;
import com.system.pojo.Synchro.Formmain0030;
import com.system.pojo.Synchro.Formmain0030U8;
import com.system.pojo.Synchro.Vendor;
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
public class Formmain0030Controller {
    @Autowired
    private Formmain0030Mapper formmain0030Mapper;
    @Autowired
    private Formmain0030U8Mapper formmain0030U8Mapper;
    @Autowired
    private HttpClientService httpClientService;
    private String app_key = "opae31b125fd61d09ee";
    private String app_secret = "39ad49a6705245c6b2ac8306a77a63b2";
    private String from_account = "zhaoyinhe";
    private String to_account = "test_zhaoyinhe";

    Set<Formmain0030> aa = new HashSet<>();
    Set<String> set1 = new HashSet<>();
    Set<String> set2 = new HashSet<>();

    @RequestMapping("test2")
    @ResponseBody
    public String vendor() throws IOException {
        TestController testController = new TestController();
        String s = testController.add();
        String at = JSONObject.fromObject(s).getString("token");
        // System.out.println("token==="+at);
        String id = JSONObject.fromObject(at).getString("id");
        System.out.println("token==========" + id);
        String result = "";
        String result1 = "";
        String result2 = "";
        System.out.println("id===" + id);
//通过接口批量获取供应商档案
        String st = HttpRequest.sendGet("https://api.yonyouup.com/api/vendor/batch_get", "from_account=" + from_account
                + "&app_key=" + app_key + "&to_account=" + to_account + "&token=" + id);
        System.out.println(st);


        String vendor = JSONObject.fromObject(st).getString("vendor");
        JSONArray jsonArray = JSONArray.fromObject(vendor);
        //        String转List，用到 JSONObject 的静态方法 parseArray
        List<Formmain0030> list1 = formmain0030Mapper.selectformmain0030();
        System.out.println("list1========" + list1);
        List<Formmain0030U8> list = com.alibaba.fastjson.JSONObject.parseArray(jsonArray.toString(), Formmain0030U8.class);
        // Formmain0027U8 object = ObjectMapperUtil.toObject(list.toString(), Formmain0027U8.class);
        List<Vendor> u8list = formmain0030U8Mapper.selectFormmain0030U8();
        System.out.println("list========" + list);
        // System.out.println("object========"+object);

        for (Formmain0030 formmain0030 : list1) {//OAlist集合
            // System.out.println("U8存货编码====" + formmain0027U8.getCode());
            System.out.println("OA存货编码=====" + formmain0030.getField0001());
            for (Vendor vendor1 : u8list) {
                String code = formmain0030Mapper.selectList(vendor1.getCVCCode());
                if (code != "") {
                    set1.add(code);

                }
            }
            set2.add(formmain0030.getField0001());

        }
        set2.removeAll(set1);
        System.out.println("差集是==== " + set2); //差集是 [a, b]
        if (set2.isEmpty()) {
            return "无新增档案";
        } else {
            for (Formmain0030 formmain0030 : list1) {
                String[] array = new String[set2.size()];
                set2.toArray(array);
                System.out.println("转换==== " + set2.toArray(array)); //差集是 [a, b]
                for (int i = 0; i < array.length; i++) {
                    if (formmain0030.getField0001().equals(array[i])) {
                        System.out.println("转换==== " + array[i]);
                        aa.add(formmain0030);
                    }

                }
            }
            for (Formmain0030 formmain0030 : aa) {
                String ss = HttpRequest.sendGet("https://api.yonyouup.com/system/tradeid", "from_account=" + from_account
                        + "&app_key=" + app_key + "&token=" + id);
                String gg = JSONObject.fromObject(ss).getString("trade");
                // System.out.println("token==="+at);
                String tradeid = JSONObject.fromObject(gg).getString("id");
                System.out.println("tradeid==============" + tradeid);
                Formmain0030U8 formmain0030U8 = new Formmain0030U8();
                formmain0030U8.setCode(formmain0030.getField0001());
                formmain0030U8.setEmail(formmain0030.getField0008());
                formmain0030U8.setName(formmain0030.getField0002());
                formmain0030U8.setSort_code(formmain0030.getField0003());
                formmain0030U8.setPhone(formmain0030.getField0009());


                String url = "https://api.yonyouup.com/api/vendor/add?from_account=" + from_account
                        + "&to_account=" + to_account + "&app_key=" + app_key + "&token=" + id
                        + "&tradeid=" + tradeid;

                String parm = "{\"vendor\":{\"code\":\"" + formmain0030U8.getCode() + "\",\"name\":\"" + formmain0030U8.getName() + "\",\"sort_code\":\"" + formmain0030U8.getSort_code() + "\"," +
                        "\"email\":\"" + formmain0030U8.getEmail() + "\",\"phone\":\"" + formmain0030U8.getPhone() + "\" }}";
                JSONObject jsonObject = JSONObject.fromObject(parm);
                String post = HttpRequest.post(jsonObject, url);
                System.out.println("post===============" + post);
                String url1 = JSONObject.fromObject(post).getString("url");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String tt = httpClientService.doGet(url1);
                System.out.println("tt========" + tt);
                String errmsg = JSONObject.fromObject(tt).getString("errmsg");
                System.out.println("errmsg===============" + errmsg);
                if (errmsg.equals("")) {
                    String code = JSONObject.fromObject(post).getString("id");
                    formmain0030Mapper.updateFormmain0027Field0001(formmain0030.getField0001(), code);

                } else {
                    formmain0030Mapper.updateFormmain0027Syn(formmain0030.getField0001(), errmsg);

                }
            }
        }

        return "成功";
    }
}