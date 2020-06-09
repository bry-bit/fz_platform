package com.system.controller.Synchro;

import com.system.util.HttpRequest;

import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Component
@Controller
@EnableAsync
public class TestController {

    private String app_key = "opae31b125fd61d09ee";
    private String app_secret = "39ad49a6705245c6b2ac8306a77a63b2";
    private String from_account = "zhaoyinhe";
    private String to_account = "test_zhaoyinhe";
    HashMap hashMap = new HashMap();

    @ResponseBody
    @Scheduled(fixedRate = 2 * 60 * 60 * 1000)
    public String add() {
        String token = "";
        String result = "";
       /* URL url = new URL("https://api.yonyouup.com/system/token?from_account=" + from_account
                + "&app_key=" + app_key + "&app_secret=" + app_secret);*/
        String url = "https://api.yonyouup.com/system/token?from_account=" + from_account
                + "&app_key=" + app_key + "&app_secret=" + app_secret;
        String s = HttpRequest.sendGet("https://api.yonyouup.com/system/token", "from_account=" + from_account
                + "&app_key=" + app_key + "&app_secret=" + app_secret);
//        String doPost = httpClientService.doGet(url);
        String at = JSONObject.fromObject(s).getString("token");
        System.out.println("token===" + at);
        String id = JSONObject.fromObject(at).getString("id");
        System.out.println("id===" + id);


        System.out.println("=================" + s);
        hashMap.put("id", s);

        return s;

    }
}
