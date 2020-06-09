package com.system.service.Session;

import com.system.pojo.Session.Login_register;

import java.util.List;

public interface RegisterService {
    void logon(Login_register loginRegister);

    List<Login_register> whetherOrNot(Login_register loginRegister);
}
