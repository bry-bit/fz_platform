package com.system.controller.Purchase;

import com.alibaba.fastjson.JSONObject;
import com.system.pojo.Purchase.*;
import com.system.service.Purchase.QuotationService;
import com.system.service.Purchase.RequirementsService;
import com.system.util.JSONUtil;
import com.system.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Requirements {
    @Autowired
    private RequirementsService service;

    @Autowired
    private QuotationService quotationService;

    JSONUtil jsonUtil = new JSONUtil();

    @RequestMapping("FileMainTable")
    @ResponseBody
    public String FileMainTable(String name, Integer page, Integer limit) {
        try {
            System.out.println(name);
            List<Purchase_primary> list = service.fileMainTable(name);

            return jsonUtil.toJson("0", list, "获取成功！", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "获取失败！", "");
        }
    }

    @RequestMapping("FileSubTable")
    @ResponseBody
    public String FileSubTable(String id) {
        try {
            Purchase_sublist purchaseSublist = new Purchase_sublist();
            purchaseSublist.setId(id);

            List<Purchase_sublist> list = service.fileSubTable(purchaseSublist);

            return jsonUtil.toJson("0", list, "获取成功！", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "获取失败！", "");
        }
    }

    @RequestMapping("SupTable")
    @ResponseBody
    public String SupTable(Integer page, Integer limit) {
        try {
            List<Tabulation_listing> list = service.SupTable(page, limit);

            List<Tabulation_listing> lists = service.SupTables();
            return jsonUtil.toJson("0", list, "获取成功！", lists.size());
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "获取失败！", "");
        }
    }

    @RequestMapping("InsertTable")
    @ResponseBody
    public String InsertTable(@RequestBody String data) {
        try {
            System.out.println("1-----------------------------------");
            System.out.println("data：" + data);
            JSONObject object = JSONObject.parseObject(data);
            String supList = object.getString("list");
            String chooseData = object.getString("data").replaceAll("\\\\", "");
            System.out.println("2-----------------------------------");
            System.out.println("chooseData：" + chooseData);

            List<Purchase_sublist> dataList = JSONObject.parseArray(chooseData, Purchase_sublist.class);
            System.out.println("3-----------------------------------");
            System.out.println("dataList：" + dataList);
            List<Tabulation_listing> list = JSONObject.parseArray(supList, Tabulation_listing.class);
            System.out.println("4-----------------------------------");
            System.out.println("list：" + list);

            for (int j = 0; j < dataList.size(); j++) {
                Tabulation_listing formmain0030 = new Tabulation_listing();
                formmain0030.setSublist_id(dataList.get(j).getId());
                for (int i = 0; i < list.size(); i++) {
                    List<Tabulation_listing> list1 = service.selectTable(dataList.get(j).getId(), list.get(i).getField0001());
                    System.out.println("5-----------------------------------");
                    System.out.println("list1：" + list1);
                    if (list1.size() > 0) {
                        return jsonUtil.toJson("2", "", dataList.get(j).getStock_name() + "存在已经插入过的供应商！", "");
                    } else {
                        formmain0030.setField0001(list.get(i).getField0001());
                        formmain0030.setField0002(list.get(i).getField0002());
                        formmain0030.setOpen_date(list.get(i).getOpen_date());
                        formmain0030.setClose_date(list.get(i).getClose_date());
                        System.out.println("6-----------------------------------");
                        System.out.println("formmain0030：" + formmain0030);
                        service.insertTable(formmain0030);
                    }
                }
            }

            return jsonUtil.toJson("0", "", "插入成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "插入失败！", "");
        }
    }

    @RequestMapping("findSupplier")
    @ResponseBody
    public String findSupplier(String field0002) {
        try {
            List<Tabulation_listing> list = service.findSupplier(field0002);
            return jsonUtil.toJson("0", list, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }


    @RequestMapping("selTable")
    @ResponseBody
    public String selTable(String id) {
        try {
            List<Tabulation_listing> list = service.selectTable(id, null);
            return jsonUtil.toJson("0", list, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }

    @RequestMapping("selPurTable")
    @ResponseBody
    public String selPurTable(String stock_name, String brand, String norms, String id) {
        try {
            Purchase_sublist purchaseSublist = new Purchase_sublist();
            purchaseSublist.setId(id);
            purchaseSublist.setNorms(norms);
            purchaseSublist.setStock_name(stock_name);
            purchaseSublist.setBrand(brand);

            List<Purchase_sublist> list = service.fileSubTable(purchaseSublist);

            return jsonUtil.toJson("0", list, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }


    @RequestMapping("RequirementRelease")
    @ResponseBody
    public String RequirementRelease(@RequestBody String data) {
        try {
            List<Purchase_sublist> sublists = JSONObject.parseArray(data, Purchase_sublist.class);
            for (int i = 0; i < sublists.size(); i++) {
                Sub_quotation subQuotation = new Sub_quotation();
                subQuotation.setId(sublists.get(i).getId());
                subQuotation.setRelation_id(sublists.get(i).getRelation_id());
                subQuotation.setOrders(sublists.get(i).getOrders());
                subQuotation.setLine(sublists.get(i).getLine());
                subQuotation.setStock_code(sublists.get(i).getStock_code());
                subQuotation.setStock_name(sublists.get(i).getStock_name());
                subQuotation.setStock_sort(sublists.get(i).getStock_sort());
                subQuotation.setStation_no(sublists.get(i).getStation_no());
                subQuotation.setNorms(sublists.get(i).getNorms());
                subQuotation.setUnit(sublists.get(i).getUnit());
                subQuotation.setBrand(sublists.get(i).getBrand());
                subQuotation.setDelivery_date(sublists.get(i).getDelivery_date());
                subQuotation.setQuantity(sublists.get(i).getQuantity());
                subQuotation.setRemarks(sublists.get(i).getRemarks());
                subQuotation.setClass_code(sublists.get(i).getClass_code());
                subQuotation.setEnable_status(sublists.get(i).getEnable_status());
                subQuotation.setBid_state("0");

                List<DemandSummary> summaryList = service.queryP_T(sublists.get(i).getId());
                for (int j = 0; j < summaryList.size(); j++) {
                    subQuotation.setSupplier_name(summaryList.get(j).getField0002());
                    subQuotation.setSupplier_code(summaryList.get(j).getField0001());
                    subQuotation.setField0025(summaryList.get(j).getFiles_code());
                    subQuotation.setOpen_date(summaryList.get(j).getOpen_date());
                    subQuotation.setClose_date(summaryList.get(j).getClose_date());

                    quotationService.insertSub_quotation(subQuotation);

                    Purchase_sublist sublist = new Purchase_sublist();
                    sublist.setPost_status("2");
                    sublist.setId(sublists.get(i).getId());
                    sublist.setSelect_or_not("1");
                    service.updateSublist(sublist);

                    List<Main_quotation> list = quotationService.whetherOrNotId(summaryList.get(0).getId());
                    if (list.isEmpty()) {
                        Main_quotation mainQuotation = new Main_quotation();
                        mainQuotation.setId(summaryList.get(j).getId());
                        mainQuotation.setItem_code(summaryList.get(j).getItem_code());
                        mainQuotation.setItem_name(summaryList.get(j).getItem_name());
                        mainQuotation.setForm_code(summaryList.get(j).getForm_code());
                        mainQuotation.setForm_name(summaryList.get(j).getForm_name());
                        mainQuotation.setProposer(summaryList.get(j).getProposer());
                        mainQuotation.setDept(summaryList.get(j).getDept());
                        quotationService.insertMain_quotation(mainQuotation);
                    } else {
                        continue;
                    }
                }
            }

            return jsonUtil.toJson("0", "", "发布成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "发布失败！", "");
        }
    }

    @RequestMapping("updateTime")
    @ResponseBody
    public String updateTime(@RequestBody String data, String id) {
        try {
            List<Tabulation_listing> sublists = JSONObject.parseArray(data, Tabulation_listing.class);
            for (int i = 0; i < sublists.size(); i++) {
                Tabulation_listing tabulationListing = JSONObject.parseObject(
                        JSONObject.toJSONString(sublists.get(i)), Tabulation_listing.class);
                tabulationListing.setSublist_id(id);
                service.updateTime(tabulationListing);
            }

            return jsonUtil.toJson("0", "", "修改成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "修改失败！", "");
        }
    }

    /**
     * 定时修改采购主表状态
     *
     * @throws Exception
     */
    @Scheduled(fixedRate = 1000 * 20)
    public void timeUpdatePurchaseStatus() throws Exception {
        service.timeUpdatePurchaseStatus();
    }
    @RequestMapping("delSup")
    @ResponseBody
    public String delSup(@RequestBody String data) {
        try {
            Tabulation_listing listing = ObjectMapperUtil.toObject(data, Tabulation_listing.class);
            service.delSup(listing);
            return jsonUtil.toJson("0", "", "删除成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "删除失败！", "");
        }
    }

    @RequestMapping("mergeTable")
    @ResponseBody
    public String mergeTable(@RequestParam(value = "data") String data) {
        try {
            System.out.println("data：" + data);
            List<Purchase_primary> list = JSONObject.parseArray(data, Purchase_primary.class);

            List<Purchase_sublist> sublists = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Purchase_sublist sublist = new Purchase_sublist();
                sublist.setRelation_id(list.get(i).getId());
                sublists.add(sublist);
            }

            System.out.println(sublists);

            List<Purchase_sublist> mergeList = service.getSubList(sublists);

            return jsonUtil.toJson("0", mergeList, "查询成功！", "");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "查询失败！", "");
        }
    }

}
