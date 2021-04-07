package com.luolin.controller;

import com.luolin.entity.Menu;
import com.luolin.service.MenuService;
import com.luolin.service.UserDataService;
import com.luolin.utils.Result;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("create")
    public Result create(@RequestBody Menu menu){
        int flag = menuService.create(menu);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = menuService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Menu menu){
        int flag = menuService.update(menu);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(menuService.detail(id));
    }

    @PostMapping("query")
    public Result query(){
        Menu menu = new Menu();
        //获取当前登录用户
        UserData userData = userDataService.getUserData();
        menu.setType(userData.getType());
        return Result.ok(menuService.query(menu));
    }

}
