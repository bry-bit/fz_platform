package com.system.service.impl.Session;

import com.system.mapper.Session.RegisterMapper;
import com.system.pojo.Session.Login_register;
import com.system.service.Session.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper mapper;

    @Override
    public void logon(Login_register loginRegister) {
        mapper.logon(loginRegister);
    }

    @Override
    public List<Login_register> whetherOrNot(Login_register loginRegister) {
       List<Login_register> list = mapper.whetherOrNot(loginRegister);
        return list;
    }
}
