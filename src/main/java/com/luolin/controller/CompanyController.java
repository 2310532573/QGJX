package com.luolin.controller;

import com.github.pagehelper.PageInfo;
import com.luolin.entity.Company;
import com.luolin.common.redis.RedisUtil;
import com.luolin.service.CompanyService;
import com.luolin.utils.Result;
import com.luolin.utils.UserThreadLocal;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("create")
    public Result create(@RequestBody Company company){
        int flag = companyService.create(company);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = companyService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Company company){
        int flag = companyService.update(company);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(companyService.detail(id));
    }

    @GetMapping("info")
    public Result info(){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        return  Result.ok(companyService.detail(userData.getId()));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Company company){
        PageInfo<Company> pageInfo = companyService.query(company);
        return Result.ok(pageInfo);
    }

}
