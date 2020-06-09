package com.system.controller.QuotationReview;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.seeyon.oainterface.impl.exportdata.FileUploadExporter;
import com.system.util.JSONUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class Test extends HttpServlet {

    JSONUtil jsonUtil = new JSONUtil();

    //REST用户登录名
    private String userName = "fz";
    //REST用户密码
    private String password = "64097647-a4c5-4067-9dca-a281b0634db6";
    //定义REST动态客户机
    private CTPRestClient client = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public String updateFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            //获取token
            CTPServiceClientManager clientManager = CTPServiceClientManager
                    .getInstance("http://oa.tjasset.com:19997");
            client = clientManager.getRestClient();
            client.authenticate(userName, password);
            final String token = client.get("token/" + userName + "/" + password,
                    String.class, "text/plain");

            URL url = new URL("http://oa.tjasset.com:19997/seeyon/uploadService.do?method=processUploadService"
                    + "&senderLoginName=" + "fz-bry"
                    + "&token=" + token);

            StringBuffer parameters = new StringBuffer();
            String s = parameters.toString();

            URLConnection uc = url.openConnection();
            HttpURLConnection hc = (HttpURLConnection) uc;
            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");
            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=-----");


            BufferedInputStream input = new BufferedInputStream(
                    new FileInputStream("C:\\Users\\Administrator\\Desktop\\测试文档.docx"));
            String fileName="测试文档.docx";
            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append("---------------------------7d4a6d158c9");
            sb.append("");
            sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type: msoffice\r\n\r\n");

            hc.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + "---------------------------7d4a6d158c9");
            byte[] end_data = sb.toString().getBytes("UTF-8");
            DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
            dos.write(end_data);

            FileUploadExporter fileUpload = new FileUploadExporter();
            String outStr = fileUpload.processUpload(request);

            return jsonUtil.toJson("0", outStr, "上传成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "上传失败！", "");
        }
    }
}
