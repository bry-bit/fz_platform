package com.system.controller.Session;

import com.system.pojo.Session.Limits_state;
import com.system.pojo.Session.Login_register;
import com.system.service.Session.RegisterService;
import com.system.util.JSONUtil;
import com.system.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * 注册、登录和权限
 */
@Controller
public class Register {
    @Autowired
    private RegisterService service;

    JSONUtil jsonUtil = new JSONUtil();

    /**
     * 注册信息
     *
     * @param data
     * @return
     */
    @RequestMapping("Logon")
    @ResponseBody
    public String Logon(@RequestBody String data) {
        try {
            Login_register register = ObjectMapperUtil.toObject(data.toString(), Login_register.class);

            //判断该用户名称是否存在
            Login_register name = new Login_register();
            name.setUsername(register.getUsername());
            List<Login_register> nameList = service.whetherOrNot(name);
            if (nameList.size() > 0) {
                System.out.println("1");
                return jsonUtil.toJson("2", "", "该用户已注册！","");
            }

            //判断该手机号是否存在
            Login_register phone = new Login_register();
            phone.setPhone(register.getPhone());
            List<Login_register> phoneList = service.whetherOrNot(phone);
            if (phoneList.size() > 0) {
                System.out.println("2");
                return jsonUtil.toJson("2", "", "该手机号已注册！","");
            }

            //判断该邮箱是否存在
            Login_register email = new Login_register();
            email.setEmail(register.getEmail());
            List<Login_register> emailList = service.whetherOrNot(email);
            if (emailList.size() > 0) {
                System.out.println("3");
                return jsonUtil.toJson("2", "", "该邮箱已注册！","");
            }


            List<Login_register> list = service.whetherOrNot(register);
            if (list.isEmpty()) {
                if (register.getLimits_type().equals("采购主管")) {
                    register.setLimits_state(Limits_state.supervisor);
                } else if (register.getLimits_type().equals("普通采购员")) {
                    register.setLimits_state(Limits_state.generalPurchaser);
                } else {
                    register.setLimits_state(Limits_state.supplier);
                }

                Random random = new Random();
                long id = random.nextLong();
                register.setId(String.valueOf(id));

                service.logon(register);
            }
            return jsonUtil.toJson("0", "", "注册成功！","");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "注册失败！","");
        }
    }

    @RequestMapping("SignIn")
    @ResponseBody
    public String SignIn(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "password", required = false) String password) {
        try {
            System.out.println(username + "," + password);
            Login_register register = new Login_register();
            register.setUsername(username);
            register.setPassword(password);

            List<Login_register> list = service.whetherOrNot(register);
            System.out.println(list);
            return jsonUtil.toJson("0", list, "登录成功！","");
        } catch (Exception e) {
            e.printStackTrace();
            return jsonUtil.toJson("1", "", "登录失败！","");
        }
    }
}
