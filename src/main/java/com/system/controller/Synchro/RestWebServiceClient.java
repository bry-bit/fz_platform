package com.system.controller.Synchro;

import java.util.HashMap;
import java.util.Map;


import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestWebServiceClient {
    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;

    @RequestMapping("Rest")
    @ResponseBody
    public void RestWebServiceClient() {
        //取得指定服务主机的客户端管理器。
        //参数为服务主机地址，包含{协议}{Ip}:{端口}，如http://127.0.0.1:8088
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance("http://oa.tjasset.com:19997");
        //取得REST动态客户机。
        client = clientManager.getRestClient();
        //登录校验,成功返回true,失败返回false,此过程并会把验证通过获取的token保存在缓存中
        //再请求访问其他资源时会自动把token放入请求header中。
        client.authenticate(userName, password);
        client.get("orgMember?loginName=fz-zyh", String.class);
        System.out.println("client1====" + client.get("orgMember?loginName=fz-zyh", String.class));

    }
}


