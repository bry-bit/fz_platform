package com.system.controller.Purchase;

import com.system.service.sendService.SendService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class SendController {

    @Resource
    SendService sendService;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void test(){
        sendService.autoSycContract();
    }
}
