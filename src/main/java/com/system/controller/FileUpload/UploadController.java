package com.system.controller.FileUpload;

import com.system.util.upload.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UploadController {

    @RequestMapping("/downloadFile")
    @ResponseBody
    private String downloadFile(HttpServletResponse response) throws FileNotFoundException {
        //被下载的文件在服务器中的路径,
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsolutePath(), "static/");
        String newUrl = upload.getAbsolutePath() + "/";
        // String downloadFilePath = "http://localhost:8888/abc.docx";
        //被下载文件的名称
        String fileName = "";
        File file = new File(newUrl);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    outputStream.flush();
                    i = bis.read(buffer);
                }

                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

    /**
     * 上传
     *
     * @param fileN
     * @return
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public String postR(@RequestParam MultipartFile[] fileN) throws FileNotFoundException {
        System.out.println(fileN);
        //服务器中的路径静态路径,
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsolutePath(), "static/");
        String newUrl = upload.getAbsolutePath() + "/uploadCai";
        try {
            newUrl = java.net.URLDecoder.decode(newUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("newUrl = " + newUrl);
        for (int i = 0; i < fileN.length; i++) {
            UploadUtils.approvalFile(fileN[i], newUrl);
        }
        return "succ";
    }

}
