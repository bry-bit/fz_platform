package com.system.controller.QuotationReview;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

public class test {

    public static void main(String[] args) {
        //REST用户登录名
        String userName = "fz";
        //REST用户密码
        String password = "64097647-a4c5-4067-9dca-a281b0634db6";
        //定义REST动态客户机
        CTPRestClient client = null;

        //获取token
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance("http://oa.tjasset.com:19997");
        client = clientManager.getRestClient();
        client.authenticate(userName, password);
        final String token = client.get("token/" + userName + "/" + password,
                String.class, "text/plain");
    }
}
