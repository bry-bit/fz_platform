package com.system.util;


import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExceptionUtil.class)
    @ResponseBody
    public Object handleException(ExceptionUtil e) {

        e.printStackTrace();
		
		/*String callback = request.getParameter("callback");
		System.out.println(callback);
		return JsonUtils.toJSONString(callback, new JsonUtils(e));*/
        return JSON.toJSONString(new JsonResult(e));
    }
}