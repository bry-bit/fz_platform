package com.system.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    //该对象封装了请求的查实时间

    /**
     * 为了用户请求方便封装doGet方法
     * 参数说明:
     * 1.String url地址
     * 2.map<String,String>集合封装参数
     * 3.字符集编码
     */
    public String doGet(String url, HashMap<String, String> params, String charset) {
        //判断编码格式是否为空
        if (StringUtils.isEmpty(charset)) {
            //是空则赋值
            charset = "UTF-8";
        }
        //判断paramas是否是空,不是则拼接url
        if (params != null) {
            url = url + "?";
            //查分map集合,循环遍历获得key和value
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue() + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        HttpGet httpGet = new HttpGet(url);
        //设置超时时间
        httpGet.setConfig(requestConfig);
        String resutJson = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //判断状态信息
            if (response.getStatusLine().getStatusCode() == 200) {
                //说明数据正确
                resutJson = EntityUtils.toString(response.getEntity(), charset);
                //System.out.println("web服务器返回的json值:"+resutJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return resutJson;
    }

    //重写get方法
    public String doGet(String url) {
        return doGet(url, null, null);
    }

    public String doGet(String url, HashMap<String, String> params) {
        return doGet(url, params, null);
    }

    //实现httpClient POST提交
    public String doPost(String url, Map<String, String> params, String charset) {
        //System.out.println("111111111111111111111"+params.get("json"));
        String result = null;
        //1.定义请求类型
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);    //定义超时时间
        //2.判断字符集是否为null
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        //3.判断用户是否传递参数
        if (params != null) {
            //3.2准备List集合信息
            List<NameValuePair> parameters =
                    new ArrayList<>();
            //3.3将数据封装到List集合中
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                int i = 0;
                //	System.out.println("2222222222222: "+parameters.get(i));
                i++;
            }
            //3.1模拟表单提交
            try {
                UrlEncodedFormEntity formEntity =
                        new UrlEncodedFormEntity(parameters, charset); //采用u8编码
                //3.4将实体对象封装到请求对象中
                post.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //4.发送请求
        try {
            CloseableHttpResponse response =
                    httpClient.execute(post);
            System.out.println("response: " + response);
            //4.1判断返回值状态
            if (response.getStatusLine().getStatusCode() == 200) {
                //	System.out.println("发送的值 : "+response.getEntity());
                //4.2表示请求成功
                result = EntityUtils.toString(response.getEntity(), charset);
                //System.out.println("返回值 : "+result);
            } else {
                System.out.println("获取状态码信息:" + response.getStatusLine().getStatusCode());
                throw new RuntimeException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String doPost(String url) {
        return doPost(url, null, null);
    }

    public String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }

    public String doPost(String url, String charset) {
        return doPost(url, null, charset);
    }
}
