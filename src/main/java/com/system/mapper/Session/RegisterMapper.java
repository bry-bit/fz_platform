package com.system.mapper.Session;

import com.system.pojo.Session.Login_register;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterMapper {
    void logon(Login_register loginRegister);

    List<Login_register> whetherOrNot(Login_register loginRegister);
}
