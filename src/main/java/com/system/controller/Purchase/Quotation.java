package com.system.controller.Purchase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.system.pojo.Purchase.Main_quotation;
import com.system.pojo.Purchase.Sub_quotation;
import com.system.service.Purchase.QuotationService;
import com.system.util.JSONUtil;
import com.system.util.ObjectMapperUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Quotation {
    @Autowired
    private QuotationService service;

    JSONUtil jsonUtil = new JSONUtil();

//    @RequestMapping("selSubQuo")
//    @ResponseBody
//    public String selSubQuo(String name, String state, String bid_state) {
//        try {
//            System.out.println("bid_state：" + bid_state);
//            Sub_quotation subQuotation = new Sub_quotation();
//            subQuotation.setBid_state(bid_state);
//            if (state.equals("3") || state == "3") {
//                subQuotation.setSupplier_name(name);
//                System.out.println(subQuotation);
//                List<Sub_quotation> list = service.selSubQuo(subQuotation);
//
//                return jsonUtil.toJson("0", list, "查询成功！", "");
//            } else {
//                subQuotation.setProposer(name);
//                System.out.println(subQuotation);
//                List<Sub_quotation> list = service.selSubQuo(subQuotation);
//
//                return jsonUtil.toJson("0", list, "查询成功！", "");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return jsonUtil.toJson("1", "", "查询失败！", "");
//        }
//    }

    @RequestMapping("selMainNotQuoted")
    @ResponseBody
    public String selMainNotQuoted(String name, String state, String bid_state, Integer page, Integer limit) {
        try {
            System.out.println(name + "," + state + "," + bid_state);
            Main_quotation mainQuotation = new Main_quotation();
            mainQuotation.setBid_state(bid_state);
            mainQuotation.setPage(page);
            mainQuotation.setLimit(limit);

            if (state.equals("3") || state == "3") {
                mainQuotation.setSupplier_name(name);
                List<Main_quotation> list = service.selMainNotQuoted(mainQuotation);
                List<Main_quotation> quotationList = service.selMainLiat(mainQuotation);

                return jsonUtil.toJson("0", list, "查询成功！", quotationList.size());
            } else {
                mainQuotation.setName(name);
                List<Main_quotation> list = service.selMainNotQuoted(mainQuotation);
                List<Main_quotation> quotationList = service.selMainLiat(mainQuotation);

                return jsonUtil.toJson("0", list, "查询成功！", quotationList.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }

    @RequestMapping("uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, String id, String state, String supplier_name) {
        try {
            if (file.isEmpty()) {
                return jsonUtil.toJson("2", "", "上传文件为空！", "");
            } else {
                //文件名
                String fileName = file.getOriginalFilename();

                if (state.equals("3") || state.equals(3)) {
                    //上传文件路径
                    String filePath = "D:\\apache-tomcat-9.0.35\\webapps\\fzb\\WEB-INF\\classes\\static\\uploadSup";
                    //全路径读取
                    File dest = new File(filePath + fileName);
                    System.out.println(dest);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    file.transferTo(dest);

                    Sub_quotation subQuotation = new Sub_quotation();
                    subQuotation.setId(id);
                    subQuotation.setUpload_supplier("uploadSup/" + fileName);
                    subQuotation.setSupplier_name(supplier_name);
                    service.updateUpload(subQuotation);
                } else {
                    //上传文件路径
                    String filePath = "D:\\apache-tomcat-9.0.35\\webapps\\fzb\\WEB-INF\\classes\\static\\uploadSup";
                    //全路径读取
                    File dest = new File(filePath + fileName);
                    System.out.println(dest);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    file.transferTo(dest);

                    Sub_quotation subQuotation = new Sub_quotation();
                    subQuotation.setId(id);
                    subQuotation.setUpload_buyer("uploadCai/" + fileName);
                    subQuotation.setSupplier_name(supplier_name);
                    service.updateUpload(subQuotation);
                }
            }
            return jsonUtil.toJson("0", "", "上传成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "上传失败！", "");
        }
    }

    @RequestMapping("/downLoad")
    @ResponseBody
    public String downLoad(@RequestBody String data, String state) {
        try {
            System.out.println(data);
            //将json字符串赋值在对象里
            Sub_quotation subQuotation = JSONObject.parseObject(data, Sub_quotation.class);
            if (state.equals("3") || state.equals(3)) {
                if (subQuotation.getUpload_buyer() != "" || subQuotation.getUpload_buyer() != null
                        || subQuotation.getUpload_buyer().isEmpty()) {
                    //获取文件名
                    String[] fileName = subQuotation.getUpload_buyer().split("/");
                    return jsonUtil.toJson("0", fileName[1], subQuotation.getUpload_buyer(), "");
                } else {
                    return jsonUtil.toJson("1", "", "采购员未上传附件！", "");
                }
            } else {
                if (subQuotation.getUpload_supplier() != "" || subQuotation.getUpload_supplier() != null
                        || subQuotation.getUpload_supplier().isEmpty()) {
                    //获取文件名
                    String[] fileName = subQuotation.getUpload_supplier().split("/");
                    return jsonUtil.toJson("0", fileName[1], subQuotation.getUpload_supplier(), "");
                } else {
                    return jsonUtil.toJson("1", "", "供应商未上传附件！", "");
                }

            }
        } catch (NullPointerException se) {
            return jsonUtil.toJson("1", "", "下载失败,未上传附件！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "下载失败！", "");
        }
    }

    @RequestMapping("/quotedPrice")
    @ResponseBody
    public String quotedPrice(@RequestBody String data, String offer, String issuance_date) {
        try {
            System.out.println(data);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            double num = Double.valueOf(offer);
            DecimalFormat formater = new DecimalFormat("0.0000");
            formater.setRoundingMode(RoundingMode.FLOOR);

            if (StringUtils.isNotBlank(data)) {
                data = data.trim();
                if (data.startsWith("{") && data.endsWith("}")) {
                    Sub_quotation subQuotation = ObjectMapperUtil.toObject(data, Sub_quotation.class);

                    subQuotation.setOffer(formater.format(num));
                    subQuotation.setIssuance_date(issuance_date);
                    subQuotation.setOffer_date(format.format(new Date()));

                    service.updateSubQuo(subQuotation);
                } else {
                    List<Sub_quotation> list = JSONObject.parseArray(data, Sub_quotation.class);
                    for (int i = 0; i < list.size(); i++) {
                        Sub_quotation subQuotation = JSONObject.parseObject(
                                JSONObject.toJSONString(list.get(i)), Sub_quotation.class);
                        subQuotation.setOffer(formater.format(num));
                        subQuotation.setIssuance_date(issuance_date);
                        subQuotation.setOffer_date(format.format(new Date()));

                        service.updateSubQuo(subQuotation);
                    }
                }
            }
            return jsonUtil.toJson("0", "", "报价成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "报价失败！", "");
        }
    }

    /**
     * 退回报价
     *
     * @param data
     * @return
     */
    @PostMapping("/backSubquotation")
    @ResponseBody
    public String backSubquotation(@RequestBody String data, String msg) {
        List<Sub_quotation> sub_quotations = JSONArray.parseArray(data, Sub_quotation.class);
        System.out.println(sub_quotations);

        for (int i = 0; i < sub_quotations.size(); i++) {
            Sub_quotation subQuotation = JSONObject.parseObject(
                    JSONObject.toJSONString(sub_quotations.get(i)), Sub_quotation.class);
            subQuotation.setRelation_id(msg);
            service.updateBackSubquotation(subQuotation);
        }
        return service.backSubquotation(sub_quotations, msg);
    }

    /**
     * 查看历史报价
     *
     * @param id
     * @return
     */
    @PostMapping("/selSubQuoHistory")
    @ResponseBody
    public String selSubQuoHistory(@RequestParam(value = "id") String id, @RequestParam(value = "yemian") String yemian
            , @RequestParam(value = "stock_name", required = false) String stock_name
//            , @RequestParam(value = "stock_sort", required = false) String stock_sort
            , @RequestParam(value = "norms", required = false) String norms
//            , @RequestParam(value = "unit", required = false) String unit
//            , @RequestParam(value = "brand", required = false) String brand
//            , @RequestParam(value = "stock_code", required = false) String stock_code
//            , @RequestParam(value = "proName", required = false) String proName
    ) {

        return service.selSubQuoHistory(yemian, stock_name, norms);
    }


    @RequestMapping("SetQuote")
    @ResponseBody
    public String SetQuote(@RequestBody String data, String totals, String total) {
        try {
            System.out.println(data);
            System.out.println(totals + "," + total);
            //差额
            Double margin = Double.valueOf(totals) - Double.valueOf(total);
            System.out.println("差额：" + margin);

            List<Sub_quotation> list = JSONObject.parseArray(data, Sub_quotation.class);
            for (int i = 0; i < list.size(); i++) {
                Sub_quotation subQuotation = JSONObject.parseObject(
                        JSONObject.toJSONString(list.get(i)), Sub_quotation.class);
//                System.out.println(Double.valueOf(list.get(i).getQuantity()));
                //小计
                Double xiaoji = Double.valueOf(list.get(i).getQuantity()) * Double.valueOf(list.get(i).getOffer());
                System.out.println("小计：" + xiaoji);
                //平分
                Double offer = (xiaoji - ((xiaoji / Double.valueOf(totals)) * margin)) / Double.valueOf(list.get(i).getQuantity());
                System.out.println("平分：" + offer);
                subQuotation.setOffer(String.valueOf(offer));

                service.updateSubQuo(subQuotation);
            }

            return jsonUtil.toJson("0", "", "修改成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "修改失败！", "");
        }
    }
}
