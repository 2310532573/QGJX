package com.luolin.controller;

import com.luolin.entity.Company;
import com.luolin.entity.Student;
import com.luolin.entity.User;
import com.luolin.framework.redis.RedisUtil;
import com.luolin.framework.role.Role;
import com.luolin.service.CompanyService;
import com.luolin.service.StudentService;
import com.luolin.service.UserService;
import com.luolin.utils.Result;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> map){

        String account = map.get("account");
        String password = map.get("password");
        String type = map.get("type");

        boolean flag = false;
        UserData userData = new UserData();
        if(String.valueOf(Role.ADMIN.getCode()).equals(type)){ //管理员登录
            User login = userService.login(account, password);
            if(login != null){
                userData.setId(login.getId());
                userData.setAccount(login.getUserName());
                userData.setName(login.getName());
                userData.setType(Role.ADMIN.getCode());
                flag = true;
            }
        }

        if(String.valueOf(Role.COMPANY.getCode()).equals(type)){ //企业登录
            Company login = companyService.login(account, password);
            if(login != null){
                userData.setId(login.getId());
                userData.setAccount(login.getAccount());
                userData.setName(login.getName());
                userData.setType(Role.COMPANY.getCode());
                flag = true;
            }
        }

        if(String.valueOf(Role.STUDENT.getCode()).equals(type)){ //学生登录
            Student login = studentService.login(account, password);
            if(login != null){
                userData.setId(login.getId());
                userData.setAccount(login.getAccount());
                userData.setName(login.getName());
                userData.setType(Role.STUDENT.getCode());
                flag = true;
            }
        }
        if(flag){
            //登录成功的情况
            String token = UUID.randomUUID().toString();
            userData.setToken(token);
            redisUtil.set(token,userData,RedisUtil.EXPR);
            return Result.ok(userData);
        }else{
            return Result.fail("用户或密码错误");
        }
    }

}
