package com.system.controller.Synchro;

import com.system.mapper.Synchro.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("message")
    @ResponseBody
    public void RestWebServiceClient() {
        Map<String, Object> map = new HashMap<>();
        map.put("ID", "-2232241521748062687");//ID
        map.put("USER_ID", "2490380596587742846");//消息接受人
        map.put("TASK_ID", 0);//主对象Id
        map.put("SENDER_ID", "2490380596587742846");//发送者ID
        map.put("REFERENCE_ID", "-5036186315127045726");//任务ID
        map.put("MESSAGE_CATEGORY", 1);//消息所属应用ID
        map.put("MESSAGE_TYPE", 0);//消息类型
        map.put("MESSAGE_CONTENT", "测试啊123456");//消息内容
        map.put("CREATION_DATE", "2020-04-22 14:48:48.277");//创建时间
        map.put("IS_READ", 0);//是否已读
        map.put("IDENTIFIER", "0000000000");//数据标志位
        map.put("OPEN_TYPE", 0);//链接打开方式
        map.put("USER_HISTORY_MESSAGE_ID", "-2232241521748062687");//历史消息ID
        map.put("IMPORTANT_LEVEL", 1);//重要程度
        map.put("LINK_TYPE", "message.link.col.pending");
        map.put("IS_AT", "0");
        map.put("IS_TRACK", "0");
        map.put("IS_REPLY", "0");
        map.put("IS_TEMPLATE", "0");
        //  map.put("TEMPLATE_ID","NULL");
        map.put("SOURCE_TYPE", "0");
        messageMapper.InsetintoMessage(map);

    }
}
