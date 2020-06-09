package com.system.controller.FileUpload;

import com.system.controller.Synchro.Formson0047Controller;
import com.system.pojo.FileUpload.*;
import com.system.service.FileUpload.ExcelFileUploadService;
import com.system.util.JSONUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExcelFileUpload {
    @Autowired
    private ExcelFileUploadService service;

    JSONUtil jsonUtil = new JSONUtil();

    @Autowired
    private Formson0047Controller formson0047Controller;

    @RequestMapping("FileUpload")
    @ResponseBody
    public String FileUpload(@RequestParam("file") MultipartFile file, String ID) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            File_leading fileLeading = new File_leading();
            fileLeading.setFileName(file.getOriginalFilename());
            fileLeading.setIdentifier("1");

            List<File_leading> fileLeadingList = service.select(fileLeading);

            Random random = new Random();
            long id = random.nextLong();

            if (fileLeadingList.size() > 0) {
                return jsonUtil.toJson("2", "", "该文件已存在！","");
            } else {

                List<Org_member> orgMemberList = service.getAchieve(ID);
                Formmain_0046_temp formmain0046Temp = new Formmain_0046_temp();
                formmain0046Temp.setID(String.valueOf(id));
                formmain0046Temp.setField0005(orgMemberList.get(0).getField0001());
                formmain0046Temp.setField0006(orgMemberList.get(0).getField0002());
                formmain0046Temp.setField0020(orgMemberList.get(0).getName());
                formmain0046Temp.setField0021(orgMemberList.get(0).getOrgName());


                //对Excel文件进行解析（分为xls/xlsx）
                String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                //通过不同后缀名进行解析
                Workbook workbook = null;
                if (fileType.equals("xls")) {
                    workbook = new HSSFWorkbook(file.getInputStream());
                } else if (fileType.equals("xlsx")) {
                    workbook = new XSSFWorkbook(file.getInputStream());
                } else {
                    return jsonUtil.toJson("3", "", "上传Excel文件类型错误！","");
                }
                //对Excel表格解析，存入数据组中
                Sheet sheet = workbook.getSheetAt(0);

                List<List<String>> listOut = new ArrayList<>();

                for (Row row : sheet) {
                    List<String> listIn = new ArrayList<>(); //一个sheet一行
                    for (Cell cell : row) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        listIn.add(cell.getStringCellValue());
                    }
                    listOut.add(listIn);
                }

                String flag = "fail";
                for (List<String> listin : listOut) {
                    for (int i = 0; i < listin.size(); i++) {
                        String s = listin.get(i);
                        if (s.equals("外 购 件 明 细")) {
                            flag = "succ";
                        }
                    }
                }

                if (flag.equals("fail")) {
                    return jsonUtil.toJson("4", "", file.getOriginalFilename() + "文件模板错误!","");
                }

                formmain0046Temp.setField0003(listOut.get(2).get(2));
                formmain0046Temp.setField0001(listOut.get(2).get(5));
                formmain0046Temp.setField0024(listOut.get(4).get(2));
                formmain0046Temp.setField0025(listOut.get(4).get(5));
                formmain0046Temp.setField0026(listOut.get(3).get(5));
                formmain0046Temp.setField0027("1");
                formmain0046Temp.setFileName(file.getOriginalFilename());

                service.Formmain_0046(formmain0046Temp);

                for (int i = 6; i < listOut.size(); i++) {
                    Formson_0047_temp formson0047Temp = new Formson_0047_temp();
                    long fid = random.nextLong();

                    formson0047Temp.setField0008(listOut.get(i).get(0));
                    formson0047Temp.setField0013(listOut.get(i).get(1));
                    formson0047Temp.setField0032(listOut.get(i).get(2));
                    formson0047Temp.setField0014(listOut.get(i).get(3));
                    formson0047Temp.setField0034(listOut.get(i).get(4));
                    formson0047Temp.setField0010(listOut.get(i).get(5));
                    formson0047Temp.setField0038(listOut.get(i).get(6));
                    formson0047Temp.setField0041(listOut.get(i).get(7));
                    formson0047Temp.setID(String.valueOf(fid));
                    formson0047Temp.setFormmain_id(String.valueOf(id));
                    formson0047Temp.setField0039("1");
                    formson0047Temp.setFileName(file.getOriginalFilename());
                    formson0047Temp.setField0018("正常");
                    formson0047Temp.setField0033("个");

                    service.Formson_0047(formson0047Temp);
                }

//                System.out.println(listOut);
                formson0047Controller.updatePdrecode(String.valueOf(id));

                fileLeading.setUploadTime(format.format(new Date()));
                fileLeading.setFid(String.valueOf(id));
//                service.insert(fileLeading);

            }
            return jsonUtil.toJson("0", file.getOriginalFilename(), String.valueOf(id),"");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "上传失败！","");
        }
    }

    @RequestMapping("DataShow")
    @ResponseBody
    public String DataShow() {
        try {
            List<File_leading> list = service.select(null);

            return jsonUtil.toJson("0", list, "数据呈现成功！","");
        } catch (Exception e) {
            return jsonUtil.toJson("1", "", "数据呈现失败！","");
        }
    }


    @RequestMapping("MainTableTemplateI")
    @ResponseBody
    public String MainTableTemplateI(String fileName, String id) {
        try {
            List<Formmain_0046_temp> list = service.showForma046(fileName, id);

            return jsonUtil.toJson("0", list, "获取成功！","");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "获取失败！","");
        }
    }


    @RequestMapping("SubTableTemplateI")
    @ResponseBody
    public String SubTableTemplateI(String fileName, String id, Integer page, Integer limit) {
        try {
            List<Formson_0047_temp> list = service.showForms047(fileName, id, page, limit);

            return jsonUtil.toJson("0", list, "获取成功！","");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "获取失败！","");
        }
    }

    @RequestMapping("FileUpload2")
    @ResponseBody
    public String FileUpload2(@RequestParam("file") MultipartFile file, String ID) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            File_leading fileLeading = new File_leading();
            fileLeading.setFileName(file.getOriginalFilename());
            fileLeading.setIdentifier("2");

            List<File_leading> fileLeadingList = service.select(fileLeading);

            Random random = new Random();
            long id = random.nextLong();

            if (fileLeadingList.size() > 0) {
                return jsonUtil.toJson("2", "", "该文件已存在！","");
            } else {

//                List<Org_member> orgMemberList = service.getAchieve(ID);
//                Formmain_0046_temp formmain0046Temp = new Formmain_0046_temp();
//                formmain0046Temp.setID(String.valueOf(id));
//                formmain0046Temp.setField0005(orgMemberList.get(0).getField0001());
//                formmain0046Temp.setField0006(orgMemberList.get(0).getField0002());
//                formmain0046Temp.setField0020(orgMemberList.get(0).getName());
//                formmain0046Temp.setField0021(orgMemberList.get(0).getOrgName());


                //对Excel文件进行解析（分为xls/xlsx）
                String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                //通过不同后缀名进行解析
                Workbook workbook = null;
                if (fileType.equals("xls")) {
                    workbook = new HSSFWorkbook(file.getInputStream());
                } else if (fileType.equals("xlsx")) {
                    workbook = new XSSFWorkbook(file.getInputStream());
                } else {
                    return jsonUtil.toJson("3", "", "上传Excel文件类型错误！","");
                }
                //对Excel表格解析，存入数据组中
                Sheet sheet = workbook.getSheetAt(0);

                List<List<String>> listOut = new ArrayList<>();

                for (Row row : sheet) {
                    List<String> listIn = new ArrayList<>(); //一个sheet一行
                    for (Cell cell : row) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        listIn.add(cell.getStringCellValue());
                    }
                    listOut.add(listIn);
                }

                String flag = "fail";
                for (List<String> listin : listOut) {
                    for (int i = 0; i < listin.size(); i++) {
                        String s = listin.get(i);
                        if (s.equals("明 细 表")) {
                            flag = "succ";
                        }
                    }
                }

                if (flag.equals("fail")) {
                    return jsonUtil.toJson("4", "", file.getOriginalFilename() + "文件模板错误!","");
                }

                System.out.println(listOut);

            }
            return jsonUtil.toJson("0", file.getOriginalFilename(), String.valueOf(id),"");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "上传失败！","");
        }
    }
}
